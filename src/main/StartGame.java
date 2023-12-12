package main;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import states.GameOver;
import states.GameState;
import states.Instructions;
import states.Play;
import util.MusicPlayer;
import states.Menu;
import states.Paused;

public class StartGame implements Runnable {
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Menu menu;
    private Play play;
    private GameOver gameover;
    private Paused paused;
    private Instructions instructions;
    
    // Initializes the game and starts the loop
    public StartGame() {
        JFrame gameFrame = new JFrame();
        gameFrame.setTitle("Meteoroids");
        gamePanel = new GamePanel(this);
        init();
        startLoop();

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.getContentPane().add(gamePanel);
        gameFrame.pack(); // size to subcomponents - see GamePanel
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        gameFrame.addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent e) {
                //Do nothing on regained
            }

            public void windowLostFocus(WindowEvent e) {
                switch (GameState.getGameState()) {
                    case PLAY:                        
                        getPlay().getSpaceShip().stopShip();
                        break;
                    default:
                        break;
                }
            }
        });
        
    }

    /**
     * Initializes the game states and music player
     */
    private void init() {
        menu = new Menu(this);
        play = new Play(this);
        gameover = new GameOver(this);
        paused = new Paused(this);
        instructions = new Instructions(this);
        MusicPlayer.playMusicLoop("Title.mid");
    }

    /**
     * Start thread for running game loop
     */
    private void startLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Update switch for each game state
     */
    public void update() {
        switch (GameState.getGameState()) {
            case MENU:
                menu.update();
                break;
            case PLAY:
                play.update();
                break;
            case GAMEOVER:
                gameover.update();
            default:
                break;
        }
    }

    /**
     * Render/draw method call for each game state
     * @param g
     */
    public void render(Graphics g) {
        switch (GameState.getGameState()) {
            case MENU:
                menu.draw(g);
                break;
            case PLAY:
                play.draw(g);
                break;
            case GAMEOVER:
                gameover.draw(g);
                break;
            case INSTRUCTIONS:
                instructions.draw(g);
                break;
            default:
                break;
        }
    }

    /**
     * Runs game loop in thread.
     * Calculates when to draw a frame and when to update.
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                // uncomment to show the fps and ups in console
                // System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
            
        }
    }

    /**
     * @return menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @return play
     */
    public Play getPlay() {
        return play;
    }

    /**
     * @return gamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * @return gameover
     */
    public GameOver getGameOver() {
        return gameover;
    }

    /**
     * @return paused
     */
    public Paused getPaused() {
        return paused;
    }

    /**
     * @return instructions
     */
    public Instructions geInstructions() {
        return instructions;
    }

}
