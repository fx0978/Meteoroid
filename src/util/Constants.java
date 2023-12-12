package util;

import java.awt.Color;
import java.awt.Font;

public class Constants {
    
    // Panel
    public static final int PANEL_WIDTH = 1600;
    public static final int PANEL_HEIGHT = 900;
   
    // Spaceship
    public static final int SPACESHIP_SIZE = 100;     
    public static final double SPACESHIP_TOP_OFFSET = .2*SPACESHIP_SIZE;
    public static final double SPACESHIP_BOTTOM_OFFSET = .05*SPACESHIP_SIZE;
    public static final int SPACESHIP_SPAWN_X = (PANEL_WIDTH - SPACESHIP_SIZE)/2;
    public static final int SPACESHIP_SPAWN_Y = (int)(.95*(PANEL_HEIGHT - SPACESHIP_SIZE + SPACESHIP_BOTTOM_OFFSET));

    // Bullet
    public static final int BULLET_SIZE = 32;
    public static final int BULLET_CENTER_TO_SHIP = (SPACESHIP_SIZE-BULLET_SIZE)/2;
    public static final int BULLET_DEFAULT_FREQUENCY = 200;

    // Meteoroid
    public static final int METEOROID_SMALL_SIZE = 50;
    public static final int METEOROID_MEDIUM_SIZE = 200;
    public static final float METEOROID_DEFAULT_SPEED = 0.2f;
    public static final float METEOROID_MAX_SPEED = 1.6f;
    public static final int METEOROID_DEFAULT_FREQUENCY = 4000; // per miliseconds generate
    public static final int METEOROID_MIN_FREQUENCY = 2000;

    // Labels
    public static final int POINTS_LABEL_HEIGHT = 50;

    // Buttons
    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 50;
    public static final Color BUTTON_BACKGROUND = Color.BLACK;
    public static final Color BUTTON_TEXT = Color.WHITE;

    // Font
    public static final Font MEDIUM_SIZE_FONT = util.Tools.getFont("src/resources/defaultFontStyle.ttf").deriveFont(30f);
    public static final Font SMALL_SIZE_FONT = util.Tools.getFont("src/resources/defaultFontStyle.ttf").deriveFont(20f);

    // Miscellaneous
    public static final int POINTS_THRESHOLD = 25; // increase speed/frequency at this interval

}
