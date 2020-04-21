/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.scripts;

import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.component.render.RenderParticles;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ggj2k19.component.Damage;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.component.Lifetime;
import fr.rphstudio.ggj2k19.game.DotManager;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.ggj2k19.launcher.MainLauncher;
import java.io.IOException;
import java.util.List;
import org.dyn4j.dynamics.Body;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ParticleSystem;

/**
 * @author GRIGNON FAMILY
 */
public class CollisionProjectile implements IComponent, IScript {
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long       id;
    private final String     name;
    private Physic2D         phyCmp = null;
    private Lifetime         lifetime = null;
    private Entity           ownerEntity=null;
    
    public static RenderParticles particles;
    
    static
    {
        try
        {
            CollisionProjectile.particles = new RenderParticles(10000, "./sprites/particles/testParticle.png", 1000, ParticleSystem.BLEND_COMBINE, true);
        }
        catch(SlickException se)
        {
        }
    }
    
    
    
    //================================================
    // CONSTRUCTOR
    //================================================

    public CollisionProjectile(Physic2D phy, Lifetime lt, Entity owner) {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = "ScriptEnemyIA";
        // Store Components
        this.phyCmp = phy;
        this.lifetime = lt;
        this.ownerEntity = owner;

    }


    //================================================
    // INTERFACE METHODS
    //================================================
    @Override
    public long getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update(int delta)
    {
        
        if (this.phyCmp != null && this.lifetime !=null)
        {
            // Check if there is a collision
            Body curBody = this.phyCmp.getBody();
            List<Body> otherBodies = curBody.getInContactBodies(false);
            Entity projEntity = (Entity)(curBody.getUserData());
            
            if( projEntity.getDestroyRequest() == false )
            {
                if( otherBodies.size()>0 )
                {
                    Entity collidedEntity = (Entity)(otherBodies.get(0).getUserData());
                    if( collidedEntity != this.ownerEntity )
                    {
                        // We have found an entity to remove some life ^^
                        // Get its HP component
                        List<IComponent> hpTarget = collidedEntity.getComponent("HP");
                        // Get the DMG component of the current projectile entity
                        List<IComponent> dmgProj  = projEntity.getComponent("DMG");
                        // Get the DOT MANAGER component of the current collided entity
                        List<IComponent> dotTarget  = collidedEntity.getComponent("DOT");

                        // check if there is enough data to process direct DMG
                        if(hpTarget.size() > 0 && dmgProj.size() > 0)
                        {
                            // Perform the damage
                            Damage dmg = (Damage)dmgProj.get(0);
                            HP     hp  = (HP)hpTarget.get(0);
                            hp.increaseValue( -dmg.getDmgValue(), dmg.getDmgOwner());
                            // Add particle effect
                            try
                            {
                                CollisionProjectile.particles.addEmitter("./sprites/particles/testEmitter.xml", new Vector2f(MainLauncher.WIDTH/2,MainLauncher.HEIGHT/2));
                                CollisionProjectile.particles.setPosition(
                                        CollisionProjectile.particles.getNbEmitters()-1,
                                        new Vector2f( (float)(this.phyCmp.getXPosition()*Common.NB_PIXELS_PER_METER),
                                                      (float)(this.phyCmp.getYPosition()*Common.NB_PIXELS_PER_METER) )
                                );
                            }
                            catch(IOException ioe)
                            {
                            }
                        }


                        // Check if there is enough data to process DOT
                        if(dotTarget.size() > 0 && dmgProj.size() > 0)
                        {
                            DotManager dot = (DotManager)dotTarget.get(0);
                            Damage dmg = (Damage)dmgProj.get(0);
                            dot.addDotInfo(dmg.getDmgOwner(), dmg.getDmgType(), dmg.getDmgValue(), Common.DOT_DURATION);
                        }
                        
                        // in any case destroy the current projectile entity
                        projEntity.setDestroyRequest(2);
                    }
                }
                else if( this.lifetime.getRemainingDuration() <= 0 )
                {
                    projEntity.setDestroyRequest(2);
                }
            }
        }
    }

    //================================================
    // END OF CLASS
    //================================================
}
