package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import main.StartGame;

// TODO: remove the unused mouse listener methods if we don't use
public class Paused extends State{

    private JButton resumebutton = new JButton("RESUME");

    public Paused(StartGame game) {
        super(game);

        // Resume Button 
        resumebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumebutton.setVisible(false);
                GameState.setGameStatePlay();      
            }
        });
        resumebutton.setBounds((util.Constants.PANEL_WIDTH/2) - 35, 
        (util.Constants.PANEL_HEIGHT/2) + 10, 
        util.Constants.BUTTON_WIDTH, util.Constants.BUTTON_HEIGHT);
        resumebutton.setBackground(Color.BLACK);
        resumebutton.setForeground(Color.WHITE);
        resumebutton.setOpaque(true);
        resumebutton.setFocusable(false);
        resumebutton.setVisible(false);
        game.getGamePanel().add(resumebutton);
    }

    @Override
    public void update() {
        // We don't do any updates in paused state
    }

    /**
     * draw the PAUSED string
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("PAUSED", util.Constants.PANEL_WIDTH/2, util.Constants.PANEL_HEIGHT/2);
    }

    /**
     * For external method call to show the paused screen
     */
    public void showPaused() {
        game.getPlay().getSpaceShip().stopShip();
        resumebutton.setVisible(true);
        GameState.setGameStatePaused();
    }

    /**
     * For external method call to remove the paused screen
     */
    public void unPauseGame() {
        resumebutton.setVisible(false);
        GameState.setGameStatePlay();
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
