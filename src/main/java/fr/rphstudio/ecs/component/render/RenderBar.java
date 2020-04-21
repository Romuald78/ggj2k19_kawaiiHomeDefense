/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ecs.component.render;

import fr.rphstudio.ecs.component.common.Position;
import fr.rphstudio.ecs.core.utils.CoreUtils;
import fr.rphstudio.ecs.core.interf.IComponent;
import fr.rphstudio.ecs.core.interf.IRender;
import fr.rphstudio.ggj2k19.launcher.Common;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author GRIGNON FAMILY
 */
public class RenderBar implements IComponent, IRender
{
    //================================================
    // PRIVATE PROPERTIES
    //================================================
    //----------------------------
    // Personal properties
    //----------------------------
    private  long       id;
    private  String     name;
    //----------------------------
    // Process properties
    //----------------------------
    private float            zOrder;
    private boolean          isDebugDisplay;
    private int              W;
    private int              H;
    private float            percent;
    private Color            clr;
    private Position         pos;
    
    //================================================
    // CONSTRUCTOR
    //================================================
    public  RenderBar(int z, int w, int h, Position position)
    {   
        // Store name
        this.name = "BAR";
        // Get unique ID
        this.id = CoreUtils.getNewID();
        // Init current position
        this.pos = position;
        // Init Z position
        this.zOrder = z;
        // Init debug display
        this.isDebugDisplay = false;
        // Text and color
        this.W = w;
        this.H = h;
        this.clr = Color.white;
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
    public float getZOrder()
    {
        return this.zOrder;
    }
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
    {
        float rr = 0;
        float gg = 0;
        float bb = 0;
        if( this.percent <= 50 )
        {
            rr = 1;
            gg = this.percent/50;
        }
        else
        {
            gg = 1;
            rr = (100-this.percent)/50;
        }
        g.setColor(new Color(rr,gg,bb));
        g.fillRect( this.pos.getXPosition()*Common.NB_PIXELS_PER_METER-(this.W/2), this.pos.getYPosition()*Common.NB_PIXELS_PER_METER-(this.H/2), this.W*this.percent/100.0f, this.H);
      
        
        g.setColor(Color.black);
        g.drawRect( this.pos.getXPosition()*Common.NB_PIXELS_PER_METER-(this.W/2), this.pos.getYPosition()*Common.NB_PIXELS_PER_METER-(this.H/2), this.W, this.H);
        

    }
    
    
    
    //================================================
    // SETTERS
    //================================================
   
    public void setPosition(Vector2f newPos)
    {
    }
    public void setPosition(float x, float y)
    {
       this.pos = new Position();
       this.pos.setXPosition(x);
       this.pos.setYPosition(y);
    }
    public void setPosition(int eltIndex, Vector2f newPos)
    {
        throw new Error("[ERROR] setPosition method is not used in RenderText class !");
    }
    public void setZOrder(float z)
    {
        this.zOrder = z;
    }
    public void setColor(Color newClr)
    {
        this.clr = newClr;
    }
    public void setDebugDisplay(boolean dd)
    {
        this.isDebugDisplay = dd;
    }
    public void setPercent(float perc)
    {
        this.percent = perc;
    }
    public void setAngle(float ang)
    {
        
    }
    
    
    //================================================
    // GETTERS
    //================================================
    public Vector2f getPosition()
    {
        
        return null;
    }
    public float getPercent()
    {
        return this.percent;
    }
    public boolean isShown()
    {
        return true;
    }
    
    
    
    //================================================
    // END OF CLASS
    //================================================
}
