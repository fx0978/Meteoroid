package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import main.StartGame;

public class Controls extends State {
    private JButton backButton = new JButton("Back");

    public Controls(StartGame game) {
        super(game);
        
        // Back Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButton.setVisible(false);
                game.getMenu().showMenu();                
            }
        });
        backButton.setBounds((util.Constants.PANEL_WIDTH/2) - 35, 
        (util.Constants.PANEL_HEIGHT/2) + util.Constants.BUTTON_HEIGHT + 25, 
        util.Constants.BUTTON_WIDTH, util.Constants.BUTTON_HEIGHT);
        backButton.setBackground(util.Constants.BUTTON_BACKGROUND);
        backButton.setForeground(util.Constants.BUTTON_TEXT);
        backButton.setOpaque(true);
        backButton.setFocusable(false);
        backButton.setVisible(false);
        game.getGamePanel().add(backButton);
    }

    @Override
    public void update() {

    }

    /**
     * Draws the strings for controls
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(util.Constants.MEDIUM_SIZE_FONT);
        g.drawString("CONTROLS", (util.Constants.PANEL_WIDTH/2) - 58, (util.Constants.PANEL_HEIGHT/2) - 100);

        g.setFont(util.Constants.SMALL_SIZE_FONT);
        g.drawString("W - Up", (util.Constants.PANEL_WIDTH/2) - 56, (util.Constants.PANEL_HEIGHT/2) - 75);
        g.drawString("A - Left", (util.Constants.PANEL_WIDTH/2) - 56, (util.Constants.PANEL_HEIGHT/2) - 50);
        g.drawString("S - Down", (util.Constants.PANEL_WIDTH/2) - 56, (util.Constants.PANEL_HEIGHT/2) - 25);
        g.drawString("D - Right", (util.Constants.PANEL_WIDTH/2) - 56, (util.Constants.PANEL_HEIGHT/2));
        g.drawString("SPACEBAR - Shoot", (util.Constants.PANEL_WIDTH/2) - 56, (util.Constants.PANEL_HEIGHT/2) + 25);
        g.drawString("P - Pause (while started game)", (util.Constants.PANEL_WIDTH/2) - 56, (util.Constants.PANEL_HEIGHT/2) + 50);
    }

    public void showInstructions() {
        backButton.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
