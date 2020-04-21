/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.factory;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.component.render.RenderAnimations;
import fr.rphstudio.ecs.component.script.SetPositionAndRotationFromPhysic;
import fr.rphstudio.ecs.component.script.SetRenderPosition;
import fr.rphstudio.ecs.core.ECSWorld;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ggj2k19.component.Damage;
import fr.rphstudio.ggj2k19.component.Lifetime;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.ggj2k19.scripts.CollisionProjectile;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author GRIGNON FAMILY
 */
public class FactoryProjectile {

    private final ECSWorld world;

    public FactoryProjectile(ECSWorld world) {
        this.world = world;
    }

    public Entity createProjectile(Vector2f initPos, float speed, float angDeg, int dmgType, long lifetime, Entity generatorEntity, float dmgValue, int dmgSource)
    {
        Entity                           ent02;
        RenderAnimations                 ren02;
        Position                         pos02;
        Physic2D                         phy02;
        Damage                           dmg02;
        SetRenderPosition                ren2pos02;
        SetPositionAndRotationFromPhysic posRotPhy;
        CollisionProjectile              coll02;
        Lifetime                         lt02;
        
        float ang = (float)(angDeg * Math.PI / 180.0f);
        
        // entity
        ent02 = new Entity(world);    
        
        // render
        Animation anim02 = null;
        ren02 = new RenderAnimations(200);
        float ratio = 1.0f;
        switch(dmgType)
        {
            case Common.PROJECTILE_TYPE_FIRE:
                // Render FIRE
                anim02 = new Animation();
                ratio = Common.PROJECTILE_W_RADIUS/213.0f;
                anim02.addFrame( ResourceLoader.getImage("FIRE").getScaledCopy(ratio) , 100);
                ren02.addAnimation(anim02, "FIRE",(int)(Common.PROJECTILE_W_RADIUS/2), (int)(Common.PROJECTILE_W_RADIUS/2));
                break;
            case Common.PROJECTILE_TYPE_WATER:
                // Render WATER
                anim02 = new Animation();
                ratio = Common.PROJECTILE_W_RADIUS/213.0f;
                anim02.addFrame( ResourceLoader.getImage("WATER").getScaledCopy(ratio) , 100);
                ren02.addAnimation(anim02, "WATER",(int)(Common.PROJECTILE_W_RADIUS/2), (int)(Common.PROJECTILE_W_RADIUS/2));
                break;
            case Common.PROJECTILE_TYPE_ICE:
                // Render ICE
                anim02 = new Animation();
                ratio = Common.PROJECTILE_W_RADIUS/213.0f;
                anim02.addFrame( ResourceLoader.getImage("ICE").getScaledCopy(ratio) , 100);
                ren02.addAnimation(anim02, "ICE",(int)(Common.PROJECTILE_W_RADIUS/2), (int)(Common.PROJECTILE_W_RADIUS/2));
                break;
            case Common.PROJECTILE_TYPE_EARTH:
                // Render EARTH
                anim02 = new Animation();
                ratio = Common.PROJECTILE_W_RADIUS/213.0f;
                anim02.addFrame( ResourceLoader.getImage("EARTH").getScaledCopy(ratio) , 100);
                ren02.addAnimation(anim02, "EARTH",(int)(Common.PROJECTILE_W_RADIUS/2), (int)(Common.PROJECTILE_W_RADIUS/2));
                break;
        }
        // Render Finalize
        ren02.setAngle(angDeg-90.0f);
        ent02.addComponent(ren02);

        // position
        pos02 = new Position();
        pos02.setPosition( initPos );
        ent02.addComponent(pos02);

        // Physic
        phy02 = new Physic2D();
        Vector2f projPos = initPos.copy();
        projPos.x += 0.5*Math.cos(ang);
        projPos.y += 0.5*Math.sin(ang);
        phy02.addCircleBody("PROJECTILE", projPos, Common.PROJECTILE_RADIUS, false, Common.PROJECTILE_MASSRATIO, Common.PROJECTILE_DAMPING);
        phy02.setSpeed( (float)(speed*Math.cos(ang)), (float)(speed*Math.sin(ang)) );
        phy02.setUserData(ent02);
        ent02.addComponent(phy02);

        
        // Script to set render to the position
        ren2pos02 = new SetRenderPosition(pos02, ren02, -80*ratio/2.0f,-Common.PLAYER_W_PIX/2);
        ent02.addComponent(ren2pos02);
        
        // Script to put position comp on physic comp position + update render rotation
        posRotPhy = new SetPositionAndRotationFromPhysic(pos02, null, phy02, 0, 0 );
        ent02.addComponent(posRotPhy);

        // Add DMG
        dmg02 = new Damage(dmgType, dmgSource);
        dmg02.setDmgValue(dmgValue);
        ent02.addComponent(dmg02);
        
        // Life time of projectile
        lt02 = new Lifetime(lifetime);
        ent02.addComponent(lt02);
        
        // Add collision detection script
        coll02 = new CollisionProjectile(phy02,lt02,generatorEntity);
        ent02.addComponent(coll02);
        
        // return entity
        this.world.addEntity(ent02);
        return ent02;
    }
    
    
}
