package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import main.StartGame;

public class Instructions extends State {
    private JButton backButton = new JButton("Back");

    public Instructions(StartGame game) {
        super(game);
        
        // Back Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButton.setVisible(false);
                GameState.setGameStateMenu();
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

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(util.Constants.defaultFont);
        g.drawString("INSTRUCTIONS", (util.Constants.PANEL_WIDTH/2) - 58, (util.Constants.PANEL_HEIGHT/2) - 100);
        // TODO: Add instructions button scheme text
    }

    public void showInstructions() {
        backButton.setVisible(true);
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
