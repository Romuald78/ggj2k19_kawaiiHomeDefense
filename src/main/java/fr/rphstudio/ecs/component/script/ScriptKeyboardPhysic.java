/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ecs.component.script;

import fr.rphstudio.ecs.component.control.AnalogPadHandler;
import fr.rphstudio.ecs.component.control.KeyboardHandler;
import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;

/**
 *
 * @author GRIGNON FAMILY
 */
public class ScriptKeyboardPhysic implements IComponent, IScript
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long       id;
    private final String     name;
    private Physic2D         phyCmp = null;
    private KeyboardHandler  keyCmp = null;
    private float            speed  = 0;
    
    
    //================================================
    // CONSTRUCTOR
    //================================================

    public ScriptKeyboardPhysic(Physic2D phy, KeyboardHandler key, float spd)
    {
        this(phy, key, spd, "ScriptAnalogPhysic");
    }
    public ScriptKeyboardPhysic(Physic2D phy, KeyboardHandler key, float spd, String nam)
    {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = nam;
        // Store Components
        this.phyCmp = phy;
        this.keyCmp = key;
        this.speed  = spd;
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
    @Override
    public void update(int delta)
    {
        if(this.phyCmp != null && this.keyCmp != null)
        {
            float dx = 0;
            float dy = 0;
            if( this.keyCmp.isKeyActive("LEFT") )
            {
                dx = -1;
            }
            if( this.keyCmp.isKeyActive("RIGHT") )
            {
                dx = 1;
            }
            if( this.keyCmp.isKeyActive("UP") )
            {
                dy = -1;
            }
            if( this.keyCmp.isKeyActive("DOWN") )
            {
                dy = 1;
            }
            this.phyCmp.setForce( dx*this.speed , dy*this.speed );
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
