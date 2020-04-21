/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.component;

import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.contact.ContactPoint;
import org.newdawn.slick.SlickException;

/**
 *
 * @author GRIGNON FAMILY
 */
public class Lifetime implements IComponent
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    private final long     id;
    private final String   name;
    private long           maxDuration;
    private long           startTime;


    //================================================
    // CONSTRUCTOR
    //================================================
    public Lifetime(long maxDur)
    {
        // Store name
        this.name = "LIFETIME";
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // store duration
        this.setLifeTimeValue(maxDur);
    } 
    
    
    //================================================
    // INTERFACE METHODS
    //================================================
    @Override
    public long getID()
    {
        return this.id;
    }
    @Override
    public String getName()
    {
        return this.name;
    }
    
    
    //================================================
    // SETTERS
    //================================================
    public void setLifeTimeValue(long newValue)
    {
        this.maxDuration = newValue;
        this.startTime   = System.currentTimeMillis();
    }
    
    
    
    //================================================
    // GETTERS
    //================================================
    public long getMaxDuration()
    {
        return this.maxDuration;
    }
    public long getRemainingDuration()
    {
        long curTime = System.currentTimeMillis();
        long remain  = this.maxDuration-(curTime-this.startTime);
        if(remain <= 0)
        {
            remain = 0;
        }
        return remain;
    }


    //================================================
    // END OF CLASS
    //================================================
}
