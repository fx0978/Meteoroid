package util;

import java.awt.Color;

public class Constants {
    
    // Panel
    public static int panelWidth = 1600;
    public static int panelHeight = 900;
   
    // Spaceship
    public static int spaceShipSize = 100;     
    public static double spaceShipTopOffset = .2*spaceShipSize;
    public static double spaceShipBottomOffset = .05*spaceShipSize;
    public static int spaceShipSpawnX = (panelWidth - spaceShipSize)/2;
    public static int spaceShipSpawnY = (int)(.95*(panelHeight - spaceShipSize + spaceShipBottomOffset));

    // Bullet
    public static int bulletSize = 32;
    public static int bulletCenterToShip = (spaceShipSize-bulletSize)/2;
    public static int bulletFrequency = 100;

    // Meteoroid
    public static int meteoroidSmallSize = 50;
    public static int meteoroidMediumSize = 200;

    //Labels
    public static int pointsLabelHeight = 50;

    //Buttons
    public static int buttonWidth = 100;
    public static int buttonHeight = 50;
    public static Color buttonBackground = Color.BLACK;
    public static Color buttonText = Color.WHITE;

}
