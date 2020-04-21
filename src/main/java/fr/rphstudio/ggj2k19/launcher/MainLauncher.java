//--------------------------------------------------------------------
// IMPORTS
//--------------------------------------------------------------------
package fr.rphstudio.ggj2k19.launcher;

import fr.rphstudio.ecs.core.utils.ControllerAL;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


//--------------------------------------------------------------------
// MAIN CLASS
//--------------------------------------------------------------------

/**
 * This is the main class for this Object-Oriented Programming course.
 * This application uses the Slick2D Java Library for the user interface.
 * /!\ DO NOT MODIFY THIS CLASS /!\
 * @author Romuald GRIGNON
 */
public class MainLauncher extends StateBasedGame
{
    //--------------------------------------------------------------------
    // CONSTANTS
    //--------------------------------------------------------------------
    public static final int LOAD_WIDTH  = 600;
    public static final int LOAD_HEIGHT = 150;
    
    /**
     * Width of the graphic work area.
     */
    public static final int WIDTH  = 1920;

    /**
     * Height of the graphic work area.
     */
    public static final int HEIGHT = 1080;
    
    /**
     * Ratio used when the computer resolution is not HD (1920x1080)
     */
    public static float RATIO_XY = 1.0f;
    
    /**
     * X-axis Offset used when the computer resolution is not HD (1920x1080)
     */
    public static float OFFSET_X = 0.0f;
    
    /**
     * Y-axis Offset used when the computer resolution is not HD (1920x1080)
     */
    public static float OFFSET_Y = 0.0f;
    
    //--------------------------------------------------------------------
    // PRIVATE PROPERTIES
    //--------------------------------------------------------------------
    private static boolean isTestParticles;
    
    
    //--------------------------------------------------------------------
    // STATIC METHODS
    //--------------------------------------------------------------------

    /**
     * This method will resize the graphic work area to fit in the computer screen.
     * The HD ratio is maintained. If the computer screen has not the same ratio, the displayed area is centered.
     * @param container the game container containing a lot of system information.
     * @param g the graphical object used to access the graphic work area.
     */
    public static void fitScreen(GameContainer container, Graphics g)
    {
        // rendering is done : now try to scale it to fit the full screen
        float sx  = ((AppGameContainer)container).getScreenWidth()/(float)WIDTH;
        float sy  = ((AppGameContainer)container).getScreenHeight()/(float)HEIGHT;
        MainLauncher.RATIO_XY = Math.min(sx,sy); 
        MainLauncher.OFFSET_X = (((AppGameContainer)container).getScreenWidth() -(WIDTH*MainLauncher.RATIO_XY))/2;
        MainLauncher.OFFSET_Y = (((AppGameContainer)container).getScreenHeight()-(HEIGHT*MainLauncher.RATIO_XY))/2;
        // Scale and translate to the center of screen
        g.scale(MainLauncher.RATIO_XY, MainLauncher.RATIO_XY);
        g.translate(MainLauncher.OFFSET_X, MainLauncher.OFFSET_Y);
        // Set black color for the background
        g.setBackground(Color.black);
    }
    
    /**
     * Main method for this class. This is the entry point of our application.
     * @param args parameters of applications.
     * @throws SlickException Exception from the Slick2D library.
     */
    public static void main(String[] args) throws SlickException
    {
        // Particle testing or Main application selection
        MainLauncher.isTestParticles = false;
        if(args.length > 0)
        {
            if(args[0].equals("-testParticles"))
            {
                MainLauncher.isTestParticles = true;
            }
        }        
        
        // creating application
        AppGameContainer appGame = new AppGameContainer(new MainLauncher());
        
        // Set always rendering and always updating, even if not visible
        appGame.setUpdateOnlyWhenVisible(false);
        appGame.setAlwaysRender(true);

        // Remove or Display FPS
        appGame.setShowFPS(false);
        
        // Set icons
        String[] icons = { "icon32x32.png", "icon16x16.png"};
        appGame.setIcons(icons);

        // Vsync
        appGame.setVSync(true);
        
        // Window size
        //appGame.setDisplayMode(appGame.getScreenWidth(), appGame.getScreenHeight(), true);      // Full game HD
        appGame.setDisplayMode(MainLauncher.LOAD_WIDTH, MainLauncher.LOAD_HEIGHT, false);       // windowed application when loading
        
        // Start application        
        appGame.start();
    }  

    
    //--------------------------------------------------------------------
    // CONSTRUCTOR
    //--------------------------------------------------------------------
    
    /**
     * Constructor of our main class. This is just used to set the window title of our application.
     */
    public MainLauncher()
    {
        super("RPH Studio - Global Game Jam 2019");
    }
     
    
    //--------------------------------------------------------------------
    // INTERFACE METHODS
    //--------------------------------------------------------------------

    /**
     * Method called at start. It is used to register all the game execution controllers.
     * We have only one here, the 'ChessGameController'.
     * @param container the game container containing a lot of system information.
     * @throws SlickException Exception from the Slick2D library.
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        // Init the controller abstract layer
        ControllerAL.initControllers();
        
        // Add game state controller (according to executable mode)
        if(MainLauncher.isTestParticles)
        {
            this.addState( new StateParticles() );
        }
        else
        {
            this.addState( new State00Loading() );
        }
    }

    
    //--------------------------------------------------------------------
    // END OF CLASS
    //--------------------------------------------------------------------
}
