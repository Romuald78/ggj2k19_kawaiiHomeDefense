/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.ecs.component.render.RenderFont;
import fr.rphstudio.ecs.component.render.font.CuteSpriteFont;
import fr.rphstudio.ggj2k19.game.PlayerInfo;
import fr.rphstudio.ggj2k19.game.WaveGeneration;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import fr.rphstudio.ecs.core.utils.ControllerAL;
import fr.rphstudio.ecs.core.utils.ControllerAL.Buttons;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import static fr.rphstudio.ecs.component.render.font.InmyheadSpriteFont.getCharInfo;

public class State05End extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 500;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame   gameObject;
    private GameContainer    container;
    
    private Image            background;
    private List<PlayerInfo> players;
    private RenderFont renderFontb;
    private RenderFont renderFonta;


    //------------------------------------------------
    // PRIVATE METHODS (game states)
    //------------------------------------------------
    // Quit game
    // Quit game
    private void backToStartMenu()
    {
        this.gameObject.enterState( State01Start.ID, new FadeOutTransition(Common.COLOR_FADE_IN, Common.TIME_FADE_IN), new FadeInTransition(Common.COLOR_FADE_OUT, Common.TIME_FADE_OUT) );
    }
    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public State05End(List<PlayerInfo> playerInfo)
    {
        this.players = playerInfo;
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

        // render wave counter

        float ratio = 128.0f / 80.0f;
        renderFonta = new RenderFont("", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, 1.0f, new Color(252*0.8f,224*0.8f,131*0.8f,0.85f) );
        renderFonta.setColor(new Color(0.2f,0,0,0.75f));
        renderFonta.setMessage("GAME OVER");
        renderFonta.setPosition(8.5f, 4.5f);

        ratio = 128.0f / 80.0f;
        renderFontb = new RenderFont("", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, 1.0f, new Color(252*0.8f,224*0.8f,131*0.8f,0.85f) );
        renderFontb.setColor(new Color(0.2f,0,0,0.75f));
        renderFontb.setMessage("SCORE: WAVE "+WaveGeneration.lastScoreWave);
        renderFontb.setPosition(7f, 8.5f);
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

        renderFonta.render(container,game,g);
        renderFontb.render(container,game,g);

        // DEBUG DISPLAY
        //g.drawString("END SCREEN ("+this+")", 900, 500);

        // DEBUG : draw all player information coming from selection menu
        /*for(int i=0; i<this.players.size();i++)
        {
            PlayerInfo pi = this.players.get(i);
            g.drawString("Player #"+(i+1)+" - Controller #"+pi.getControllerID()+" - charId = "+pi.getCharacterID()+" - Color = "+pi.getColor()+" - Ready flag "+pi.isReadyToStart(), 800, 550+30*i);
        }*/

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
            // Quit game by pressing escape
            case Input.KEY_SPACE:
            case Input.KEY_ENTER:
            case Input.KEY_NUMPADENTER:
            case Input.KEY_ESCAPE:
                this.backToStartMenu();
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
        if( ControllerAL.isButtonOK(Buttons.BUTTON_START, controller, button) )
        {
            this.backToStartMenu();
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