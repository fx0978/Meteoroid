package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.Inputs;

public class GamePanel extends JPanel {

    private Inputs inputs;
    private StartGame game;

    public GamePanel(StartGame game) {
        this.game = game;
        setSize();
        setBackground(Color.BLACK);
        setLayout(null);

        inputs = new Inputs(this);
        this.addKeyListener(this.inputs);
        this.addMouseListener(this.inputs);
        this.addMouseMotionListener(this.inputs);

        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    // Sets the size of the panel component
    private void setSize() {
        Dimension size = new Dimension(util.Constants.PANEL_WIDTH, util.Constants.PANEL_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    /**
     * @return game of StartGame type object reference
     */
    public StartGame getGame(){
        return game;
    }
}
