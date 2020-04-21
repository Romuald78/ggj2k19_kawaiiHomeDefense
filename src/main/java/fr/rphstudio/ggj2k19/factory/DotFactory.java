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
import fr.rphstudio.ecs.core.ECSWorld;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.game.DotManager;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.ggj2k19.scripts.DeathEnemy;
import fr.rphstudio.ggj2k19.scripts.LifeToBar;
import fr.rphstudio.ggj2k19.scripts.ScriptEnemyIA;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GRIGNON FAMILY
 */
public class DotFactory {

    private ECSWorld world;

    public DotFactory(ECSWorld world) {
        this.world = world;
    }

    public void addDotToEntity(Entity entity) {
        // Add DOT MANAGER

        final ScriptEnemyIA scriptEnemyIA = entity.getComponent(ScriptEnemyIA.class).size() > 0 ?
                (ScriptEnemyIA) entity.getComponent(ScriptEnemyIA.class).get(0) : null;

        final Position position = (Position) entity.getComponent(Position.class).get(0);
        final HP hp = (HP) entity.getComponent(HP.class).get(0);

        float ratio = (Common.ENEMY_W_PIX / 213.0f) * 0.5f;
        Color transp = new Color(1, 1, 1, 0.75f);
        int yPositionMax = (int) Common.ENEMY_W_PIX;
        // fire state render
        RenderAnimations renderAnimationsFire;
        {
            renderAnimationsFire = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("FIRE").getScaledCopy(ratio), 200);
            renderAnimationsFire.addAnimation(anim02, "buff", transp);//pixel
            renderAnimationsFire.setZOrder(10000);
            entity.addComponent(renderAnimationsFire);
            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimationsFire, -0 * ratio / 2.0f, -336 * ratio);
            entity.addComponent(setRenderPosition);
        }
        RenderAnimations renderAnimationsIce;
        {
            renderAnimationsIce = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("ICE").getScaledCopy(ratio), 200);
            renderAnimationsIce.addAnimation(anim02, "buff", transp);//pixel
            renderAnimationsIce.setZOrder(10000);
            entity.addComponent(renderAnimationsIce);

            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimationsIce, -0 * ratio / 2.0f, -336 * ratio);
            entity.addComponent(setRenderPosition);
        }
        RenderAnimations renderAnimationsWater;
        {
            renderAnimationsWater = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("WATER").getScaledCopy(ratio), 200);
            renderAnimationsWater.addAnimation(anim02, "buff", transp);//pixel
            renderAnimationsWater.setZOrder(10000);
            entity.addComponent(renderAnimationsWater);

            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimationsWater, -0 * ratio / 2.0f, -336 * ratio);
            entity.addComponent(setRenderPosition);
        }
        RenderAnimations renderAnimationsEarth;
        {
            renderAnimationsEarth = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("EARTH").getScaledCopy(ratio), 200);
            renderAnimationsEarth.addAnimation(anim02, "buff", transp);//pixel
            renderAnimationsEarth.setZOrder(10000);
            entity.addComponent(renderAnimationsEarth);

            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimationsEarth, -0 * ratio / 2.0f, -336 * ratio);
            entity.addComponent(setRenderPosition);
        }
        RenderAnimations renderAnimationsLava;
        {
            renderAnimationsLava = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("LAVA").getScaledCopy(ratio), 200);
            renderAnimationsLava.addAnimation(anim02, "buff", transp);//pixel
            renderAnimationsLava.setZOrder(10000);
            entity.addComponent(renderAnimationsLava);

            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimationsLava, -0 * ratio / 2.0f, -336 * ratio);
            entity.addComponent(setRenderPosition);
        }
        RenderAnimations renderAnimationsHeal;
        {
            renderAnimationsHeal = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("HEAL").getScaledCopy(ratio), 200);
            renderAnimationsHeal.addAnimation(anim02, "buff", transp);//pixel
            renderAnimationsHeal.setZOrder(10000);
            entity.addComponent(renderAnimationsHeal);

            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimationsHeal, -0 * ratio / 2.0f, -336 * ratio);
            entity.addComponent(setRenderPosition);
        }
        RenderAnimations renderAnimationsFreeze;
        {
            renderAnimationsFreeze = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("FREEZE").getScaledCopy(ratio), 200);
            renderAnimationsFreeze.addAnimation(anim02, "buff", transp);//pixel
            renderAnimationsFreeze.setZOrder(10000);
            entity.addComponent(renderAnimationsFreeze);

            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimationsFreeze, -0 * ratio / 2.0f, -336 * ratio);
            entity.addComponent(setRenderPosition);
        }


        DotManager dotman = new DotManager(
                scriptEnemyIA,
                hp, Common.DAMAGE_OWNER_ENEMY,
                renderAnimationsEarth,
                renderAnimationsFire,
                renderAnimationsWater,
                renderAnimationsIce,
                renderAnimationsLava,
                renderAnimationsHeal,
                renderAnimationsFreeze
        );

        entity.addComponent(dotman);
    }


}
