/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.scripts;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.component.control.AnalogPadHandler;
import fr.rphstudio.ecs.component.control.PadHandler;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.factory.FactoryProjectile;
import fr.rphstudio.ggj2k19.launcher.Common;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author GRIGNON FAMILY
 */
public class LaunchProjectile implements IComponent, IScript {
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long id;
    private final String name;
    private Position posCmp = null;
    private PadHandler padCmp = null;
    private AnalogPadHandler anlCmp = null;
    private FactoryProjectile facProj = null;
    private Entity generatorEnt = null;
    private HP hpCmp;

    float fireRatePerSecond = Common.FIRERATE_PER_SECOND;
    float timeBetweenProjectile = 1000 / fireRatePerSecond;
    float time = 0;
    float timeLast = 0;

    //================================================
    // CONSTRUCTOR
    //================================================
    public LaunchProjectile(Position pos, PadHandler pad, FactoryProjectile fp, AnalogPadHandler anl, Entity generator, HP hp) {
        this(pos, pad, fp, anl, generator, hp, "LaunchProjectile");
    }

    public LaunchProjectile(Position pos, PadHandler pad, FactoryProjectile fp, AnalogPadHandler anl, Entity generator, HP hp,String nam) {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = nam;
        // Store Components
        this.posCmp = pos;
        this.padCmp = pad;
        this.facProj = fp;
        this.anlCmp = anl;
        this.generatorEnt = generator;
        this.hpCmp = hp;
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
        time += delta;
        
        if (time >= timeLast + timeBetweenProjectile) {
        
            if (this.posCmp != null && this.padCmp != null && this.anlCmp != null) {
                timeLast = time;
                // prepare data
                float dx = this.anlCmp.getDirectionX();
                float dy = this.anlCmp.getDirectionY();
                float ang = (float) (Math.atan2(dy, dx));
                float modifAng = (float) ((-10 + Math.random() * 20) * Math.PI / 180.0f);
                ang += modifAng;
                Vector2f projPos = this.posCmp.getPosition();
                projPos.x += Math.cos(ang) * 0;
                projPos.y += Math.sin(ang) * 0;
                long lifeTime = Common.PROJECTILE_LIFETIME;

                
                if(this.hpCmp.getValue() > 0)
                {
                    // IS BUTTON ACTIVE Allow to shoot like a flame thrower !!!
                    if (this.padCmp.isButtonActive("B")) {
                        this.facProj.createProjectile(projPos,
                                Common.PROJECTILE_SPEED,
                                (float) (Math.toDegrees(ang)),
                                Common.PROJECTILE_TYPE_FIRE,
                                lifeTime,
                                this.generatorEnt,
                                Common.PROJECTILE_DMG,
                                Common.DAMAGE_OWNER_PLAYER);
                    } else if (this.padCmp.isButtonActive("X")) {
                        this.facProj.createProjectile(projPos,
                                Common.PROJECTILE_SPEED,
                                (float) (Math.toDegrees(ang)),
                                Common.PROJECTILE_TYPE_WATER,
                                lifeTime,
                                this.generatorEnt,
                                Common.PROJECTILE_DMG,
                                Common.DAMAGE_OWNER_PLAYER);
                    } else if (this.padCmp.isButtonActive("A")) {
                        this.facProj.createProjectile(projPos,
                                Common.PROJECTILE_SPEED,
                                (float) (Math.toDegrees(ang)),
                                Common.PROJECTILE_TYPE_EARTH,
                                lifeTime,
                                this.generatorEnt,
                                Common.PROJECTILE_DMG,
                                Common.DAMAGE_OWNER_PLAYER);
                    } else if (this.padCmp.isButtonActive("Y")) {
                        this.facProj.createProjectile(projPos,
                                Common.PROJECTILE_SPEED,
                                (float) (Math.toDegrees(ang)),
                                Common.PROJECTILE_TYPE_ICE,
                                lifeTime,
                                this.generatorEnt,
                                Common.PROJECTILE_DMG,
                                Common.DAMAGE_OWNER_PLAYER);
                    }
                }
            }
        }
    }


    //================================================
    // SETTERS
    //================================================


    //================================================
    // GETTERS
    //================================================


    //================================================
    // END OF CLASS
    //================================================
}
