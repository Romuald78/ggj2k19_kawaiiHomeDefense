/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ecs.component.script;

import fr.rphstudio.ecs.component.control.AnalogPadHandler;
import fr.rphstudio.ecs.component.control.PadHandler;
import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.launcher.Common;
import fr.rphstudio.ggj2k19.launcher.MainLauncher;

/**
 *
 * @author GRIGNON FAMILY
 */
public class ScriptAnalogPhysic implements IComponent, IScript
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
    private AnalogPadHandler padCmp = null;
    private PadHandler       butCmp = null;
    private float            speed  = 0;
    private boolean isZOK;
    private HP hpCmp    ;

    
    //================================================
    // CONSTRUCTOR
    //================================================

    public ScriptAnalogPhysic(Physic2D phy, AnalogPadHandler pad, float spd, PadHandler but, HP hp)
    {
        this(phy, pad, spd, but, hp, "ScriptAnalogPhysic");
    }
    public ScriptAnalogPhysic(Physic2D phy, AnalogPadHandler pad, float spd, PadHandler but, HP hp, String nam)
    {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = nam;
        // Store Components
        this.phyCmp = phy;
        this.padCmp = pad;
        this.butCmp = but;
        this.speed  = spd;
        this.isZOK  = true;
        this.hpCmp  = hp;
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
        if(this.phyCmp != null && this.padCmp != null && this.hpCmp != null)
        {
            
            if(this.hpCmp.getValue() > 0)
            {
                float dx = this.padCmp.getDirectionX();
                float dy = this.padCmp.getDirectionY();
                if(Math.abs(dx)<0.25f)
                {
                    dx = 0;
                }
                if(Math.abs(dy)<0.25f)
                {
                    dy = 0;
                }

                if(this.padCmp.getRotationZ() != -1)
                {
                    isZOK = false;
                }
                if( !this.butCmp.isButtonActive("L") && !this.butCmp.isButtonActive("R") && (Math.abs(this.padCmp.getRotationZ())<=0.5 || isZOK) )
                {
                    this.phyCmp.setForce( dx*this.speed , dy*this.speed );
                }
            }
            else
            {
                this.phyCmp.getBody().setLinearVelocity(0, 0);
            }
            // Saturate position in the screen
            this.phyCmp.setXPosition( (float)Math.max(0,this.phyCmp.getXPosition()) );
            this.phyCmp.setXPosition( (float)Math.min(MainLauncher.WIDTH/Common.NB_PIXELS_PER_METER,this.phyCmp.getXPosition()) );
            this.phyCmp.setYPosition( (float)Math.max(0,this.phyCmp.getYPosition()) );
            this.phyCmp.setYPosition( (float)Math.min(MainLauncher.HEIGHT/Common.NB_PIXELS_PER_METER,this.phyCmp.getYPosition()) );

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
