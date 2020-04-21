/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.utils.Loaders.ResourceLoader;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class State00Loading extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 1;

    
    //------------------------------------------------
    // Resource class
    //------------------------------------------------
    public enum ResourceType
    {
        IMAGE,
        SOUND,
        MUSIC,
        SPRITESHEET,
    }
    private class ResourceInfo
    {
        private String       name;
        private String       path;
        private ResourceType type;
        private boolean      loaded;
        private int          param1;
        private int          param2;
        
        private ResourceInfo(String n, String p, ResourceType t, int p1, int p2)
        {
            this.name   = n;
            this.path   = p;
            this.type   = t;
            this.loaded = false;
            this.param1 = p1;
            this.param2 = p2;
        }
        private ResourceInfo(String n, String p, ResourceType t)
        {
            this(n, p, t, -1, -1);
        }
    }
    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame gameObject;
    private GameContainer  container;

    // flag to tell if we are loading resources or not
    private boolean isLoading;
    // Resource list
    private List<ResourceInfo> resources;
    
    
    //------------------------------------------------
    // PRIVATE METHODS
    //------------------------------------------------
    // Quit game
    private void quitGame()
    {
        this.container.exit();
    }
    // go to start menu
    private void goToIntro()
    {
        // Init game state
        GameState gs = this.gameObject.getState(State00IntroA.ID);
        try
        {
            if(gs == null)
            {
                gs = new State00IntroA();
                this.gameObject.addState(gs);
            }
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // enter menu
        this.gameObject.enterState( State00IntroA.ID, new FadeOutTransition(Color.black, Common.TIME_FADE_OUT2), new FadeInTransition(Color.black, Common.TIME_FADE_IN2) );
    } 
    // Get next resource name to be loaded
    private String getNextResourceName()
    {
        for (ResourceInfo ri : this.resources)
        {
            if(ri.loaded == false)
            {
                return ri.name;
            }
        }
        return "";
    }
    // Load next resource
    private void loadNextResource()
    {
        for (ResourceInfo ri : this.resources)
        {
            if(ri.loaded == false)
            {
                switch(ri.type)
                {
                    case IMAGE:
                        // Load picture into hashmap
                        ResourceLoader.addImage(ri.name, ri.path);
                        // Image has been loaded now
                        ri.loaded = true;
                        return;
                        
                    case MUSIC:
                        ResourceLoader.addMusic(ri.name, ri.path);
                        // Image has been loaded now
                        ri.loaded = true;
                        break;
                        
                    case SOUND:
                        ResourceLoader.addSound(ri.name, ri.path);
                        // Image has been loaded now
                        ri.loaded = true;
                        break;
    
                    case SPRITESHEET:
                        ResourceLoader.addSpriteSheet(ri.name, ri.path, ri.param1, ri.param2);
                        // sprite shset has been loaded now
                        ri.loaded = true;
                        break;
                }
            }
        }
    }
    private boolean allResourceLoaded()
    {
        for (ResourceInfo ri : this.resources)
        {
            if(ri.loaded == false)
            {
                return false;
            }
        }
        return true;
    }
    private int getLoadingPercent()
    {
        int total  = 0;
        int loaded = 0;
        for (ResourceInfo ri : this.resources)
        {
            if(ri.loaded)
            {
                loaded++;
            }
            total++;
        }
        if(total == 0)
        {
            total  = 1;
            loaded = 1;
        }
        return (100*loaded)/total;
    }

    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public State00Loading()
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
        
        // set the flag
        this.isLoading = true;
        // Init the resources to load
        this.resources = new ArrayList<ResourceInfo>();

        // Intro Menus
        this.resources.add( new ResourceInfo("LOGO_GGJ"  , "./sprites/backgrounds/ggj2k19.jpg", ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("LOGO_SLICK", "./sprites/backgrounds/slick.png"  , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("LOGO_RPH"  , "./sprites/backgrounds/rph_800.png", ResourceType.IMAGE) );
        
        // START MENU
        this.resources.add( new ResourceInfo("BACKGROUND_START" , "./sprites/backgrounds/start_screen.png", ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("RULES" , "./sprites/backgrounds/rules.png", ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("SELECT" , "./sprites/backgrounds/select.png", ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("MUSIC_START"      , "./music/start_music.ogg"               , ResourceType.MUSIC) );
        
        // SELECT MENU
        this.resources.add( new ResourceInfo("ICON_PAD_XBOX"    , "./sprites/icons/gamepad_xbox.png"      , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("ICON_PAD_GC"      , "./sprites/icons/gamepad_gamecube.png"  , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("ICON_KEYBOARD"    , "./sprites/icons/keyboard.png"          , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("FRAME"            , "./sprites/characters/frame.png"             , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("SOUND_SELECT"     , "./sfx/powerup.wav"                     , ResourceType.SOUND) );

        // GAME VIEW
        this.resources.add( new ResourceInfo("BACKGROUND_GAME"  , "./sprites/backgrounds/game.png"        , ResourceType.IMAGE) );
        
        this.resources.add( new ResourceInfo("PLAYER_0"         , "./sprites/characters/playerBlue.png"   , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("PLAYER_1"         , "./sprites/characters/playerRed.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("PLAYER_2"         , "./sprites/characters/playerGreen.png"  , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("PLAYER_3"         , "./sprites/characters/playerOrange.png" , ResourceType.IMAGE) );
        
        this.resources.add( new ResourceInfo("LEFT_0"           , "./sprites/characters/blueLeft.png"     , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("LEFT_1"           , "./sprites/characters/redLeft.png"      , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("LEFT_2"           , "./sprites/characters/greenLeft.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("LEFT_3"           , "./sprites/characters/orangeLeft.png"   , ResourceType.IMAGE) );
        
        this.resources.add( new ResourceInfo("RIGHT_0"          , "./sprites/characters/blueRight.png"     , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("RIGHT_1"          , "./sprites/characters/redRight.png"      , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("RIGHT_2"          , "./sprites/characters/greenRight.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("RIGHT_3"          , "./sprites/characters/orangeRight.png"   , ResourceType.IMAGE) );
        
        this.resources.add( new ResourceInfo("UP_0"             , "./sprites/characters/blueUp.png"        , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("UP_1"             , "./sprites/characters/redUp.png"         , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("UP_2"             , "./sprites/characters/greenUp.png"       , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("UP_3"             , "./sprites/characters/orangeUp.png"      , ResourceType.IMAGE) );
        
        this.resources.add( new ResourceInfo("TOMB_0"           , "./sprites/characters/blueTomb.png"      , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("TOMB_1"           , "./sprites/characters/redTomb.png"       , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("TOMB_2"           , "./sprites/characters/greenTomb.png"     , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("TOMB_3"           , "./sprites/characters/orangeTomb.png"    , ResourceType.IMAGE) );
        
        this.resources.add( new ResourceInfo("ENEMY"            , "./sprites/characters/enemyGray.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("FONT_SS_GAME"     , "./sprites/fonts/space_font.png"        , ResourceType.SPRITESHEET, 80, 80) );
        this.resources.add( new ResourceInfo("EARTH"            , "./sprites/characters/icon_earth.png"   , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("ICE"              , "./sprites/characters/icon_ice.png"     , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("WATER"            , "./sprites/characters/icon_water.png"   , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("FIRE"             , "./sprites/characters/icon_fire.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("FREEZE"           , "./sprites/characters/icon_freeze.png"  , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("LAVA"             , "./sprites/characters/icon_lava.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("HEAL"             , "./sprites/characters/icon_heal.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("HOME"             , "./sprites/backgrounds/home.png"    , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("ARROW"             , "./sprites/characters/arrow.png"    , ResourceType.IMAGE) );
        
        
        
        // PAUSE VIEW
        this.resources.add( new ResourceInfo("BACKGROUND_PAUSE" , "./sprites/backgrounds/pause_screen.jpg", ResourceType.IMAGE) );
        
        // END VIEW
        this.resources.add( new ResourceInfo("BACKGROUND_END"   , "./sprites/backgrounds/end_screen.jpg"  , ResourceType.IMAGE) );
        
        // FONT SPRITESHEETS
        this.resources.add( new ResourceInfo("FONT_CUTE"        , "./sprites/fonts/CuteSpriteFont.png"     , ResourceType.IMAGE) );
        this.resources.add( new ResourceInfo("FONT_ARIAL"       , "./sprites/fonts/ArialSpriteFont.png"     , ResourceType.IMAGE) );
        
    }

        
    //------------------------------------------------
    // RENDER METHOD
    //------------------------------------------------
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        // Fit Screen
        MainLauncher.fitScreen(container, g);
        
        // If we are in the pre loading phase
        if(this.isLoading)
        {
            int border = 50;
            int barW   = 499;
            int barH   = 49;
            int refX   = (MainLauncher.LOAD_WIDTH -barW-2*border)/2; // (MainLauncher.WIDTH -barW-2*border)/2;
            int refY   = (MainLauncher.LOAD_HEIGHT-barH-2*border)/2; // (MainLauncher.HEIGHT-barH-4*border)/2;
            int perc = this.getLoadingPercent();
            g.setColor(Color.white);
            String message = "Loading "+perc+"%";
            long dots = (System.currentTimeMillis()/250) % 4;
            if(dots>0)
            {
                message = message.concat(".");
            }
            if(dots>1)
            {
                message = message.concat(".");
            }
            if(dots>2)
            {
                message = message.concat(".");
            }
            g.drawRect(refX, refY, barW+2*border , barH+2*border);

            g.setColor(Color.gray);
            g.fillRect(refX+border, refY+border, (perc*barW)/100, barH);

            g.setColor(Color.white);
            g.drawString(message, refX+barW/2, refY+border+barH/3);
            g.drawRect(refX+border, refY+border, barW , barH);
            
            g.setColor(Color.gray);
            g.drawString(this.getNextResourceName().toLowerCase(), refX+barW/2, refY+1.25f*border+barH);
        }
    }

    
    //------------------------------------------------
    // UPDATE METHOD
    //------------------------------------------------
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        // Load next resource if there is
        this.loadNextResource();
        // check if this is the end of loading
        if(this.allResourceLoaded())
        {
            // go to next page
            this.goToIntro();
        }        
    }
    
    
    //------------------------------------------------
    // KEYBOARD METHODS
    //------------------------------------------------
    @Override
    public void keyPressed(int key, char c) 
    {
        if(key==Input.KEY_ESCAPE)
        {
            this.quitGame();
        }
    }

    
    //------------------------------------------------
    // CONTROLLER METHODS
    //------------------------------------------------
    @Override
    public void controllerButtonReleased(int controller, int button)
    {
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