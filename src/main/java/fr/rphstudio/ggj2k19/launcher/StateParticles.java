/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.ecs.component.render.RenderParticles;
import fr.rphstudio.ecs.core.ECSWorld;
import fr.rphstudio.ecs.core.Entity;
import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateParticles extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 555;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame  gameObject;
    private GameContainer   container;
   
    private ECSWorld        world;
    private Entity          entity;
    private RenderParticles particles;
    private Vector2f        initPos;
    private int             move;
    
    
    //------------------------------------------------
    // PRIVATE METHODS
    //------------------------------------------------
    // Quit game
    private void quitGame()
    {
        this.container.exit();
    }
    // toggle full/windowed screen
    private void toggleFullScreen()
    {
        try
        {
            this.container.setFullscreen(!this.container.isFullscreen());
        }
        catch(SlickException se)
        {
            throw new Error(se);
        }
    }
    // Start the particle generator
    private void factoryParticles()
    {
        // Destroy entity if needed
        if(this.entity != null)
        {
            this.entity.setDestroyRequest();
        }
        // Create entity
        this.entity = new Entity(this.world);
        // Create particle component
        try
        {
            this.particles = new RenderParticles(100, "./sprites/particles/testParticle.png", 1000, ParticleSystem.BLEND_COMBINE, true);
        }
        catch(SlickException se){throw new Error(se);}
        // Add component to entity
        this.entity.addComponent(this.particles);
        // Add entity into the world
        this.world.addEntity(this.entity);
    }
    
    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public StateParticles()
    {
    }
    
    
    //------------------------------------------------
    // INIT METHOD
    //------------------------------------------------
    public void init(GameContainer container, StateBasedGame sbGame) throws SlickException
    {
        // Init fields
        this.container  = container;
        this.gameObject = sbGame;        
        
        // Switch to full screen before launching the game
        try
        {
            AppGameContainer agc = (AppGameContainer)(this.container);
            if(!agc.isFullscreen())
            {
                agc.setDisplayMode(this.container.getScreenWidth(), this.container.getScreenHeight(), true);      // Full game HD
            }
        }
        catch(SlickException se)
        {
        }
        
        // Instanciate World
        this.world = new ECSWorld("World1");
    
        // Init pos
        this.initPos = new Vector2f(MainLauncher.WIDTH/2, MainLauncher.HEIGHT/2);
        this.move    = 0;
        
        // Create particle component
        this.factoryParticles();
    }

        
    //------------------------------------------------
    // RENDER METHOD
    //------------------------------------------------
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        // Fit Screen
        MainLauncher.fitScreen(container, g);

        // Draw init pos
        g.setColor(Color.white);
        g.drawLine(this.initPos.x-3, this.initPos.y, this.initPos.x+3, this.initPos.y);
        g.drawLine(this.initPos.x, this.initPos.y-3, this.initPos.x, this.initPos.y+3);
        
        // Call world render method
        this.world.render(container, game, g);
    }

    
    //------------------------------------------------
    // UPDATE METHOD
    //------------------------------------------------
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        // Clear graphics
        container.getGraphics().clear();

        // Call all script components and execute them
        this.world.update(container, game, delta);

        // Update particle position
        if((this.move & 0x01) == 0x01)
        {
            this.initPos.x-=5;
        }
        if((this.move & 0x02) == 0x02)
        {
            this.initPos.x+=5;
        }
        if((this.move & 0x04) == 0x04)
        {
            this.initPos.y-=5;
        }
        if((this.move & 0x08) == 0x08)
        {
            this.initPos.y+=5;
        }
    }
    
    
    //------------------------------------------------
    // KEYBOARD METHODS
    //------------------------------------------------
    @Override
    public void keyPressed(int key, char c) 
    {
        // Call World Key Method
        this.world.keyPressed(key);
        
        switch(key)
        {
            // toggle windowed/full screen
            case Input.KEY_F11:
                this.toggleFullScreen();
                break;
            // Quit game by pressing escape
            case Input.KEY_ESCAPE:
                this.quitGame();
                break;
            // Add an emitter
            case Input.KEY_SPACE:
                try
                {
                    this.particles.addEmitter("./sprites/particles/testEmitter.xml", new Vector2f(MainLauncher.WIDTH/2,MainLauncher.HEIGHT/2));
                    this.particles.setPosition(this.particles.getNbEmitters()-1, this.initPos);
                }
                catch(IOException ioe){throw new Error(ioe);}
                break;
            // remove all emitters
            case Input.KEY_BACK:
            case Input.KEY_DELETE:
                this.particles.removeAllEmitters();
                break;
            // Move particle system position
            case Input.KEY_LEFT:
                this.move |= 0x01;
                break;
            case Input.KEY_RIGHT:
                this.move |= 0x02;
                break;
            case Input.KEY_UP:
                this.move |= 0x04;
                break;
            case Input.KEY_DOWN:
                this.move |= 0x08;
                break;
            // all other keys have no effect
            default :     
                break;        
        }
    }
    @Override
    public void keyReleased(int key, char c)
    {
        // Call World Key Method
        this.world.keyReleased(key);

        switch(key)
        {
            // Move particle system position
            case Input.KEY_LEFT:
                this.move &= ~0x01;
                break;
            case Input.KEY_RIGHT:
                this.move &= ~0x02;
                break;
            case Input.KEY_UP:
                this.move &= ~0x04;
                break;
            case Input.KEY_DOWN:
                this.move &= ~0x08;
                break;
            // all other keys have no effect
            default :     
                break;        
        }
    }

    
    //------------------------------------------------
    // STATE ID METHOD
    //------------------------------------------------
    @Override
    public int getID()
    {
          return this.ID;
    }
    
    
    //------------------------------------------------
    // END OF STATE
    //------------------------------------------------
}