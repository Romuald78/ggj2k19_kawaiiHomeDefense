/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ecs.component.script;

import fr.rphstudio.ecs.component.control.AnalogPadHandler;
import fr.rphstudio.ecs.component.control.KeyboardHandler;
import fr.rphstudio.ecs.component.render.RenderAnimations;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;

/**
 *
 * @author GRIGNON FAMILY
 */
public class ScriptKeyboardRender implements IComponent, IScript
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long       id;
    private final String     name;
    private RenderAnimations renCmp = null;
    private KeyboardHandler  keyCmp = null;
    
    
    //================================================
    // CONSTRUCTOR
    //================================================

    public ScriptKeyboardRender(RenderAnimations ren, KeyboardHandler key)
    {
        this(ren, key, "ScriptAnalogPhysic");
    }
    public ScriptKeyboardRender(RenderAnimations ren, KeyboardHandler key, String nam)
    {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = nam;
        // Store Components
        this.renCmp = ren;
        this.keyCmp = key;
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
        if(this.renCmp != null && this.keyCmp != null)
        {
            float dx = 0;
            float dy = 0;
            if( this.keyCmp.isKeyActive("LEFT") )
            {
                dx -= 1;
            }
            if( this.keyCmp.isKeyActive("RIGHT") )
            {
                dx += 1;
            }
            if( this.keyCmp.isKeyActive("UP") )
            {
                dy -= 1;
            }
            if( this.keyCmp.isKeyActive("DOWN") )
            {
                dy += 1;
            }
            
            // Check if vertical or horizontal
            if( Math.abs(dy) > Math.abs(dx) )
            {
                //VERTICAL
                if(dy>0)
                {
                    this.renCmp.selectAnimation("WALK_DOWN");
                }
                else
                {
                    this.renCmp.selectAnimation("WALK_UP");
                }
            }
            else
            {
                // HORIZONTAL
                if(dx>0)
                {
                    this.renCmp.selectAnimation("WALK_RIGHT");
                }
                else
                {
                    this.renCmp.selectAnimation("WALK_LEFT");
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
