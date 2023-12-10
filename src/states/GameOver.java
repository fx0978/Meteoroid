package states;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.StartGame;

// TODO: remove the unused mouse listener methods if we don't use
public class GameOver extends State{

    private BufferedImage gameoverImg;
    private int animationY;
    private JButton restartButton = new JButton("RESTART");
    private JButton returnButton = new JButton("RETURN");
    private JLabel point;

    /**
     * Initialize the Game Over with buttons
     * @param game
     */
    public GameOver(StartGame game) {
        super(game);
        gameoverImg = util.Tools.importImg("game_over.png");

        animationY = -gameoverImg.getHeight();
        point = game.getPlay().pointsLabel;

        // Restart Button
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartButton.setVisible(false);
                returnButton.setVisible(false);
                game.getPlay().restart();
            }
        });
        restartButton.setVisible(GameState.state == GameState.GAMEOVER);
        restartButton.setBounds((util.Constants.panelWidth/2) - 35, 
        (util.Constants.panelHeight/2) + point.getHeight() + 20, 
        util.Constants.buttonWidth, util.Constants.buttonHeight);
        restartButton.setBackground(util.Constants.buttonBackground);
        restartButton.setForeground(util.Constants.buttonText);
        restartButton.setOpaque(true);
        restartButton.setFocusable(false);
        game.getGamePanel().add(restartButton);

        // Return Button
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartButton.setVisible(false);
                returnButton.setVisible(false);
                point.setVisible(false);
                game.getMenu().showMenu();
            }
        });
        returnButton.setVisible(GameState.state == GameState.GAMEOVER);
        returnButton.setBounds((util.Constants.panelWidth/2) - 35, 
        (util.Constants.panelHeight/2) + returnButton.getVerticalAlignment() + 130, 
        util.Constants.buttonWidth, util.Constants.buttonHeight);
        returnButton.setBackground(util.Constants.buttonBackground);
        returnButton.setForeground(util.Constants.buttonText);
        returnButton.setOpaque(true);
        returnButton.setFocusable(false);
        game.getGamePanel().add(returnButton);
    }

    /**
     * Updates Y axis of the "GAME OVER" title
     */
    @Override
    public void update() {
        if (animationY < (util.Constants.panelHeight - gameoverImg.getHeight())/2) {
            animationY+=2;
        }
    }

    /**
     * For external call to show the correct elements for the game over screen
     */
    public void showGameOver() {
        GameState.state = GameState.GAMEOVER;
        animationY = -gameoverImg.getHeight();
        point = game.getPlay().pointsLabel;

        // Readjust point to display in center
        point.setBounds((util.Constants.panelWidth/2) - 37, (util.Constants.panelHeight/2) + 20, point.getWidth(), point.getHeight());

        restartButton.setVisible(GameState.state == GameState.GAMEOVER);
        returnButton.setVisible(GameState.state == GameState.GAMEOVER);

        game.getMusicPlayer().stop();
        game.getMusicPlayer().playMusicLoop("GameOver.mid");
    }

    /**
     * Draws the title animation
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(gameoverImg, (int)(util.Constants.panelWidth*.20), animationY,(int) (util.Constants.panelWidth*.67), (int) (.4*util.Constants.panelHeight), null);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
