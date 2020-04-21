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
public class Damage implements IComponent
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    private final long     id;
    private final String   name;
    private       float dmgValue;
    private int      dmgOwner;
    private int      dmgType;


    //================================================
    // CONSTRUCTOR
    //================================================
    public Damage(int typ, int own)
    {
        // Store name
        this.name = "DMG";
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Init position
        this.dmgValue = 0;
        // store source of damage
        this.dmgOwner = own;
        this.dmgType = typ;
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
    public void setDmgValue(float newValue)
    {
        this.dmgValue = newValue;
    }
    public void increaseValue(float dV)
    {
        this.dmgValue += dV;
    }
    
    
    
    //================================================
    // GETTERS
    //================================================
    public float getDmgValue()
    {
        return this.dmgValue;
    }

    public int getDmgOwner()
    {
        return this.dmgOwner;
    }
    
    public int getDmgType()
    {
        return this.dmgType;
    }
    
   

    //================================================
    // END OF CLASS
    //================================================
}
