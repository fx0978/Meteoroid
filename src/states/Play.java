package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;

import characters.Meteoroid;
import characters.SpaceShip;
import main.StartGame;

// TODO: remove the unused mouse listener methods if we don't use
public class Play extends State {

    protected SpaceShip spaceShip;
    private List<Meteoroid> meteoroids = new CopyOnWriteArrayList<>();
    private long lastCheck = System.currentTimeMillis();
    private int points = 0;
    protected JLabel pointsLabel = new JLabel("Points: 0");

    public Play(StartGame game) {
        super(game);
        init();
    }

    /**
     * Initializes spaceship, points label, and meteoroid generation start
     */
    private void init() {
        spaceShip = new SpaceShip(util.Constants.spaceShipSpawnX, util.Constants.spaceShipSpawnY,
                util.Constants.spaceShipSize, util.Constants.spaceShipSize);
        createMeteoroids();

        pointsLabel.setBounds(10, (int) (util.Constants.panelHeight - util.Constants.pointsLabelHeight),
                (int) (util.Constants.panelWidth), util.Constants.pointsLabelHeight);
        pointsLabel.setVisible(false);
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setFont(util.Constants.defaultFont);
        game.getGamePanel().add(pointsLabel);
    }

    /**
     * Creates the meteoroids using a random
     */
    private void createMeteoroids() {
        Random random = new Random();
        int meteoroidSize;
        Meteoroid m;
        // Shouldn't use the newer method random.nextInt(origin, bound) as that is only in Java 17 and older systems may not be able to run it!
        for (int numberOfMeteoroids = random.nextInt(5) + 5; numberOfMeteoroids > 0; numberOfMeteoroids--) {
            
            meteoroidSize = random.nextInt(util.Constants.meteoroidMediumSize) + util.Constants.meteoroidSmallSize;

            if ((m = createMeteoroid(meteoroids, meteoroidSize)) != null) {
                meteoroids.add(m);
            }
        }
    }

    /**
     * Creates a single meteoroid such that it doesn't colloid with the other
     * existing meteoroids in the given list meteoroids
     * 
     * @param meteoroids
     * @param size
     * @return the new meteroid
     */
    private Meteoroid createMeteoroid(List<Meteoroid> meteoroids, int size) {
        Boolean colliding;
        Random random = new Random();
        int x, y;
        int retry = 300;
        do {
            colliding = false;
            x = random.nextInt(util.Constants.panelWidth);
            y = 0 - size;

            for (Meteoroid m : meteoroids) {
                if (m.getHitBox().intersects(x, 0 - size, size, size)) {
                    colliding = true;
                    retry--;
                    break;
                }
            }
        } while ((colliding || isOutside(x, y, size)) && retry > 0);

        if (retry <= 0 && colliding) {
            return null;
        }

        return new Meteoroid(x, y, size, size);

    }

    /**
     * Checks if a given element is outside of the panel
     * TODO: Consider moving this to util Tool? - Seems generic tool
     * 
     * @param x
     * @param y
     * @param size
     * @return
     */
    private Boolean isOutside(int x, int y, int size) {
        return x + size > util.Constants.panelWidth || y + size > util.Constants.panelHeight;
    }

    /**
     * Call update for the spaceship and meteoroids.
     * Here we do the timing for creating meteoroids.
     */
    @Override
    public void update() {
        spaceShip.update();
        meteoroids.forEach(m -> m.update());

        if (System.currentTimeMillis() - lastCheck >= 4000) {
            lastCheck = System.currentTimeMillis();
            createMeteoroids();
        }
    }

    /**
     * Draws spaceship, meteoroids, check if game over (due to colliding with
     * meteoroid)
     */
    @Override
    public void draw(Graphics g) {
        spaceShip.render(g);
        statusCheck(meteoroids);
        pointsLabel.setVisible(true);

        for (Meteoroid m : meteoroids) {
            if (m.getHitBox().intersects(spaceShip.getHitBox())) {
                GameState.state = GameState.GAMEOVER;
                game.getGameOver().showGameOver();
                break;
            }
            m.render(g);
        }
    }

    /**
     * Checks the bullet colliding with meteoroids, adding points
     * 
     * @param meteoroids
     */
    private void statusCheck(List<Meteoroid> meteoroids) {
        meteoroids.removeIf(meteoroid -> meteoroid.getY() > util.Constants.panelHeight);

        // Chat GPT recommended loop for modifying object when iterating through it
        for (Meteoroid m : meteoroids) {
            if (spaceShip.getBullets().removeIf(bullet -> bullet.getHitBox().intersects(m.getHitBox()))) {
                if (m.getSize() == 0) {
                    points++;
                } else {
                    points += 2;
                }
        
                pointsLabel.setText("Points: " + points);
                meteoroids.remove(m); // Directly remove the element from the CopyOnWriteArrayList
            }
        }
    }

    /**
     * Sets the game state for playing. Can be called on first start or when
     * restarting from other game states.
     */
    public void restart() {
        // Reset game state and elements
        pointsLabel.setBounds(10, (int) (util.Constants.panelHeight - util.Constants.pointsLabelHeight),
                (int) (util.Constants.panelWidth), util.Constants.pointsLabelHeight);
        points = 0;
        pointsLabel.setText("Points: 0");
        meteoroids.removeIf(m -> true);
        spaceShip.resetShip();
        GameState.state = GameState.PLAY;

        // Restart music
        game.getMusicPlayer().stop();
        game.getMusicPlayer().playMusic("Start.mid");
        game.getMusicPlayer().playMusicLoop("Playing.mid");
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

    /**
     * Sets the key press for spaceship directions, attack to true and shows the
     * pause screen
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                spaceShip.setDirUp(true);
                break;
            case KeyEvent.VK_A:
                spaceShip.setDirLeft(true);
                break;
            case KeyEvent.VK_S:
                spaceShip.setDirDown(true);
                break;
            case KeyEvent.VK_D:
                spaceShip.setDirRight(true);
                break;
            case KeyEvent.VK_SPACE:
                spaceShip.setAttack(true);
                break;
            case KeyEvent.VK_P:
                game.getPaused().showPaused();
                break;
        }
    }

    /**
     * Sets the key released for spaceship direction, attack to false
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                spaceShip.setDirUp(false);
                break;
            case KeyEvent.VK_A:
                spaceShip.setDirLeft(false);
                break;
            case KeyEvent.VK_S:
                spaceShip.setDirDown(false);
                break;
            case KeyEvent.VK_D:
                spaceShip.setDirRight(false);
                break;
            case KeyEvent.VK_SPACE:
                spaceShip.setAttack(false);
                break;
        }
    }

    /**
     * @return spaceship
     */
    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

}
