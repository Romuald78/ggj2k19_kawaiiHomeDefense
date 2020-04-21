/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.ecs.component.render.RenderFont;
import fr.rphstudio.ecs.component.render.font.CuteSpriteFont;
import fr.rphstudio.ggj2k19.game.PlayerInfo;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import fr.rphstudio.ecs.core.utils.ControllerAL;
import fr.rphstudio.ecs.core.utils.ControllerAL.Brands;
import fr.rphstudio.ecs.core.utils.ControllerAL.Buttons;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class State02Select extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 200;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame   gameObject;
    private GameContainer    container;
    
    private Image            background;
    private List<PlayerInfo> players;     
    private Sound            bip;
    private Image frame;
    private RenderFont renderFonta;
    
    //------------------------------------------------
    // PRIVATE METHODS (player selection)
    //------------------------------------------------
    private boolean isIUsedID(int id)
    {
        boolean result = false;
        for(PlayerInfo pi : this.players)
        {
            if(pi.getCharacterID() == id)
            {
                result = true;
                break;
            }
        }
        return result;
    }
    private void setNextID(int ctrlID)
    {
        this.setNextID(ctrlID, false);
    }
    private void setNextID(int ctrlID, boolean reverse)
    {
        PlayerInfo pi = this.getPlayer(ctrlID);
        int step = 1;
        if(reverse)
        {
            step = -1;
        }
        if(pi != null)
        {
            int id = pi.getCharacterID();
            for(int loop=1;loop<=Common.playerColors.length;loop++)
            {
                int charIndex = (id+(step*loop)+Common.playerColors.length)%Common.playerColors.length;
                if( !this.isIUsedID(charIndex) )
                {
                    pi.setNewCharacter(charIndex);
                    break;
                }
            }
        }
    }
    
    private boolean areAllPlayersReady()
    {
        for(PlayerInfo pi : this.players)
        {
            if(!pi.isReadyToStart())
            {
                return false;
            }
        }
        // All players are ready
        return true;
    }
    
    private PlayerInfo getPlayer(int ctrlID)
    {
        // look in the player list if the current controller already exists
        for(PlayerInfo pi : this.players)
        {
            if(pi.getControllerID() == ctrlID)
            {
                return pi;
            }
        }
        // the controller has not been found
        return null;
    }
    
    private boolean doesPlayerExist(int ctrlID)
    {
        return ( this.getPlayer(ctrlID) != null );
    }
    
    private void pressStart(int ctrlID)
    {
        // Play sound 
        this.bip.play();
        
        // Get player info according to the controller ID
        PlayerInfo pi = this.getPlayer(ctrlID);
            
        // add a player if it does not exist, else validate its character choice
        if(pi ==  null)
        {
            // Check there is some space for a new player
            // Player will be added only if it is possible,
            // else, the START button will have no effect
            if(this.players.size() < Common.playerColors.length)
            {
                // Create new player info
                pi = new PlayerInfo(ctrlID);
                // add object to player list
                this.players.add(pi);
                // Get available character id
                this.setNextID(ctrlID);
            }
        }
        else
        {
            // Check if this player is already ready or not
            if(pi.isReadyToStart())
            {
                // Check if all other registered players are ready to start to
                // If they are all ready, launch the game, else do nothing
                if( this.areAllPlayersReady() )
                {
                    // Go to game
                    this.goToGame();
                }
            }
            else
            {
                // the player is NOW ready to start
                pi.setReady();
            }
        }
    }
    
    private void pressBack(int ctrlID)
    {
        // Play sound 
        this.bip.play();
        
        // Get player info according to the controller ID
        PlayerInfo pi = this.getPlayer(ctrlID);
                
        // Check if this player is registered
        if(pi == null)
        {
            // player doe snot exist, back to start menu
            this.backToStartMenu();
        }
        else
        {
            // If this player is ready to start, remove this flag
            if(pi.isReadyToStart())
            {
                pi.setNotReady();
            }
            else
            {
                // this player is removed from the player list
                this.players.remove(pi);
            }
        }
    }
    
    
    //------------------------------------------------
    // PRIVATE METHODS (game states)
    //------------------------------------------------
    // Quit game
    private void backToStartMenu()
    {
        this.gameObject.enterState( State01Start.ID, new FadeOutTransition(Common.COLOR_FADE_IN, Common.TIME_FADE_IN), new FadeInTransition(Common.COLOR_FADE_OUT, Common.TIME_FADE_OUT) );
    }
    // Go to next menu 
    private void goToGame()
    {
        // Get Menu state
        GameState gs = null;
        // Init Menu state
        try
        {
            gs = new State03Game(this.players);
            this.gameObject.addState(gs);
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // enter Menu state
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
    public State02Select()
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
        this.background = ResourceLoader.getImage("SELECT");
        
        // init the sounds
        this.bip = ResourceLoader.getSound("SOUND_SELECT");
        
        this.frame = ResourceLoader.getImage("FRAME");
        
        // Init the player information list (if the list already exists, keep it, but sets all the ready flag to false)
        if(this.players == null)
        {
            this.players = new ArrayList<PlayerInfo>();
        }
        for(PlayerInfo pi : this.players)
        {
            pi.setNotReady();
        }
        
        float ratio = 0.35f;
        renderFonta = new RenderFont("", ResourceLoader.getImage("FONT_CUTE"), CuteSpriteFont.getCharInfo(), 1000, ratio, new Color(252*0.8f,224*0.8f,232*0.8f,0.8f) );
        
        
    }

        
    //------------------------------------------------
    // RENDER METHOD
    //------------------------------------------------
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        // Fit Screen
        MainLauncher.fitScreen(container, g);
        
        // Render Start screen background
        g.drawImage(this.background, 0, 0);
       g.setColor(new Color(0,0,0,0.1f));
       g.fillRect(0,0,1920,1080);
        
       Color arrowClr = new Color(1f,1f,0f,0.75f);
       
       g.drawImage(ResourceLoader.getImage("RULES"),0,0);
       
        renderFonta.setColor(new Color(0.2f,0,0,0.75f));
        renderFonta.setMessage("PRESS START TO JOIN / BACK TO LEAVE.");
        renderFonta.setPosition(200/Common.NB_PIXELS_PER_METER, 400/Common.NB_PIXELS_PER_METER); 
        renderFonta.render(container, game, g);
        
        
        
        // Draw all character sleection, according to the arrayList 'players'
        for(int i=0; i<this.players.size();i++)
        {
            PlayerInfo pi = this.players.get(i);
            int refY = 550;
            int refX = (1920 - this.players.size()*200 - (this.players.size()-1)*50)/2 + i*250 - 300-50;
            
            Color clr=null;
            if(pi.isReadyToStart())
            {
                clr = pi.getColor();
            }
            else
            {
                clr = new Color(0,0,0,0.5f);
            }
            
            
            Image pad;
            if(ControllerAL.getBrand(pi.getControllerID()) == Brands.GAMECUBE)
            {
                pad = ResourceLoader.getImage("ICON_PAD_GC");
            }
            else if(ControllerAL.getBrand(pi.getControllerID()) == Brands.XBOX)
            {
                pad = ResourceLoader.getImage("ICON_PAD_XBOX");
            }
            else
            {
                pad = ResourceLoader.getImage("ICON_KEYBOARD");
            }
            
            if( !pi.isReadyToStart() && this.players.size()<4 )
            {
                g.drawImage(ResourceLoader.getImage("ARROW"), refX+70, refY-74, arrowClr);
                g.drawImage(ResourceLoader.getImage("ARROW").getFlippedCopy(false, true), refX+70, refY+250,arrowClr);
            }
            
            
            g.drawImage(this.frame, refX, refY, clr);
            g.drawImage(ResourceLoader.getImage("PLAYER_"+pi.getCharacterID()).getScaledCopy(0.75f), refX+40, refY+20);
            g.setColor(clr);
            g.fillOval(refX, refY+240-64, 64, 64);
            g.drawImage( pad.getScaledCopy(64,64) ,refX, refY+240-64);
        }

        if(this.areAllPlayersReady() && this.players.size() > 0)
        {
            renderFonta.setMessage("ANY PLAYER PRESS START TO DEFEND HOME !");
            renderFonta.setPosition(150/Common.NB_PIXELS_PER_METER, 850/Common.NB_PIXELS_PER_METER); 
            renderFonta.render(container, game, g);
        }
        
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
            // BACK button management
            case Input.KEY_ESCAPE:
                this.pressBack(ControllerAL.KEYBOARD);
                break;
            // Start button management
            case Input.KEY_SPACE:
            case Input.KEY_ENTER:
            case Input.KEY_NUMPADENTER:
                //this.pressStart(ControllerAL.KEYBOARD);
                break;
            // LEFT and RIGHT management
            case Input.KEY_LEFT:
                this.setNextID(ControllerAL.KEYBOARD, true);
                break;
            case Input.KEY_RIGHT:
                this.setNextID(ControllerAL.KEYBOARD, false);
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
            this.pressBack(controller);
        }
        
        // START BUTTON MANAGEMENT
        if( ControllerAL.isButtonOK(Buttons.BUTTON_START, controller, button) )
        {
            this.pressStart(controller);
        }
    }
    @Override
    public void controllerUpPressed(int controller)
    {
        this.setNextID(controller,true);
    }
    @Override
    public void controllerDownPressed(int controller)
    {
        this.setNextID(controller);
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