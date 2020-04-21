//--------------------------------------------------------------------
// IMPORTS
//--------------------------------------------------------------------
package fr.rphstudio.ecs.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.input.Controllers;


//--------------------------------------------------------------------
// CLASS
//--------------------------------------------------------------------

/**
 * 
 * @author Romuald GRIGNON
 */
public class ControllerAL
{
    // Value of controller for keyboard
    public static final int KEYBOARD = -10000;
    
    //--------------------------------------------------------------------
    // PROPERTIES
    //--------------------------------------------------------------------
    public enum Brands
    {
        KEYBOARD,
        XBOX,
        GAMECUBE,
    }
    
    public enum Buttons
    {
        BUTTON_A,
        BUTTON_B,
        BUTTON_X,
        BUTTON_Y,
        BUTTON_L1,
        BUTTON_R1,
        BUTTON_START,
        BUTTON_BACK,
        
    };

    public enum Axis
    {
        AXIS_X,
        AXIS_Y,
        ROTATE_X,
        ROTATE_Y,
        ROTATE_Z1, // How to handle the difference between Xbox and Gamecube ? gamecube uses two axes for ROTATE_Z where xbox uses only one axis (two physical, merged internally)
        ROTATE_Z2, // How to handle the difference between Xbox and Gamecube ? gamecube uses two axes for ROTATE_Z where xbox uses only one axis (two physical, merged internally)
    }
    
    
    // button configuration types
    private static HashMap<Buttons, Integer> buttons_xbox       = new HashMap<Buttons, Integer>();
    private static HashMap<Buttons, Integer> buttons_gamecube   = new HashMap<Buttons, Integer>();
    private static HashMap<Buttons, Integer> buttons_snesusb    = new HashMap<Buttons, Integer>();
    // button controller configurations
    private static ArrayList<HashMap<Buttons, Integer>> buttons = new ArrayList<HashMap<Buttons, Integer>>();
    
    // axis configuration types
    private static HashMap<Axis, Integer> axis_xbox       = new HashMap<Axis, Integer>();
    private static HashMap<Axis, Integer> axis_gamecube   = new HashMap<Axis, Integer>();    
    private static HashMap<Axis, Integer> axis_snesusb    = new HashMap<Axis, Integer>();    
    // axis controller configurations
    private static ArrayList<HashMap<Axis, Integer>> axis = new ArrayList<HashMap<Axis, Integer>>();

    // Z Axis ratio
    private static ArrayList<Float> zAxisRatio = new ArrayList<Float>();
    

