/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.game;

import fr.rphstudio.ggj2k19.launcher.Common;
import org.newdawn.slick.Color;

/**
 *
 * @author GRIGNON FAMILY
 */
public class PlayerInfo {
    
    private int     controllerID;
    private int     characterID;
    private boolean isReady;
    
    
    // Constructor
    public PlayerInfo(int ctrlID)
    {
        this.controllerID = ctrlID;
        this.characterID  = -1;
        this.isReady      = false;
    }
    // Controller
    public int getControllerID()
    {
        return this.controllerID;
    }
    // Ready Flag
    public void setReady()
    {
        this.isReady = true;
    }
    public void setNotReady()
    {
        this.isReady = false;
    }
    public boolean isReadyToStart()
    {
        return this.isReady;
    }
    // CharacterID
    public void setNewCharacter(int charID)
    {
        if(!this.isReady)
        {
            this.characterID = charID;
        }
    }
    public int getCharacterID()
    {
        return this.characterID;
    }
    // Color (coming from Common constant)
    public Color getColor()
    {
        return Common.playerColors[this.characterID];
    }
    
    
}
