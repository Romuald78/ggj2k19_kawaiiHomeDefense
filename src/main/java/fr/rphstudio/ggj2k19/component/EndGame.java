/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.component;

import fr.rphstudio.ecs.component.physic.Physic2D;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IScript;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ggj2k19.factory.FactoryProjectile;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author GRIGNON FAMILY
 */
public class EndGame implements IComponent, IScript {
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private final long id;
    private final String name;
    private HP hp;
    private Runnable endgame;


    //================================================
    // CONSTRUCTOR
    //================================================

    public EndGame(HP hp,Runnable endgame) {
        this.hp = hp;
        this.endgame = endgame;
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Store name
        this.name = "endgame";
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
    public void update(int delta) {
        if(hp.getValue()<=0){
            endgame.run();
        }
    }


    //================================================
    // END OF CLASS
    //================================================
}
