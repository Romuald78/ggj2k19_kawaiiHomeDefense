/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.game;

import fr.rphstudio.ggj2k19.launcher.Common;
import org.newdawn.slick.Color;

/**
 *
 * @author GRIGNON FAMILY
 */
public class DotInfo {
    
    private int     dmgOwner;
    private int     dmgType;
    private float   dmgBase;
    private long    startTime;
    private long    duration;
    
    
    // Constructor
    public DotInfo(int own, int typ, float dmg, long dur)
    {
        this.dmgOwner  = own;
        this.dmgType   = typ;
        this.dmgBase   = dmg;
        this.duration  = dur;
        this.startTime = System.currentTimeMillis();
    }

    public int getOwner()
    {
        return this.dmgOwner;
    }
    public int getType()
    {
        return this.dmgType;
    }
    public float getDamage()
    {
        return this.dmgBase;
    }
    public long getDuration()
    {
        return this.duration;
    }
    public boolean isActive()
    {
        return (System.currentTimeMillis()-this.startTime <= duration);
    }

    
    
    
}
