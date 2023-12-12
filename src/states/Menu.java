package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.StartGame;
import util.MusicPlayer;

public class Menu extends State{

    private BufferedImage titleImg;
    private JButton startButton = new JButton("START");
    private JButton controlsButton = new JButton("CONTROLS");
    private JButton quitButton = new JButton("QUIT");

    public Menu(StartGame game) {
        super(game);

        titleImg = util.Tools.importImg("title.png");

        // Start Button 
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlay().restart();
                startButton.setVisible(false);
                controlsButton.setVisible(false);
                quitButton.setVisible(false);                
            }
        });
        startButton.setBounds((util.Constants.PANEL_WIDTH/2) - 35, 
        (util.Constants.PANEL_HEIGHT/2) + 10, 
        util.Constants.BUTTON_WIDTH, util.Constants.BUTTON_HEIGHT);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setOpaque(true);
        startButton.setFocusable(false);
        game.getGamePanel().add(startButton);

        // Instructions Button
        controlsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setVisible(false);
                controlsButton.setVisible(false);
                quitButton.setVisible(false);
                GameState.setGameStateInstruction();
                game.geInstructions().showInstructions();
            }
        });
        controlsButton.setBounds((util.Constants.PANEL_WIDTH/2) - 35, 
        (util.Constants.PANEL_HEIGHT/2) + util.Constants.BUTTON_HEIGHT + 25, 
        util.Constants.BUTTON_WIDTH, util.Constants.BUTTON_HEIGHT);
        controlsButton.setBackground(util.Constants.BUTTON_BACKGROUND);
        controlsButton.setForeground(util.Constants.BUTTON_TEXT);
        controlsButton.setOpaque(true);
        controlsButton.setFocusable(false);
        game.getGamePanel().add(controlsButton);

        // Exit Button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        game.getGamePanel(),
                        "Are you sure you want to quit the game?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

                // Exit option
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        quitButton.setBounds((util.Constants.PANEL_WIDTH/2) - 35, 
        (util.Constants.PANEL_HEIGHT/2) + util.Constants.BUTTON_HEIGHT + 90, 
        util.Constants.BUTTON_WIDTH, util.Constants.BUTTON_HEIGHT);
        quitButton.setBackground(util.Constants.BUTTON_BACKGROUND);
        quitButton.setForeground(util.Constants.BUTTON_TEXT);
        quitButton.setOpaque(true);
        quitButton.setFocusable(false);
        game.getGamePanel().add(quitButton);


    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(util.Constants.MEDIUM_SIZE_FONT);
        g.drawString("MENU", util.Constants.PANEL_WIDTH/2 - 15, util.Constants.PANEL_HEIGHT/2);
        g.drawImage(titleImg, (int)(util.Constants.PANEL_WIDTH*.20), 0,(int) (util.Constants.PANEL_WIDTH*.67), 200, null);
    }

    public void showMenu() {
        startButton.setVisible(true);
        controlsButton.setVisible(true);
        quitButton.setVisible(true);

        // Reset the game music only when going from GAMEOVER -> MENU
        if (GameState.getGameState() == GameState.GAMEOVER) {
            MusicPlayer.stop();
            MusicPlayer.playMusicLoop("Title.mid");
        }

        GameState.setGameStateMenu();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P) {
            GameState.setGameStatePaused();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}   
