package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.StartGame;

// TODO: remove the unused mouse listener methods if we don't use
public class Menu extends State{

    private BufferedImage titleImg;
    private JButton startButton = new JButton("START");
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
                quitButton.setVisible(false);                
            }
        });
        startButton.setBounds((util.Constants.panelWidth/2) - 35, 
        (util.Constants.panelHeight/2) + 10, 
        util.Constants.buttonWidth, util.Constants.buttonHeight);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setOpaque(true);
        startButton.setFocusable(false);
        game.getGamePanel().add(startButton);

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
        quitButton.setBounds((util.Constants.panelWidth/2) - 35, 
        (util.Constants.panelHeight/2) + (int) startButton.getAlignmentY() + 75, 
        util.Constants.buttonWidth, util.Constants.buttonHeight);
        quitButton.setBackground(util.Constants.buttonBackground);
        quitButton.setForeground(util.Constants.buttonText);
        startButton.setOpaque(true);
        quitButton.setFocusable(false);
        game.getGamePanel().add(quitButton);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(util.Constants.defaultFont);
        g.drawString("MENU", util.Constants.panelWidth/2 - 15, util.Constants.panelHeight/2);
        g.drawImage(titleImg, (int)(util.Constants.panelWidth*.20), 0,(int) (util.Constants.panelWidth*.67), 200, null);
    }

    public void showMenu() {
        startButton.setVisible(true);
        quitButton.setVisible(true);

        GameState.state = GameState.MENU;
        game.getMusicPlayer().stop();
        game.getMusicPlayer().playMusicLoop("Title.mid");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P) {
            GameState.state = GameState.PLAY;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}   
