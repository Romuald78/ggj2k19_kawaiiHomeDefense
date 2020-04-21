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
import fr.rphstudio.ecs.component.render.RenderFont;
import fr.rphstudio.ecs.component.render.font.CuteSpriteFont;
import fr.rphstudio.ecs.component.script.SetPositionFromPhysic;
import fr.rphstudio.ecs.component.script.SetRenderPosition;
import fr.rphstudio.ecs.core.ECSWorld;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ggj2k19.component.EndGame;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.scripts.LifeToBar;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import fr.rphstudio.ecs.component.script.SetZOrderFromYPosition;

/**
 * @author GRIGNON FAMILY
 */
public class HomeFactory {

    private ECSWorld world;
    private DotFactory dotFactory;

    public HomeFactory(ECSWorld world, DotFactory dotFactory) {
        this.world = world;
        this.dotFactory = dotFactory;
    }

    public Entity create(Runnable finishGame, DotFactory dotFactory) {
        Vector2f initialPosition = new Vector2f(28, 15);
        // entity
        Entity entity = new Entity(world);

        // position
        Position position = new Position();
        position.setPosition(initialPosition);
        entity.addComponent(position);

        {
            // render
            float ratio = 1f;
            RenderAnimations renderAnimations = new RenderAnimations(200);
            Animation anim02 = new Animation();
            anim02.addFrame(ResourceLoader.getImage("HOME").getScaledCopy(ratio), 200);
            renderAnimations.addAnimation(anim02, "WALK");
            entity.addComponent(renderAnimations);

            // Script to set render to the position
            SetRenderPosition setRenderPosition = new SetRenderPosition(position, renderAnimations, -103f * ratio, -150.0f * ratio);
            entity.addComponent(setRenderPosition);

            //Add HP
            HP hp = new HP(Common.DAMAGE_OWNER_PLAYER);
            hp.setValue(Common.HOME_HP);
            entity.addComponent(hp);

            // Render bar of life
            RenderBar bar02 = new RenderBar(1000000, Common.LIFEBAR_HOME_W, Common.LIFEBAR_HOME_H, position);
            bar02.setColor(Color.green);
            bar02.setPercent(100);
            bar02.setPosition(initialPosition.x, initialPosition.y+1.0f);
            entity.addComponent(bar02);

            // Place correct Y position
            SetZOrderFromYPosition zOrder = new SetZOrderFromYPosition(position, renderAnimations);
            entity.addComponent(zOrder);
            
            
            // Life 2 bar
            LifeToBar l2b = new LifeToBar(hp, bar02);
            entity.addComponent(l2b);

            //add endgame
            EndGame endGame = new EndGame(hp, finishGame);
            entity.addComponent(endGame);
        }


        {
            // render wave counter
            float ratio = 128.0f / 80.0f;
            RenderFont renderFont = new RenderFont("WAVE 2325", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, 0.5f, new Color(0.2f,0,0,0.75f));
            entity.addComponent(renderFont);
            renderFont.setPosition(initialPosition.x - 3.5f, initialPosition.y - 3.5f);

        }
        {
            // render wave counter
            float ratio = 128.0f / 80.0f;
            RenderFont renderFont = new RenderFont("NEXT WAVE 0", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, 0.5f, new Color(0.2f,0,0,0.75f));
            entity.addComponent(renderFont);
            renderFont.setPosition(0, 0);
            // Script to set render to the position
        }


        // Physic
        Physic2D physic2D = new Physic2D();
        physic2D.addSquareBody("HOME", initialPosition, 100.0f / Common.NB_PIXELS_PER_METER, 100.0f/Common.NB_PIXELS_PER_METER, true, 1.0f, 7.0f);
        physic2D.setXPosition(initialPosition.x);
        physic2D.setYPosition(initialPosition.y);
        physic2D.setUserData(entity);
        entity.addComponent(physic2D);

        // Script to put position comp on physic comp position + update render rotation
        SetPositionFromPhysic posRotPhy = new SetPositionFromPhysic(position, physic2D, 0, 0);
        entity.addComponent(posRotPhy);


        dotFactory.addDotToEntity(entity);

        // return entity
        this.world.addEntity(entity);
        return entity;
    }


}
