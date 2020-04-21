/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.ecs.component.render.RenderFont;
import fr.rphstudio.ggj2k19.factory.*;
import fr.rphstudio.ggj2k19.game.PlayerInfo;
import fr.rphstudio.ecs.core.ECSWorld;
import fr.rphstudio.ecs.core.Entity;
import fr.rphstudio.ecs.core.utils.ControllerAL;
import fr.rphstudio.ecs.core.utils.ControllerAL.Buttons;
import fr.rphstudio.ggj2k19.game.WaveGeneration;
import fr.rphstudio.ggj2k19.scripts.CollisionProjectile;
import fr.rphstudio.utils.Loaders.ResourceLoader;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class State03Game extends BasicGameState
{   
    //------------------------------------------------
    // PUBLIC CONSTANTS
    //------------------------------------------------
    public static final int ID = 300;

    
    //------------------------------------------------
    // PRIVATE PROPERTIES
    //------------------------------------------------
    private StateBasedGame   gameObject;
    private GameContainer    container;
    
    private Image            background;
    private List<PlayerInfo> players;     
    
    private ECSWorld          world;
    private WaveGeneration    waveGeneration;
    private FactoryProjectile factoryProjectile;
    private HomeFactory       homeFactory;



    //------------------------------------------------
    // PRIVATE METHODS (game states)
    //------------------------------------------------
    // Quit game
    private void pauseGame()
    {
        // Init game state
        GameState gs = this.gameObject.getState(State04Pause.ID);
        try
        {
            if(gs == null)
            {
                gs = new State04Pause();
                this.gameObject.addState(gs);
            }
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // enter select menu
        this.gameObject.enterState( State04Pause.ID, new FadeOutTransition(Common.COLOR_FADE_IN, Common.TIME_FADE_IN), new FadeInTransition(Common.COLOR_FADE_OUT, Common.TIME_FADE_OUT) );
    }
    // finish game
    private void finishGame()
    {
        // Init game state
        GameState gs = null;
        try
        {
            gs = new State05End(this.players);
            this.gameObject.addState(gs);
            gs.init(this.container, this.gameObject);
        }
        catch(Exception e)
        {
            throw new Error(e);
        };
        // enter select menu
        this.gameObject.enterState( State05End.ID, new FadeOutTransition(Common.COLOR_FADE_IN, Common.TIME_FADE_IN), new FadeInTransition(Common.COLOR_FADE_OUT, Common.TIME_FADE_OUT) );
    }
    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    public State03Game(List<PlayerInfo> playerInfo)
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
 
        // Instanciate World
        this.world = new ECSWorld("World1");
        
        // Add particle system
        Entity dummy = new Entity(this.world);
        dummy.addComponent(CollisionProjectile.particles);
        this.world.addEntity(dummy);
        
        
        // Clear the pause screen
        try
        {
            GameState gs = new State04Pause();
            this.gameObject.addState(gs);
            gs.init(this.container, this.gameObject);
        }
        catch (SlickException se)
        {
            throw new Error(se);
        }


        DotFactory dotFactory = new DotFactory(world);
        factoryProjectile = new FactoryProjectile(this.world);
        homeFactory = new HomeFactory(this.world,dotFactory);

        List<Entity> enemyTarget=new ArrayList<>();
        final Entity entityHome = homeFactory.create(() -> {
            this.finishGame();
        },dotFactory);
        enemyTarget.add(entityHome);

        //========================================================
        // CREATE ALL PLAYERS
        //========================================================
        for(PlayerInfo pi : this.players)
        {
            // Create player
            Entity ent02 = FactoryPlayer.createPlayer(pi, this.world,dotFactory);
            // Add entity to the world
            this.world.addEntity(ent02);
            enemyTarget.add(ent02);
            
          
        }

        //========================================================
        // Wave Generation
        //========================================================
        EnemyFactory enemyFactory = new EnemyFactory(this.world,factoryProjectile,dotFactory);
        waveGeneration = new WaveGeneration(
                enemyFactory,
                new Vector2f(15,10),
                15,
                10,
                enemyTarget,
                (RenderFont)entityHome.getComponent(RenderFont.class).get(0),
                (RenderFont)entityHome.getComponent(RenderFont.class).get(1)
        );
        
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

        // DEBUG DISPLAY
        //g.drawString("GAME VIEW ("+this+")", 900, 500);
        
        // DEBUG : draw all player information coming from selection menu
        /*for(int i=0; i<this.players.size();i++)
        {
            PlayerInfo pi = this.players.get(i);
            g.drawString("Player #"+(i+1)+" - Controller #"+pi.getControllerID()+" - charId = "+pi.getCharacterID()+" - Color = "+pi.getColor()+" - Ready flag "+pi.isReadyToStart(), 800, 550+30*i);
        }*/

        // Call world render method
        this.world.render(container, game, g);
        
    }

    
    //------------------------------------------------
    // UPDATE METHOD
    //------------------------------------------------
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        //waves
        waveGeneration.update(delta);

        // Clear graphics
        container.getGraphics().clear();

        // Call all script components and execute them
        this.world.update(container, game, delta);
    }
    
    
    //------------------------------------------------
    // KEYBOARD METHODS
    //------------------------------------------------
    @Override
    public void keyPressed(int key, char c)
    {
        // Call World Key Method
        this.world.keyPressed(key);

        // Process game workflow
        switch(key)
        {
            // Quit game by pressing escape
            case Input.KEY_ESCAPE:
                this.pauseGame();
                break;
    
            // DEBUG TEST (to remove in final delivery)
            case Input.KEY_X:
                this.finishGame();
                break;
            
            case Input.KEY_F:
                this.container.setShowFPS( !this.container.isShowingFPS() );
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
    }

    
    //------------------------------------------------
    // CONTROLLER METHODS
    //------------------------------------------------
    @Override
    public void controllerButtonPressed(int controller, int button)
    {
        // Call World PAD Button method
        this.world.controllerButtonPressed(controller, button);

        // BACK button management (game workflow)
        if( ControllerAL.isButtonOK(Buttons.BUTTON_BACK, controller, button) )
        {
            this.pauseGame();
        }
    }
    @Override
    public void controllerButtonReleased(int controller, int button)
    {   
        // Call World PAD Button method
        this.world.controllerButtonReleased(controller, button);
    }
    @Override
    public void controllerLeftPressed(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionPressed(controller, ECSWorld.PAD_DIR_LEFT);
    }
    @Override
    public void controllerLeftReleased(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionReleased(controller, ECSWorld.PAD_DIR_LEFT);
    }    
    @Override
    public void controllerRightPressed(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionPressed(controller, ECSWorld.PAD_DIR_RIGHT);
    }
    @Override
    public void controllerRightReleased(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionReleased(controller, ECSWorld.PAD_DIR_RIGHT);
    }    
    @Override
    public void controllerUpPressed(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionPressed(controller, ECSWorld.PAD_DIR_UP);
    }
    @Override
    public void controllerUpReleased(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionReleased(controller, ECSWorld.PAD_DIR_UP);
    }    
    @Override
    public void controllerDownPressed(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionPressed(controller, ECSWorld.PAD_DIR_DOWN);
    }
    @Override
    public void controllerDownReleased(int controller)
    {
        // Call World PAD Direction method
        this.world.controllerDirectionReleased(controller, ECSWorld.PAD_DIR_DOWN);
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