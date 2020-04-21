/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.scripts;

import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ggj2k19.component.Damage;
import fr.rphstudio.ggj2k19.component.HP;
import fr.rphstudio.ggj2k19.component.Lifetime;
import java.util.List;
import org.dyn4j.dynamics.Body;

/**
 * @author GRIGNON FAMILY
 */
public class DeathEnemy implements IComponent, IScript {
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long   id;
    private final String name;
    private Entity       ownerEntity = null;
    private HP           hp          = null;

    
    //================================================
    // CONSTRUCTOR
    //================================================

    public DeathEnemy(Entity ent, HP health) {
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = "DeathEnemy";
        // Store Components
        this.ownerEntity = ent;
        this.hp          = health;
    }


    //================================================
    // INTERFACE METHODS
    //================================================
    @Override
    public long getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void update(int delta)
    {
        if (this.ownerEntity != null && this.hp !=null)
        {
            if(this.hp.getValue() <= 0)
            {
                if( !this.ownerEntity.getDestroyRequest() )
                {
                    this.ownerEntity.setDestroyRequest(2);
                }
            }
        }
    }
    

    //================================================
    // END OF CLASS
    //================================================
}
