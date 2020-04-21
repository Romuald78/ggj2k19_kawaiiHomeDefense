/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.scripts;

import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.factory.FactoryProjectile;
import fr.rphstudio.ggj2k19.launcher.Common;
import org.dyn4j.geometry.Mass;
import fr.rphstudio.ggj2k19.launcher.MainLauncher;

import org.newdawn.slick.geom.Vector2f;

import java.util.List;

/**
 * @author GRIGNON FAMILY
 */
public class ScriptEnemyIA implements IComponent, IScript {
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long id;
    private final String name;
    private final Mass resetMass;
    private Physic2D phyCmp = null;
    private float speed = 0;
    private float resetSpeed = 0;
    private List<Physic2D> targets;
    private FactoryProjectile factoryProjectile;
    private float time = 0;//ms
    private float timeForNextProjectile = 0;//ms
    private Entity generatorEnt=null;
    private boolean freezeMode=false;
    private boolean boss;


    //================================================
    // CONSTRUCTOR
    //================================================

    public ScriptEnemyIA(Physic2D phy, List<Physic2D> target, float spd,
                         FactoryProjectile factoryProjectile, Entity generator,boolean boss) {
        this.targets = target;
        this.factoryProjectile = factoryProjectile;
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = "ScriptEnemyIA";
        // Store Components
        this.phyCmp = phy;
        this.speed = spd;
        this.resetMass = phyCmp.getBody().getMass();
        this.resetSpeed = spd;
        this.generatorEnt = generator;
        this.boss=boss;
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
    public void update(int delta) {
        time+=delta;

        if (this.phyCmp != null) {
            if(freezeMode){
                this.phyCmp.getBody().setLinearVelocity(0,0);
            }
            Physic2D target=targets.get(0);
            float distance = Float.POSITIVE_INFINITY;

            Vector2f ownPosition = new Vector2f((float)this.phyCmp.getXPosition(),(float)this.phyCmp.getYPosition());
            for (Physic2D t : targets) {
                Vector2f targetPosition = new Vector2f((float)t.getXPosition(),(float)t.getYPosition());
                float dist = ownPosition.distance(targetPosition);
                Entity ent = (Entity)(t.getBody().getUserData());
                List<IComponent> listHP = ent.getComponent("HP");
                if(listHP.size() > 0)
                {
                    if( ((HP)(listHP.get(0))).getValue() > 0 )
                    {
                        if(dist < distance){
                            distance = dist;
                            target = t;
                        }    
                    }
                }
            }


            float dx = (float) (target.getXPosition() - this.phyCmp.getXPosition());
            float dy = (float) (target.getYPosition() - this.phyCmp.getYPosition());
            Vector2f targetPos = new Vector2f((float)target.getXPosition(),(float)target.getYPosition());
            Vector2f enemyPos  = new Vector2f((float)this.phyCmp.getXPosition(),(float)this.phyCmp.getYPosition());
            Vector2f direction = new Vector2f(dx,dy);
            Vector2f normalisedDirection = direction.normalise();
            float dist = targetPos.distance(enemyPos);
            
            if(dist >= Common.ENEMY_MOVE_MAX_DISTANCE)
            {
                this.phyCmp.setForce(normalisedDirection.x * this.speed,normalisedDirection.y * this.speed);
            }
            
            //*
            if(dist <= Common.ENEMY_SHOOT_MIN_DISTANCE && !freezeMode)
            {
                if(time>timeForNextProjectile) {
                    timeForNextProjectile = time + 2000;
                    float thetaRadians = (float) Math.atan2(normalisedDirection.y, normalisedDirection.x);
                    float size = 1f;
                    long lifeTime = Common.PROJECTILE_LIFETIME;
                    int  projType = this.boss?Common.PROJECTILE_TYPE_EARTH:Common.PROJECTILE_TYPE_FIRE; // TODO types
                    this.factoryProjectile.createProjectile(
                            new Vector2f((float) this.phyCmp.getXPosition() + (normalisedDirection.x * 0),
                            (float) this.phyCmp.getYPosition()+ (normalisedDirection.y * 0)),
                            Common.PROJECTILE_SPEED,
                            (float) Math.toDegrees(thetaRadians),
                            projType,
                            lifeTime,
                            this.generatorEnt,
                            Common.PROJECTILE_DMG,
                            Common.DAMAGE_OWNER_ENEMY );
                }
            }
            //*/
            
            // Saturate position on RIGHT and BOTTOM edges
            if(this.phyCmp.getXPosition() >= MainLauncher.WIDTH/Common.NB_PIXELS_PER_METER)
            {
                this.phyCmp.setXPosition(MainLauncher.WIDTH/Common.NB_PIXELS_PER_METER);
            }
            if(this.phyCmp.getYPosition() >= MainLauncher.HEIGHT/Common.NB_PIXELS_PER_METER)
            {
                this.phyCmp.setYPosition(MainLauncher.HEIGHT/Common.NB_PIXELS_PER_METER); 
            }
            
            
        }
    }

    public void setFreeze() {
        this.speed = 0.0f;
        freezeMode=true;
    }

    public void setSlow() {
        this.speed = this.speed/2.0f;
    }

    public void resetSpeed() {
        freezeMode=false;
        this.speed = this.resetSpeed;
    }

    public boolean isBoss() {
        return boss;
    }


    //================================================
    // END OF CLASS
    //================================================
}
