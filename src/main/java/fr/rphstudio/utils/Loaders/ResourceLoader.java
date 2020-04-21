//--------------------------------------------------------------------
// IMPORTS
//--------------------------------------------------------------------
package fr.rphstudio.utils.Loaders;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;


//--------------------------------------------------------------------
// CLASS
//--------------------------------------------------------------------

/**
 * 
 * @author Romuald GRIGNON
 */
public class ResourceLoader
{
    //--------------------------------------------------------------------
    // PROPERTIES
    //--------------------------------------------------------------------
    
    // button configuration types
    private static HashMap<String, Image>       images       = new HashMap<String, Image>();
    private static HashMap<String, SpriteSheet> spritesheets = new HashMap<String, SpriteSheet>();
    private static HashMap<String, Sound>       sounds       = new HashMap<String, Sound>();
    private static HashMap<String, Music>       musics       = new HashMap<String, Music>();

    
    //--------------------------------------------------------------------
    // METHODS
    //--------------------------------------------------------------------
    // IMAGE
    public static void addImage(String name, String path)
    {
        try
        {
            ResourceLoader.images.put( name, new Image(path) );
        }
        catch(SlickException se)
        {
            throw new Error(se);
        }
    }
    public static Image getImage(String name)
    {
        Image result = null;
        if(ResourceLoader.images.containsKey(name))
        {
            result = ResourceLoader.images.get(name);
        }
        return result;
    }
    
    // SPRITESHEET
    public static void addSpriteSheet(String name, String path, int dw, int dh)
    {
        try
        {
            ResourceLoader.spritesheets.put( name, new SpriteSheet(path, dw, dh) );
        }
        catch(SlickException se)
        {
            throw new Error(se);
        }
    }
    public static SpriteSheet getSpriteSheet(String name)
    {
        SpriteSheet result = null;
        if(ResourceLoader.spritesheets.containsKey(name))
        {
            result = ResourceLoader.spritesheets.get(name);
        }
        return result;
    }
    
    // SOUND
    public static void addSound(String name, String path)
    {
        try
        {
            ResourceLoader.sounds.put( name, new Sound(path) );
        }
        catch(SlickException se)
        {
            throw new Error(se);
        }
    }
    public static Sound getSound(String name)
    {
        Sound result = null;
        if(ResourceLoader.sounds.containsKey(name))
        {
            result = ResourceLoader.sounds.get(name);
        }
        return result;
    }

    // MUSIC
    public static void addMusic(String name, String path)
    {
        try
        {
            ResourceLoader.musics.put( name, new Music(path) );
        }
        catch(Exception e)
        {
            System.out.println("ERROR WHILE loading music");
            throw new Error(e);
        }
    }
    public static Music getMusic(String name)
    {
        Music result = null;
        if(ResourceLoader.musics.containsKey(name))
        {
            result = ResourceLoader.musics.get(name);
        }
        return result;
    }

    
    //--------------------------------------------------------------------
    // END OF CLASS
    //--------------------------------------------------------------------
}

