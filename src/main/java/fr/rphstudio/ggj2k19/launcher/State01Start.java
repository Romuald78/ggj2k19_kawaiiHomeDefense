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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class State01Start extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 100;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame gameObject;
    private GameContainer  container;
    private String         version;

    // add background and font objects if needed
    private Image background;
    // Music
    private Music music;
    private RenderFont renderFont;
    private Color startClr;
    private long refTime = 0;
    
    
    //------------------------------------------------
    // PRIVATE METHODS
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

    // Get current program version string from file
    private void getVersion()
    {
        // Get display version
        BufferedReader br = null;
        try
        {
            this.version = "";
            br = new BufferedReader(new FileReader("info/version.txt"));
            String line;
            line = br.readLine();
            while(line != null)
            {
                this.version = this.version + line + "\n";
                line = br.readLine();
            }
            if (br != null)
            {
                br.close();
            }
        }
        catch (IOException e)
        {
            throw new Error(e);
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException ex)
            {
                throw new Error(ex);
            }
        }
    }
    // Quit game
    private void quitGame()
    {
        this.container.exit();
    }
    // Go to next menu 
    private void goToSelectMenu()
    {
        // Init game state
        GameState gs = this.gameObject.getState(State02Select.ID);
        try
        {
            if(gs == null)
            {
                gs = new State02Select();
                this.gameObject.addState(gs);
            }
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // Stop music
        this.music.stop();
        // enter select menu
        this.gameObject.enterState( State02Select.ID, new FadeOutTransition(Common.COLOR_FADE_IN, Common.TIME_FADE_IN), new FadeInTransition(Common.COLOR_FADE_OUT, Common.TIME_FADE_OUT) );
    } 
    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public State01Start()
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
        this.background = ResourceLoader.getImage("BACKGROUND_START");
        // Load the music
        this.music = ResourceLoader.getMusic("MUSIC_START");

        this.startClr = new Color(252,224,232);

        float ratio = 0.35f;
        renderFont = new RenderFont("", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, ratio, this.startClr);
        renderFont.setMessage("PRESS START");
        renderFont.setPosition(815/Common.NB_PIXELS_PER_METER, 990/Common.NB_PIXELS_PER_METER);        
        
        
        // Get version string
        this.getVersion();
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

        // renderfont
        this.renderFont.setColor(this.startClr);
        this.renderFont.render(container, game, g);
        
        // Render version number
        g.setColor(Color.black);
        g.drawString(this.version, 15, 8);
    }

    
    //------------------------------------------------
    // UPDATE METHOD
    //------------------------------------------------
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        if(!this.music.playing())
        {
            this.music.loop();
        }
        
        this.refTime += delta;
        if( this.refTime >= 500)
        {
            this.refTime = 0;
            if(this.startClr.r >= 0.5f)
            {
                this.startClr = new Color(252/4,224/4,232/4);
            }
            else
            {
                this.startClr = new Color(252*0.8f,224*0.8f,232*0.8f);
            }  
        }
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
                this.quitGame();
                break;
            // go to selection game by pressing SPACE or ENTER
            case Input.KEY_ENTER:
            case Input.KEY_SPACE:
            case Input.KEY_NUMPADENTER:
                this.goToSelectMenu();
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
    public void controllerButtonReleased(int controller, int button)
    {
        // go to selection game, if START button is pressed
        if( ControllerAL.isButtonOK(Buttons.BUTTON_START, controller, button) )
        {
            this.goToSelectMenu();
        }

        // Exit game, if BACK button is pressed
        if( ControllerAL.isButtonOK(Buttons.BUTTON_BACK, controller, button) )
        {
            this.quitGame();
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