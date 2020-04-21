/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.component;

import fr.rphstudio.ecs.component.common.*;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ggj2k19.launcher.Common;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author GRIGNON FAMILY
 */
public class HP implements IComponent
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    private final long     id;
    private final String   name;
    private       float    value;
    private float          maxValue;
    private int            hpOwner;
    
    
    //================================================
    // CONSTRUCTOR
    //================================================
    public HP(int own)
    {
        // Store name
        this.name = "HP";
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Init position
        this.value = 0;
        this.hpOwner = own;
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
    public void setValue(float newValue)
    {
        this.value = newValue;
        this.maxValue = newValue;
    }
    public void increaseValue(float dV, int dmgOwner)
    {
        //enemy do not damage each other in any way
        if(!(Common.DAMAGE_OWNER_ENEMY == dmgOwner && hpOwner==Common.DAMAGE_OWNER_ENEMY))
        {
            this.value = Math.min( this.value+dV, this.maxValue);
            this.value = Math.max( this.value+dV, 0            );
        }
    }
    
    
    
    //================================================
    // GETTERS
    //================================================
    public float getValue()
    {
        return this.value;
    }
    public float getMaxValue()
    {
        return this.maxValue;
    }
    
    
    
    
    //================================================
    // END OF CLASS
    //================================================
}
