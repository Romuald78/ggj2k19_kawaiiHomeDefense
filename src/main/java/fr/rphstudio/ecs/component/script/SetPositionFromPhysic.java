/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ecs.component.script;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IRender;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ecs.core.utils.CoreUtils;

/**
 *
 * @author GRIGNON FAMILY
 */
public class SetPositionFromPhysic implements IComponent, IScript
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long       id;
    private final String     name;
    private Position         posCmp  = null;
    private Physic2D         phyCmp  = null;
    private int              bodyRef = 0;
    private float            relativeAngle;


    //================================================
    // CONSTRUCTOR
    //================================================
    public SetPositionFromPhysic(Position pos, Physic2D phy, float relAng, int bdyRf)
    {
        this(pos, phy, relAng, bdyRf , "SetPositionAndRotationFromPhysic");
    }
    public SetPositionFromPhysic(Position pos, Physic2D phy, float relAng, int bdyRf, String nam)
    {
        // Store name
        this.name = nam;
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store components
        this.posCmp  = pos;
        this.phyCmp  = phy;
        this.bodyRef = bdyRf;
        this.relativeAngle = relAng;    
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
        // Update position from physic component
        if(this.posCmp != null && this.phyCmp != null)
        {
            // Set position
            this.posCmp.setXPosition( (float)this.phyCmp.getXPosition(this.bodyRef) );
            this.posCmp.setYPosition( (float)this.phyCmp.getYPosition(this.bodyRef) );
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
