/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.game;

import fr.rphstudio.ecs.component.render.RenderAnimations;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ggj2k19.component.Boss;
import fr.rphstudio.ggj2k19.component.Damage;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.ggj2k19.scripts.ScriptEnemyIA;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author GRIGNON FAMILY
 */
public class DotManager implements IComponent, IScript {
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long id;
    private final String name;

    private HP hp;
    private int owner;
    private ArrayList<DotInfo> dotList;
    private ScriptEnemyIA scriptEnemyIA;
    private RenderAnimations animEarth;
    private RenderAnimations animFire;
    private RenderAnimations animWater;
    private RenderAnimations animLava;
    private RenderAnimations animHeal;
    private RenderAnimations animFreeze;
    private RenderAnimations animSlow;


    private void cleanDots()
    {
        boolean loop = true;
        while(loop)
        {
            loop = false;
            for(DotInfo di : this.dotList)
            {
                if(!di.isActive())
                {
                    this.dotList.remove(di);
                    loop = true;
                    break;
                }
            }
        }
    }
    

    //================================================
    // CONSTRUCTOR
    //================================================

    public DotManager(ScriptEnemyIA scriptEnemyIA, HP health, int own,
                      RenderAnimations raEarth,
                      RenderAnimations raFire,
                      RenderAnimations raWater,
                      RenderAnimations raSlow,
                      RenderAnimations raLava,
                      RenderAnimations raHeal,
                      RenderAnimations raFreeze
    ) {
        this.scriptEnemyIA = scriptEnemyIA;
        // Store anims
        this.animEarth  = raEarth;
        this.animFire   = raFire;
        this.animWater  = raWater;
        this.animLava   = raLava;
        this.animHeal   = raHeal;
        this.animFreeze = raFreeze;
        this.animSlow   = raSlow;
        
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = "DOT";
        // Store Components
        this.hp = health;
        this.dotList = new ArrayList<DotInfo>();
        this.owner = own;
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

    
    public void addDotInfo(int own, int typ, float dmg, long dur)
    {
        this.dotList.add( new DotInfo(own,typ,dmg,dur) );
    }
    
    
    
    @Override
    public void update(int delta) {
        
        // First clean the old dotinfo
        this.cleanDots();
        // Then check the max dmg for each dmgtype
        float maxDmgPerType[] = {0,0,0,0};
        int finalOwn = this.owner;

        //clear visual type
        this.animFire.hide();
        this.animWater.hide();
        this.animSlow.hide();
        this.animEarth.hide();
        this.animLava.hide();
        this.animHeal.hide();
        this.animFreeze.hide();

        //================================ CANCEL ================================
        maxDmgPerType[Common.PROJECTILE_TYPE_EARTH] = 0;
        maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]  = 0;
        maxDmgPerType[Common.PROJECTILE_TYPE_ICE]   = 0;
        maxDmgPerType[Common.PROJECTILE_TYPE_WATER] = 0;
        for(DotInfo di : this.dotList)
        {
            int idx = di.getType();
            int own = di.getOwner();
            if(this.owner != own)
            {
                finalOwn = own;
                maxDmgPerType[idx] = Math.max(di.getDamage()*Common.DOT_COEF*delta, maxDmgPerType[idx]);
            }
        }
        
        // Check if we have FIRE + ICE (to remove)
        if( maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]>0 && maxDmgPerType[Common.PROJECTILE_TYPE_ICE]>0 )
        {
            //System.out.println("Remove FIRE+ICE");
            // Remove all fire and all ice
            Iterator<DotInfo> iter = this.dotList.iterator();
            while( iter.hasNext() )
            {
                DotInfo dot = iter.next();
                if( (dot.getType() == Common.PROJECTILE_TYPE_FIRE) || (dot.getType() == Common.PROJECTILE_TYPE_ICE) )
                {
                    iter.remove();
                }
            }
        }
        
