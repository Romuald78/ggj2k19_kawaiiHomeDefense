/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.scripts;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.component.render.RenderBar;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ggj2k19.component.HP;

/**
 *
 * @author GRIGNON FAMILY
 */
public class LifeToBar implements IComponent, IScript
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long       id;
    private final String     name;
    //----------------------------
    // Process properties
    //----------------------------
    private HP     hp;
    private RenderBar rb;
    
    
    //================================================
    // CONSTRUCTOR
    //================================================
    public LifeToBar(HP health, RenderBar render)
    {
        // Store name
        this.name = "L2B";
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Common process
        this.hp = health;
        this.rb = render;
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
       if(this.rb != null && this.hp != null)
       {
           this.rb.setPercent( 100*this.hp.getValue()/this.hp.getMaxValue() );
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
