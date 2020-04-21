/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.factory;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.component.render.RenderAnimations;
import fr.rphstudio.ecs.component.render.RenderBar;
import fr.rphstudio.ecs.component.script.SetPositionFromPhysic;
import fr.rphstudio.ecs.component.script.SetRenderPosition;
import fr.rphstudio.ecs.component.script.SetZOrderFromYPosition;
import fr.rphstudio.ecs.core.ECSWorld;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ggj2k19.component.Boss;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.game.DotManager;
import fr.rphstudio.ggj2k19.scripts.ScriptEnemyIA;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.ggj2k19.scripts.DeathEnemy;
import fr.rphstudio.ggj2k19.scripts.LifeToBar;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GRIGNON FAMILY
 */
public class EnemyFactory {

    private ECSWorld world;
    private final FactoryProjectile factoryProjectile;
    private DotFactory dotFactory;

    public EnemyFactory(ECSWorld world, FactoryProjectile factoryProjectile, DotFactory dotFactory) {
        this.world = world;
        this.factoryProjectile = factoryProjectile;
        this.dotFactory = dotFactory;
    }

    public Entity create(Vector2f initialPosition, List<Entity> targets,boolean boss) {
        // entity
        Entity entity = new Entity(world);

        // render


        // position
        Position position = new Position();
        position.setPosition(initialPosition);
        entity.addComponent(position);


        // render
        {
            RenderAnimations renderAnimations = new RenderAnimations(200);
            Animation anim02 = new Animation();
            float ratio = (boss?Common.ENEMY_W_PIX_BOSS:Common.ENEMY_W_PIX)/166.0f;
            anim02.addFrame( ResourceLoader.getImage("ENEMY").getScaledCopy(ratio), 200);
            renderAnimations.addAnimation(anim02, "WALK");
            entity.addComponent(renderAnimations);

            // Place correct Y position
            SetZOrderFromYPosition zOrder = new SetZOrderFromYPosition(position, renderAnimations);
            entity.addComponent(zOrder);

            
            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimations, -166.0f * ratio / 2.0f, -256.0f * ratio);
            entity.addComponent(setRenderPosition);
        }


        // Physic
        Physic2D physic2D = new Physic2D();
        physic2D.addCircleBody("ENEMY", initialPosition,
                boss?Common.ENEMY_RADIUS_BOSS:Common.ENEMY_RADIUS, false,
                boss?Common.ENEMY_MASSRATIO_BOSS:Common.ENEMY_MASSRATIO,
                boss?Common.ENEMY_DAMPING_BOSS:Common.ENEMY_DAMPING);
        physic2D.setXPosition(initialPosition.x);
        physic2D.setYPosition(initialPosition.y);
        entity.addComponent(physic2D);

        // Script to put position comp on physic comp position + update render rotation
        SetPositionFromPhysic posRotPhy = new SetPositionFromPhysic(position, physic2D, 0, 0);
        physic2D.setUserData(entity);
        entity.addComponent(posRotPhy);



        // Add enemy IA
        List<Physic2D> phys = new ArrayList<>();
        for (Entity target : targets) {
            phys.add((Physic2D) target.getComponent(Physic2D.class).get(0));
        }
        ScriptEnemyIA scriptEnemyIA = new ScriptEnemyIA(
                physic2D,
                phys,
                boss?Common.ENEMY_SPEED_BOSS:Common.ENEMY_SPEED,
                factoryProjectile, entity,boss);
        entity.addComponent(scriptEnemyIA);


        //Add enemy HP
        HP hp = new HP(Common.DAMAGE_OWNER_ENEMY);
        hp.setValue(boss?Common.ENEMY_HP_BOSS:Common.ENEMY_HP);
        entity.addComponent(hp);

        // Render bar of life
        RenderBar bar02 = new RenderBar(1000000, Common.LIFEBAR_W, Common.LIFEBAR_H, position);
        bar02.setColor(Color.green);
        bar02.setPercent(100);
        entity.addComponent(bar02);

        // Life 2 bar
        LifeToBar l2b = new LifeToBar(hp, bar02);
        entity.addComponent(l2b);


        // Add script to kill enemy
        DeathEnemy death = new DeathEnemy(entity, hp);
        entity.addComponent(death);

        if(boss){
            entity.addComponent(new Boss());
        }

        dotFactory.addDotToEntity(entity);

        // return entity
        this.world.addEntity(entity);
        return entity;
    }


}
