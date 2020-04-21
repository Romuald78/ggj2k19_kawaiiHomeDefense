/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.factory;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.component.control.AnalogPadHandler;
import fr.rphstudio.ecs.component.control.KeyboardHandler;
import fr.rphstudio.ecs.component.control.PadHandler;
import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.component.render.RenderAnimations;
import fr.rphstudio.ecs.component.render.RenderBar;
import fr.rphstudio.ecs.component.script.ScriptAnalogPhysic;
import fr.rphstudio.ecs.component.script.ScriptAnalogRender;
import fr.rphstudio.ecs.component.script.ScriptKeyboardPhysic;
import fr.rphstudio.ecs.component.script.ScriptKeyboardRender;
import fr.rphstudio.ecs.component.script.SetPositionAndRotationFromPhysic;
import fr.rphstudio.ecs.component.script.SetRenderPosition;
import fr.rphstudio.ecs.component.script.SetZOrderFromYPosition;
import fr.rphstudio.ecs.core.ECSWorld;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.utils.ControllerAL;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.ggj2k19.game.PlayerInfo;
import fr.rphstudio.ggj2k19.scripts.DeathEnemy;
import fr.rphstudio.ggj2k19.scripts.LaunchProjectile;
import fr.rphstudio.ggj2k19.scripts.LifeToBar;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author GRIGNON FAMILY
 */
public class FactoryPlayer {
    
