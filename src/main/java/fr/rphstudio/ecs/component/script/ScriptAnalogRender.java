/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ecs.component.script;

import fr.rphstudio.ecs.component.control.AnalogPadHandler;
import fr.rphstudio.ecs.component.render.RenderAnimations;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ggj2k19.component.HP;

/**
 *
 * @author GRIGNON FAMILY
 */
public class ScriptAnalogRender implements IComponent, IScript
{ 
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long       id;
    private final String     name;
    private RenderAnimations  renCmp = null;
    private AnalogPadHandler padCmp = null;
    private HP hpCmp;
    
    //================================================
    // CONSTRUCTOR
    //================================================

    public ScriptAnalogRender(RenderAnimations ren, AnalogPadHandler pad, HP hp)
    {
        this(ren, pad, hp,"ScriptAnalogPhysic");
    }
    public ScriptAnalogRender(RenderAnimations ren, AnalogPadHandler pad, HP hp, String nam)
    {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = nam;
        // Store Components
        this.renCmp = ren;
        this.padCmp = pad;
        this.hpCmp = hp;
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
        if(this.renCmp != null && this.padCmp != null)
        {
            float dx = this.padCmp.getDirectionX();
            float dy = this.padCmp.getDirectionY();
            if(Math.abs(dx)<0.1f)
            {
                dx = 0;
            }
            if(Math.abs(dy)<0.1f)
            {
                dy = 0;
            }
            
            if(this.hpCmp.getValue() > 0)
            {
                // Check if vertical or horizontal
                if( Math.abs(dy) > Math.abs(dx))
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
            else
            {
                this.renCmp.selectAnimation("DEATH");
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
