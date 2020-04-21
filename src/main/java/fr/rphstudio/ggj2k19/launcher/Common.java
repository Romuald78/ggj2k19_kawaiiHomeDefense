/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.ggj2k19.launcher;

import org.newdawn.slick.Color;

/**
 *
 * @author GRIGNON FAMILY
 */
public class Common
{
    //---------------------------------------------------------
    // PROGRAM CONSTANTS
    //---------------------------------------------------------
    // Screen default dimensions (automatically resized, if the screen has a different resolution)
    public static final float   SCREEN_WIDTH   = 1920.0f;
    public static final float   SCREEN_HEIGHT  = 1080.0f;    
    // Colors used during game state transitions
    public static final Color   COLOR_FADE_IN  = Color.orange;
    public static final Color   COLOR_FADE_OUT = Color.yellow;
    // Timings used during game state transitions
    public static final int     TIME_FADE_IN   = 250;
    public static final int     TIME_FADE_OUT  = 250;
    public static final int     TIME_FADE_IN2  = 250;
    public static final int     TIME_FADE_OUT2 = 750;
    // Timing used for intro auto skip
    public final static long    INTRO_PAGE_TIME_MS = 4000;
  
    // Physic-Display relationships
    public final static float   NB_PIXELS_PER_METER         = 64.0f;
//    public final static float   SPRITE_PHYSIC_DISPLAY_RATIO = 0.75f; 
    
    // Player colors
    public static final Color[] playerColors =
    {
        new Color(131, 153, 236, 200),
        new Color(236, 131, 131, 200),
        new Color(144, 236, 131, 200),
        new Color(239, 146, 0, 200),
    };
    
    // PROJECTILES
    public final static float PROJECTILE_W_RADIUS   = 20.0f;
    public final static float PROJECTILE_RADIUS     = PROJECTILE_W_RADIUS/(2*NB_PIXELS_PER_METER);
    public final static float PROJECTILE_MASSRATIO  = 5.0f;
    public final static float PROJECTILE_DAMPING    = 0.0f;
    public final static long  PROJECTILE_LIFETIME   = 500;
    public final static float PROJECTILE_SPEED      = 20f;
    public final static float PROJECTILE_DMG        = 1.0f;
    public final static int   PROJECTILE_TYPE_FIRE  = 0;
    public final static int   PROJECTILE_TYPE_WATER = 1;
    public final static int   PROJECTILE_TYPE_ICE   = 2;
    public final static int   PROJECTILE_TYPE_EARTH = 3;
    
    // PLAYER
    public static final float FIRERATE_PER_SECOND = 20;
    public final static float PLAYER_W_PIX     = 64.0f;
    public final static float PLAYER_RADIUS    = PLAYER_W_PIX/(2*NB_PIXELS_PER_METER);
    public final static float PLAYER_MASSRATIO = 0.5f;
    public final static float PLAYER_DAMPING   = 7.0f;
    public final static float PLAYER_SPEED     = 0.5f;
    public final static float PLAYER_HP        = 200.0f;
    
    // ENEMY
    public final static float ENEMY_W_PIX     = 64.0f;
    public final static float ENEMY_RADIUS    = ENEMY_W_PIX/(2*NB_PIXELS_PER_METER);
    public final static float ENEMY_MASSRATIO = 0.4f;
    public final static float ENEMY_DAMPING   = 7.0f;
    public final static float ENEMY_SPEED     = 0.04f;
    public final static float ENEMY_HP        = 200.0f;

    public final static float ENEMY_HP_BOSS        = 2000.0f;
    public final static float ENEMY_W_PIX_BOSS     = 64.0f*2;
    public final static float ENEMY_RADIUS_BOSS    = (ENEMY_W_PIX*2)/(2*NB_PIXELS_PER_METER);
    public final static float ENEMY_MASSRATIO_BOSS = 2.0f;
    public final static float ENEMY_DAMPING_BOSS   = 7.0f;
    public final static float ENEMY_SPEED_BOSS     = 0.7f;
    
    public static final float ENEMY_MOVE_MAX_DISTANCE  = 128.0f/NB_PIXELS_PER_METER;
    public static final float ENEMY_SHOOT_MIN_DISTANCE = 10;
    
    // HOME

    public final static float HOME_HP        = 500.0f;
    
    // WAVES
    public final static float WAVE_INTERVAL_MS = 15000;
    public final static float WAVE_INTERVAL_PAUSE_MS = 40000;
    public final static float WAVE_NB_ENEMIES  = 3;
    public final static float WAVE_INCREASE  = 1.05f;
    
    // DAMAGE
    public static final int DAMAGE_OWNER_PLAYER = 1;
    public static final int DAMAGE_OWNER_ENEMY  = 2;
    
    // DOT
    public static final int DOT_DURATION  = 10000;
    public static final float DOT_COEF      = 1.0f/250.0f;
    
    // Life bar 
    public static final int LIFEBAR_W = 64;
    public static final int LIFEBAR_H = 7;
    public static final int LIFEBAR_HOME_W = 200;
    public static final int LIFEBAR_HOME_H = 15;
    
    
    public static final float CUMUL_COEF = 2.0f;


}