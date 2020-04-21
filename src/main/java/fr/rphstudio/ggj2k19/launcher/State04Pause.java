/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.ecs.component.render.RenderFont;
import fr.rphstudio.ecs.component.render.font.CuteSpriteFont;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import fr.rphstudio.ecs.core.utils.ControllerAL;
import fr.rphstudio.ecs.core.utils.ControllerAL.Buttons;

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

public class State04Pause extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 400;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame   gameObject;
    private GameContainer    container;
    
    private Image            background;
    private RenderFont renderFonta;
    private RenderFont renderFontb;
    private RenderFont renderFontc;
    
    
    //------------------------------------------------
    // PRIVATE METHODS (game states)
    //------------------------------------------------
    private void backToSelectMenu()
    {
        // Init game state
        GameState gs = this.gameObject.getState(State02Select.ID);
        try
        {
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // enter select menu
        this.gameObject.enterState( State02Select.ID, new FadeOutTransition(Common.COLOR_FADE_IN, Common.TIME_FADE_IN), new FadeInTransition(Common.COLOR_FADE_OUT, Common.TIME_FADE_OUT) );
    }
    
    private void backToGame()
    {
        this.gameObject.enterState( State03Game.ID, new FadeOutTransition(Common.COLOR_FADE_IN, Common.TIME_FADE_IN), new FadeInTransition(Common.COLOR_FADE_OUT, Common.TIME_FADE_OUT) );
    } 
    
    
    //------------------------------------------------
    // PRIVATE METHODS (screen)
    //------------------------------------------------
    // toggle full/windowed screen
    private void toggleFullScreen()
    {
        try
        {
            this.container.setFullscreen(!this.container.isFullscreen());
        }
        catch(SlickException se)
        {}
    }

    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public State04Pause()
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
    
        // Init the background image
        this.background = ResourceLoader.getImage("BACKGROUND_GAME");  
        
        float ratio = 0.35f;
        renderFonta = new RenderFont("", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, ratio, new Color(252*0.8f,224*0.8f,232*0.8f,0.8f) );
        renderFonta.setColor(new Color(0.2f,0,0,0.75f));
        renderFonta.setMessage("PAUSED GAME.");
        renderFonta.setPosition(700/Common.NB_PIXELS_PER_METER, 400/Common.NB_PIXELS_PER_METER);  
    
        renderFontb = new RenderFont("", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, ratio, new Color(252*0.8f,224*0.8f,232*0.8f,0.8f) );
        renderFontb.setColor(new Color(0.2f,0,0,0.75f));
        renderFontb.setMessage("Press any key to resume,");
        renderFontb.setPosition(700/Common.NB_PIXELS_PER_METER, 500/Common.NB_PIXELS_PER_METER);  
    
        renderFontc = new RenderFont("", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, ratio, new Color(252*0.8f,224*0.8f,232*0.8f,0.8f) );
        renderFontc.setColor(new Color(0.2f,0,0,0.75f));
        renderFontc.setMessage("or BACK to exit.");
        renderFontc.setPosition(700/Common.NB_PIXELS_PER_METER, 600/Common.NB_PIXELS_PER_METER);  
    
    }

        
    //------------------------------------------------
    // RENDER METHOD
    //------------------------------------------------
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        // Fit Screen
        MainLauncher.fitScreen(container, g);
        
        // Render Start screen background
        g.setColor(Color.white);
        g.drawImage(this.background, 0, 0);

        g.setColor(new Color(0,0,0,0.25f));
        g.fillRect(0, 0, 1920, 1080);

        this.renderFonta.render(container, game, g);
        this.renderFontb.render(container, game, g);
        this.renderFontc.render(container, game, g);


    }

    
    //------------------------------------------------
    // UPDATE METHOD
    //------------------------------------------------
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {   

    }
    
    
    //------------------------------------------------
    // KEYBOARD METHODS
    //------------------------------------------------
    @Override
    public void keyPressed(int key, char c)
    {
        switch(key)
        {
            // toggle windowed/full screen
            case Input.KEY_F11:
                this.toggleFullScreen();
                break;
            // Quit game by pressing escape
            case Input.KEY_ESCAPE:
                this.backToSelectMenu();
                break;
            // Resume game
            case Input.KEY_SPACE:
            case Input.KEY_ENTER:
            case Input.KEY_NUMPADENTER:
                this.backToGame();
                break;
            // all other keys have no effect
            default :     
                break;        
        }
    }

    
    //------------------------------------------------
    // CONTROLLER METHODS
    //------------------------------------------------
    @Override
    public void controllerButtonPressed(int controller, int button)
    {
        // BACK button management
        if( ControllerAL.isButtonOK(Buttons.BUTTON_BACK, controller, button) )
        {
            this.backToSelectMenu();
        }
        
        // START BUTTON MANAGEMENT
        if( ControllerAL.isButtonOK(Buttons.BUTTON_START, controller, button) )
        {
            this.backToGame();
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