/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ecs.component.script;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.component.render.RenderAnimations;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IRender;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ggj2k19.launcher.Common;

/**
 *
 * @author GRIGNON FAMILY
 */
public class SetRenderPosition implements IComponent, IScript
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long   id;
    private final String name;
    private Position     posCmp = null;
    
    private IRender      renCmp = null;
    private float        dX     = 0;
    private float        dY     = 0;
    
    
    
    //================================================
    // CONSTRUCTOR
    //================================================
    private void init(Position pos, IRender ren, float offsetX, float offsetY)
    {
    }
    public SetRenderPosition(Position pos, IRender ren, float offsetX, float offsetY )
    {
        this(pos, ren, offsetX, offsetY, "ScriptMoveRenderToPosition");
    }
    public SetRenderPosition(Position pos, IRender ren, float offsetX, float offsetY, String nam)
    {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = nam;
        // Store Components
        this.posCmp = pos;
        this.renCmp = ren;
        // Store display offset
        this.dX     = offsetX;
        this.dY     = offsetY;
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
        if(this.posCmp != null && this.renCmp != null)
        {
            this.renCmp.setPosition( (float)(this.posCmp.getXPosition()*Common.NB_PIXELS_PER_METER)+this.dX,
                                     (float)(this.posCmp.getYPosition()*Common.NB_PIXELS_PER_METER)+this.dY );
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
