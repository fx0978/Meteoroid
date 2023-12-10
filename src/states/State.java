package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;

import main.StartGame;

public abstract class State {
    protected StartGame game;

    /**
     * sets the game to the parameter game
     * @param game
     */
    public State(StartGame game) {
        this.game = game;
    }

    /**
     * @return game
     */
    public StartGame getGame() {
        return game;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
}