    public static Entity createPlayer(PlayerInfo pi, ECSWorld world, DotFactory dotFactory)
    {
        Entity                           entity;
        AnalogPadHandler                 pad02;
        KeyboardHandler                  key02;
        PadHandler                       but02;
        RenderAnimations                 ren02;
        Position                         position;
        Physic2D                         phy02;
        HP                               hp;
        SetRenderPosition                ren2pos02;
        SetPositionAndRotationFromPhysic posRotPhy;
        ScriptAnalogPhysic               pad2phy02;
        ScriptKeyboardPhysic             key2phy02;
        ScriptAnalogRender               pad2ren02;
        ScriptKeyboardRender             key2ren02;
        LaunchProjectile                 launch02;
        FactoryProjectile                facProj02;
        
        // entity
        entity = new Entity(world);
        
        // render
        Animation anim02;
        ren02 = new RenderAnimations(200);
        // Render LEFT
        anim02 = new Animation();
        float ratio     = Common.PLAYER_W_PIX/164.0f;
        anim02.addFrame( ResourceLoader.getImage("LEFT_"+Integer.toString(pi.getCharacterID())).getScaledCopy(ratio) , 200);
        ren02.addAnimation(anim02, "WALK_LEFT" );
        // Render RIGHT
        anim02 = new Animation();
        anim02.addFrame( ResourceLoader.getImage("RIGHT_"+Integer.toString(pi.getCharacterID())).getScaledCopy(ratio) , 200);
        ren02.addAnimation(anim02, "WALK_RIGHT");
        // Render UP
        anim02 = new Animation();
        anim02.addFrame( ResourceLoader.getImage("UP_"+Integer.toString(pi.getCharacterID())).getScaledCopy(ratio) , 200);
        ren02.addAnimation(anim02, "WALK_UP"   );
        // Render DOWN
        anim02 = new Animation();
        anim02.addFrame( ResourceLoader.getImage("PLAYER_"+Integer.toString(pi.getCharacterID())).getScaledCopy(ratio) , 200);
        ren02.addAnimation(anim02, "WALK_DOWN" );
        // Render DEATH
        anim02 = new Animation();
        anim02.addFrame( ResourceLoader.getImage("TOMB_"+Integer.toString(pi.getCharacterID())).getScaledCopy(ratio) , 200);
        ren02.addAnimation(anim02, "DEATH" );
        // Render Finalize
        ren02.setAllColor( pi.getColor() );
        entity.addComponent(ren02);

        // position
        position = new Position();
        position.setPosition( new Vector2f(0,0) );
        entity.addComponent(position);

        // Place correct Y position
        SetZOrderFromYPosition zOrder = new SetZOrderFromYPosition(position, ren02);
        entity.addComponent(zOrder);
        
        // Physic
        phy02 = new Physic2D();
        phy02.addCircleBody("PLAYER", new Vector2f(10,10), Common.PLAYER_RADIUS, false, Common.PLAYER_MASSRATIO, Common.PLAYER_DAMPING);
        phy02.setUserData(entity);
        entity.addComponent(phy02);

        // Add HP
        hp = new HP(Common.DAMAGE_OWNER_PLAYER);
        hp.setValue(Common.PLAYER_HP);
        entity.addComponent(hp);

        
        // pad/keyboard
        if( pi.getControllerID() == ControllerAL.KEYBOARD )
        {
            // Keyboard
            key02 = new KeyboardHandler();
            key02.addActionKey("LEFT" , Input.KEY_LEFT );
            key02.addActionKey("RIGHT", Input.KEY_RIGHT);
            key02.addActionKey("UP"   , Input.KEY_UP   );
            key02.addActionKey("DOWN" , Input.KEY_DOWN );
            entity.addComponent(key02);
            // Script to move the physic from keyoard
            key2phy02 = new ScriptKeyboardPhysic(phy02, key02, Common.PLAYER_SPEED);
            entity.addComponent(key2phy02);
            // Script analog render
            key2ren02 = new ScriptKeyboardRender(ren02,key02);
            entity.addComponent(key2ren02);
        }
        else
        {
            // pad Axis
            pad02 = new AnalogPadHandler( pi.getControllerID() );
            entity.addComponent(pad02);
            // Pad buttons
            but02 = new PadHandler( pi.getControllerID() );
            but02.addButton("A", ControllerAL.getButtonNumber( pi.getControllerID(), ControllerAL.Buttons.BUTTON_A));
            but02.addButton("B", ControllerAL.getButtonNumber( pi.getControllerID(), ControllerAL.Buttons.BUTTON_B));
            but02.addButton("X", ControllerAL.getButtonNumber( pi.getControllerID(), ControllerAL.Buttons.BUTTON_X));
            but02.addButton("Y", ControllerAL.getButtonNumber( pi.getControllerID(), ControllerAL.Buttons.BUTTON_Y));
            but02.addButton("L", ControllerAL.getButtonNumber( pi.getControllerID(), ControllerAL.Buttons.BUTTON_L1));
            but02.addButton("R", ControllerAL.getButtonNumber( pi.getControllerID(), ControllerAL.Buttons.BUTTON_R1));
            entity.addComponent(but02);
            // Script to move the physic from analog pad
            pad2phy02 = new ScriptAnalogPhysic(phy02, pad02, Common.PLAYER_SPEED, but02, hp);
            entity.addComponent(pad2phy02);
            // Script analog render
            pad2ren02 = new ScriptAnalogRender(ren02,pad02,hp);
            entity.addComponent(pad2ren02);
            // Factory projectile
            facProj02 = new FactoryProjectile(world);
            // Launch projectile using pad
            launch02 = new LaunchProjectile(position, but02, facProj02, pad02, entity, hp);
            entity.addComponent(launch02);
        }

        // Script to set render to the position
        ren2pos02 = new SetRenderPosition(position, ren02, -166.0f*ratio/2, -256.0f*ratio);
        entity.addComponent(ren2pos02);
        
        // Script to put position comp on physic comp position + update render rotation
        posRotPhy = new SetPositionAndRotationFromPhysic(position, null, phy02, 0, 0 );
        entity.addComponent(posRotPhy);

        {


            // Render bar of life
            RenderBar bar02 = new RenderBar(1000000, Common.LIFEBAR_W, Common.LIFEBAR_H, position);
            bar02.setColor(Color.green);
            bar02.setPercent(100);
            entity.addComponent(bar02);

            // Life 2 bar
            LifeToBar l2b = new LifeToBar(hp, bar02);
            entity.addComponent(l2b);
        }
        
        
        
        dotFactory.addDotToEntity(entity);
        
        // return entity
        return entity;
    }


}
