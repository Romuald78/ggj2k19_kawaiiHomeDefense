/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.ecs.component.render.RenderFont;
import fr.rphstudio.ecs.component.render.font.ArialSpriteFont;
import fr.rphstudio.utils.Loaders.ResourceLoader;

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

public class State00IntroC extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 33000;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame gameObject;
    private GameContainer  container;

    // add background and font objects if needed
    private Image background;
    private long  timer;
    private RenderFont  font;
    
    
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
    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public State00IntroC()
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
        this.background = ResourceLoader.getImage("LOGO_RPH");
    
        // Load font component
        this.font = new RenderFont("RPH Studio presents", ResourceLoader.getImage("FONT_ARIAL"), ArialSpriteFont.getCharInfo(), 100.0f, 0.35f, new Color(1.0f, 1.0f, 1.0f, 1.0f) );
        
        // Store timer
        this.timer = System.currentTimeMillis();
    }

        
    //------------------------------------------------
    // RENDER METHOD
    //------------------------------------------------
    public void render(GameContainer contain, StateBasedGame game, Graphics g) throws SlickException
    {
        // Fit Screen
        MainLauncher.fitScreen(container, g);
        
        int refX = (MainLauncher.WIDTH -this.background.getWidth() )/2;
        int refY = (MainLauncher.HEIGHT-this.background.getHeight())/2;
                
        g.setColor(Color.white);
        g.drawImage(this.background, refX, refY);

        this.font.setPosition((refX+225)/Common.NB_PIXELS_PER_METER, (refY+this.background.getHeight())/Common.NB_PIXELS_PER_METER);
        this.font.render(contain, game, g);
        
    }

    
    //------------------------------------------------
    // UPDATE METHOD
    //------------------------------------------------
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        if( System.currentTimeMillis() - this.timer > Common.INTRO_PAGE_TIME_MS)
        {
            this.goToStartMenu();
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