package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;
import states.GameState;

//TODO: Remove the mouse listeners if we don't end up using these
public class Inputs implements KeyListener, MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public Inputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Mandatory override, we don't actually use this method for now
    }

    /**
     * Implements key binding for W,A,S,D keyboard keys when pressed.
     * Each GameState implements their own pressed action. This switch statement calls their method.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAY:
                gamePanel.getGame().getPlay().keyPressed(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().keyPressed(e);
                break;
            default:
                break;
        }
    }

    /**
     * Implements key binding for W,A,S,D keyboard keys when released
     * Each GameState implements their own released action. This switch statement calls their method.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAY:
                gamePanel.getGame().getPlay().keyReleased(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().keyReleased(e);
                break;
            case PAUSED:
                gamePanel.getGame().getPaused().keyReleased(e);
            default:
                break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
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
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