        // Check if we have FIRE + WATER (to remove)
        if( maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]>0 && maxDmgPerType[Common.PROJECTILE_TYPE_WATER]>0 )
        {
            //System.out.println("Remove FIRE+WATER");
            // Remove all fire and all ice
            Iterator<DotInfo> iter = this.dotList.iterator();
            while( iter.hasNext() )
            {
                DotInfo dot = iter.next();
                if( (dot.getType() == Common.PROJECTILE_TYPE_FIRE) || (dot.getType() == Common.PROJECTILE_TYPE_WATER) )
                {
                    iter.remove();
                }
            }
        }   
        
        // Check if we have EARTH + ICE (to remove)
        if( maxDmgPerType[Common.PROJECTILE_TYPE_ICE]>0 && maxDmgPerType[Common.PROJECTILE_TYPE_EARTH]>0 )
        {
            //System.out.println("Remove ICE+EARTH");
            // Remove all fire and all ice
            Iterator<DotInfo> iter = this.dotList.iterator();
            while( iter.hasNext() )
            {
                DotInfo dot = iter.next();
                if( (dot.getType() == Common.PROJECTILE_TYPE_ICE) || (dot.getType() == Common.PROJECTILE_TYPE_EARTH) )
                {
                    iter.remove();
                }
            }
        }   
        
        
        //================================ MEGA DPS ================================                
        maxDmgPerType[Common.PROJECTILE_TYPE_EARTH] = 0;
        maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]  = 0;
        maxDmgPerType[Common.PROJECTILE_TYPE_ICE]   = 0;
        maxDmgPerType[Common.PROJECTILE_TYPE_WATER] = 0;
        for(DotInfo di : this.dotList)
        {
            int idx = di.getType();
            int own = di.getOwner();
            if(this.owner != own)
            {
                finalOwn = own;
                maxDmgPerType[idx] = Math.max(di.getDamage()*Common.DOT_COEF*delta, maxDmgPerType[idx]);
            }
        }

        if(this.scriptEnemyIA!=null) {
            this.scriptEnemyIA.resetSpeed();
        }

        if( maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]>0 && maxDmgPerType[Common.PROJECTILE_TYPE_EARTH]>0 )
        {
            // DAMAGE ENEMY
            this.hp.increaseValue( (-maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]-maxDmgPerType[Common.PROJECTILE_TYPE_EARTH])*Common.CUMUL_COEF   , finalOwn);
            // Show LAVA
            this.animLava.show();
        }
        else if( maxDmgPerType[Common.PROJECTILE_TYPE_WATER]>0 && maxDmgPerType[Common.PROJECTILE_TYPE_EARTH]>0 )
        {
            // HEAL ENEMY
            this.hp.increaseValue( (+maxDmgPerType[Common.PROJECTILE_TYPE_WATER]+maxDmgPerType[Common.PROJECTILE_TYPE_EARTH])*Common.CUMUL_COEF   , finalOwn);    
            // SHOW HEAL
            this.animHeal.show();
        }
        else if( maxDmgPerType[Common.PROJECTILE_TYPE_WATER]>0 && maxDmgPerType[Common.PROJECTILE_TYPE_ICE]>0 )
        {
            if(this.scriptEnemyIA!=null) {
                this.scriptEnemyIA.setFreeze();
            }
        
            // SHOW FREEZE
            this.animFreeze.show();
            //TODO if boss and boss is freeze more damage

            this.hp.increaseValue( -(maxDmgPerType[Common.PROJECTILE_TYPE_WATER]+maxDmgPerType[Common.PROJECTILE_TYPE_ICE]), finalOwn);

            if(scriptEnemyIA != null)
            {
                boolean boss = scriptEnemyIA.isBoss();
                if(boss){
                    this.hp.increaseValue( (-maxDmgPerType[Common.PROJECTILE_TYPE_WATER]-maxDmgPerType[Common.PROJECTILE_TYPE_ICE])*2, finalOwn);
                }
            }
        }
        else
        {
            // unitary dot
            if( maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]>0 )
            {
                // DMG FIRE
                this.hp.increaseValue( -maxDmgPerType[Common.PROJECTILE_TYPE_FIRE]   , finalOwn);
                // SHOW FIRE
                this.animFire.show();
            }
            if( maxDmgPerType[Common.PROJECTILE_TYPE_WATER]>0 )
            {
                // DMG WATER
                this.hp.increaseValue( -maxDmgPerType[Common.PROJECTILE_TYPE_WATER]   , finalOwn);
                // SHOW WATER
                this.animWater.show();
            }
            if( maxDmgPerType[Common.PROJECTILE_TYPE_ICE]>0 )
            {
                // SHOW ICE
                this.animSlow.show();
                if(this.scriptEnemyIA!=null) {
                    this.scriptEnemyIA.setSlow();
                }
            }
            if( maxDmgPerType[Common.PROJECTILE_TYPE_EARTH]>0 )
            {
                // SHOW EARTH
                this.animEarth.show();
                // DMG EARTH
                this.hp.increaseValue( -maxDmgPerType[Common.PROJECTILE_TYPE_EARTH]   , finalOwn);
            }
        }
        
        if(this.hp.getValue() <= 0)
        {
            //clear visual type
            this.animFire.hide();
            this.animWater.hide();
            this.animSlow.hide();
            this.animEarth.hide();
            this.animLava.hide();
            this.animHeal.hide();
            this.animFreeze.hide();
        }
        
    }
    
    


    //================================================
    // END OF CLASS
    //================================================
}