    //--------------------------------------------------------------------
    // METHODS
    //--------------------------------------------------------------------
    public static void initControllers()
    {
        // Init Button configuration for xbox
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_A    , 1 );        
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_B    , 2 );        
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_X    , 3 );        
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_Y    , 4 );        
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_L1   , 5 );        
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_R1   , 6 );        
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_BACK , 7 );        
        ControllerAL.buttons_xbox.put(Buttons.BUTTON_START, 8 );
        // Init axis configuration for xbox
        ControllerAL.axis_xbox.put(Axis.AXIS_X   ,  1);
        ControllerAL.axis_xbox.put(Axis.AXIS_Y   ,  0);
        ControllerAL.axis_xbox.put(Axis.ROTATE_X ,  3);
        ControllerAL.axis_xbox.put(Axis.ROTATE_Y ,  2);
        ControllerAL.axis_xbox.put(Axis.ROTATE_Z1,  4);
        ControllerAL.axis_xbox.put(Axis.ROTATE_Z2, -1);
        
        // Init Button config for gamecube
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_A    , 2 );
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_B    , 3 );
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_X    , 1 );
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_Y    , 4 );
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_L1   , 5 );
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_R1   , 6 );
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_BACK , 8 );
        ControllerAL.buttons_gamecube.put(Buttons.BUTTON_START, 10);
        // Init axis configuration for gamecube
        ControllerAL.axis_gamecube.put(Axis.AXIS_X   , 3);
        ControllerAL.axis_gamecube.put(Axis.AXIS_Y   , 2);
        ControllerAL.axis_gamecube.put(Axis.ROTATE_X , 0);
        ControllerAL.axis_gamecube.put(Axis.ROTATE_Y , 1);
        ControllerAL.axis_gamecube.put(Axis.ROTATE_Z1, 5);
        ControllerAL.axis_gamecube.put(Axis.ROTATE_Z2, 4);

        // Init Button configuration for SNES classic (USB)
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_A    , 2 );
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_B    , 3 );
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_X    , 1 );
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_Y    , 4 );
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_L1   , 5 );
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_R1   , 6 );
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_BACK , 9 );
        ControllerAL.buttons_snesusb.put(Buttons.BUTTON_START, 10);
        // Init axis configuration for xbox
        ControllerAL.axis_snesusb.put(Axis.AXIS_X   ,  1);
        ControllerAL.axis_snesusb.put(Axis.AXIS_Y   ,  0);
        ControllerAL.axis_snesusb.put(Axis.ROTATE_X , -1);
        ControllerAL.axis_snesusb.put(Axis.ROTATE_Y , -1);
        ControllerAL.axis_snesusb.put(Axis.ROTATE_Z1, -1);
        ControllerAL.axis_snesusb.put(Axis.ROTATE_Z2, -1);
        
        // Look for each controller and store information
        for (int i=0; i<Controllers.getControllerCount(); i++)
        {
            // Get controller name
            String nam = Controllers.getController(i).getName().toUpperCase();
            int nbButt = Controllers.getController(i).getButtonCount();
            // If this is a controller according to the SLICK2D properties
            if( (nbButt >= 3) && (nbButt < 100) )
            {
                // If this is a GAMECUBE pad, add the gamecube config, else add Xbox config
                if( nam.contains("MAYFLASH") && nam.contains("GAMECUBE") && nam.contains("CONTROLLER") )
                {
                    // SPECIFIC GAMECUBE
                    ControllerAL.buttons.add(ControllerAL.buttons_gamecube);
                    ControllerAL.axis.add(ControllerAL.axis_gamecube);
                    ControllerAL.zAxisRatio.add(0.5f);
                }
                else if( nam.contains("USB") && nam.contains("GAMEPAD"))
                {
                    // SPECIFIC SNES CLASSIC
                    ControllerAL.buttons.add(ControllerAL.buttons_snesusb);
                    ControllerAL.axis.add(ControllerAL.axis_snesusb);
                    ControllerAL.zAxisRatio.add(1.0f);
                }
                else
                {
                    // DEFAULT is XBOX360
                    ControllerAL.buttons.add(ControllerAL.buttons_xbox);
                    ControllerAL.axis.add(ControllerAL.axis_xbox);
                    ControllerAL.zAxisRatio.add(1.0f);
                }
                System.out.println("Added controller : "+nam);
                
                /*
                int nbAx = Controllers.getController(i).getAxisCount();
                for(int ax=0;ax<nbAx;ax++)
                {
                    System.out.println(Controllers.getController(i).getAxisName(ax));
                }
                //*/
            }
        } 
    }
        
    // Get button number (physical) according to the button name (logical)
    public static int getButtonNumber(int ctrlID, Buttons buttonName)
    {
        // Init button number with invalid number
        int result = -1;
        // Check if the controller is in the list
        if( ctrlID>=0 && ctrlID<ControllerAL.buttons.size() )
        {
            // Get the button number from the hashmap
            result = ControllerAL.buttons.get(ctrlID).getOrDefault(buttonName, -1);
        }
        return result;
    }
    
    // Get the axis number (physical) according to the axis name (logical)    
    public static int getAxisNumber(int ctrlID, Axis axisName)
    {
        // Init button number with invalid number
        int result = -1;
        // Check if the controller is in the list
        if( ctrlID>=0 && ctrlID<ControllerAL.axis.size() )
        {
            // Get the button number from the hashmap
            result = ControllerAL.axis.get(ctrlID).getOrDefault(axisName, -1);
        }
        return result;
    }
    
    // Get the axis number (physical) according to the axis name (logical)    
    public static float getZAxisRatio(int ctrlID)
    {
        // Init button number with invalid number
        float result = 1.0f;
        // Check if the controller is in the list
        if( ctrlID>=0 && ctrlID<ControllerAL.axis.size() )
        {
            // Get the button number from the hashmap
            result = ControllerAL.zAxisRatio.get(ctrlID);
        }
        return result;
    }
    
    // Get brand of the controller
    public static Brands getBrand(int ctrlID)
    {
        // Init result
        Brands brand = Brands.KEYBOARD;
        // Check if the controller is in the list
        if( ctrlID>=0 && ctrlID<ControllerAL.buttons.size() )
        {
            // Check the controller brand (gamecube)
            if( ControllerAL.buttons.get(ctrlID) == ControllerAL.buttons_gamecube )
            {
                brand = Brands.GAMECUBE;
            }
            else
            {
                brand = Brands.XBOX;
            }
        }
        // return controller brand
        return brand;
    }
    
    
    // Check if logical button has been pressed. Needs the physical button number in parameter
    public static boolean isButtonOK(Buttons buttonName, int controllerID, int buttonID)
    {
        return (ControllerAL.getButtonNumber(controllerID, buttonName) == buttonID);
    }

    // Check if logical button has been pressed. Needs the physical button number in parameter
    public static boolean isAxisOK(Axis axisName, int controllerID, int axisID)
    {
        return (ControllerAL.getAxisNumber(controllerID, axisName) == axisID);
    }
    
    // Display all information about a button number from a controller number
    public static void displayButtonName(int ctrlID, int buttonID)
    {
        if( ctrlID>=0 && ctrlID<ControllerAL.buttons.size() )
        {
            // get current configuration
            HashMap<Buttons,Integer> cfg = ControllerAL.buttons.get(ctrlID);
            for(Buttons b : cfg.keySet())
            {
                if(cfg.get(b)==buttonID)
                {
                    System.out.println("Controller #"+ctrlID+" / Button #"+buttonID+" ("+b+")");
                }
            }
        }       
    }
    
    // Display all information about an axis number from a controller number
    public static void displayAxisName(int ctrlID, int axisID, float value)
    {
        if( ctrlID>=0 && ctrlID<ControllerAL.buttons.size() )
        {
            // get current configuration
            HashMap<Axis,Integer> cfg = ControllerAL.axis.get(ctrlID);
            for(Axis ax : cfg.keySet())
            {
                if(cfg.get(ax)==axisID)
                {
                    System.out.println("Controller #"+ctrlID+" / Axis #"+axisID+" ("+ax+") / Value = "+value);
                }
            }
        }       
    }
    
    
    
    //--------------------------------------------------------------------
    // END OF CLASS
    //--------------------------------------------------------------------
}

/*
+--------------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+
| BUTTONS      |  A  |  B  |  X  |  Y  | L1  | R1  | BCK | STR |     |     |     |     |     |     |
| AXIS         |     |     |     |     |     |     |     |     | AxX | AxY | RoX | RoY | Z1  | Z2  | 
+--------------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+
| XBOX         |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  1  |  0  |  3  |  2  |  4  |  ?  |
+--------------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+
| GAMECUBE     |  2  |  3  |  1  |  4  |  5  |  6  |  8  | 10  |  3  |  2  |  0  |  1  |  5  |  4  |
+--------------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+
| SuperNes USB |  2  |  3  |  1  |  4  |  5  |  6  |  9  | 10  |  1  |  0  |  ?  |  ?  |  ?  |  ?  |
+--------------+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+
//*/
