/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.utils.Loaders.ResourceLoader;
import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class State00IntroA extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 11000;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame gameObject;
    private GameContainer  container;

    // add background and font objects if needed
    private Image background;
    private long  timer;
    
    

    // Quit game
    private void quitGame()
    {
        this.container.exit();
    }
    // go to start menu
    private void goToStartMenu()
    {
        // Init game state
        GameState gs = this.gameObject.getState(State01Start.ID);
        try
        {
            if(gs == null)
            {
                gs = new State01Start();
                this.gameObject.addState(gs);
            }
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // enter menu
        this.gameObject.enterState( State01Start.ID, new FadeOutTransition(Color.black, Common.TIME_FADE_OUT), new FadeInTransition(Color.black, Common.TIME_FADE_IN) );
    }     
    private void goToNextMenu()
    {
        // Init game state
        GameState gs = this.gameObject.getState(State00IntroB.ID);
        try
        {
            if(gs == null)
            {
                gs = new State00IntroB();
                this.gameObject.addState(gs);
            }
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // enter menu
        this.gameObject.enterState( State00IntroB.ID, new FadeOutTransition(Color.black, Common.TIME_FADE_OUT2), new FadeInTransition(Color.black, Common.TIME_FADE_IN2) );
    } 
    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public State00IntroA()
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

        // Load the background for this gamestate
        this.background = ResourceLoader.getImage("LOGO_GGJ");
        // Store timer
        this.timer = System.currentTimeMillis();
    }

        
    //------------------------------------------------
    // RENDER METHOD
    //------------------------------------------------
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
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

        
        // Fit Screen
        MainLauncher.fitScreen(container, g);
        
        g.setColor(Color.white);
        g.drawImage(this.background, 0, 0);
    
        // g.drawString("a game developped in less than 48 hours...", 780, 875);
        
    }

    
    //------------------------------------------------
    // UPDATE METHOD
    //------------------------------------------------
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        if( System.currentTimeMillis() - this.timer > Common.INTRO_PAGE_TIME_MS)
        {
            this.goToNextMenu();
        }
    }
    
    
    //------------------------------------------------
    // KEYBOARD METHODS
    //------------------------------------------------
    @Override
    public void keyPressed(int key, char c) 
    {
        if(key == Input.KEY_ESCAPE)
        {
            this.quitGame();
        }
        else
        {
            this.goToStartMenu();
        }
    }

    
    //------------------------------------------------
    // CONTROLLER METHODS
    //------------------------------------------------
    @Override
    public void controllerButtonReleased(int controller, int button)
    {
        this.goToStartMenu();
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