/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.component;

import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ggj2k19.launcher.Common;

/**
 *
 * @author GRIGNON FAMILY
 */
public class Boss implements IComponent
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    private final long     id;
    private final String   name;


    //================================================
    // CONSTRUCTOR
    //================================================
    public Boss()
    {
        // Store name
        this.name = "BOSS";
        // Get unique ID
        this.id = CoreUtils.getNewID();
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

}
