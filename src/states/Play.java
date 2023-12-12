package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;

import characters.Meteoroid;
import characters.SpaceShip;
import main.StartGame;
import util.MusicPlayer;

public class Play extends State {

    protected SpaceShip spaceShip;
    private List<Meteoroid> meteoroids = new CopyOnWriteArrayList<>();
    private long lastCheck = System.currentTimeMillis();
    private int points = 0;
    private int lastTrackedPoints = points;
    protected JLabel pointsLabel = new JLabel("Points: 0");
    private float meteoriodsSpeed = util.Constants.METEOROID_DEFAULT_SPEED;
    private int meteoroidFrequency = util.Constants.METEOROID_DEFAULT_FREQUENCY;

    public Play(StartGame game) {
        super(game);
        init();
    }

    /**
     * Initializes spaceship, points label, and meteoroid generation start
     */
    private void init() {
        spaceShip = new SpaceShip(util.Constants.SPACESHIP_SPAWN_X, util.Constants.SPACESHIP_SPAWN_Y,
                util.Constants.SPACESHIP_SIZE, util.Constants.SPACESHIP_SIZE);
        createMeteoroids();

        pointsLabel.setBounds(10, (int) (util.Constants.PANEL_HEIGHT - util.Constants.POINTS_LABEL_HEIGHT),
                (int) (util.Constants.PANEL_WIDTH), util.Constants.POINTS_LABEL_HEIGHT);
        pointsLabel.setVisible(false);
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setFont(util.Constants.MEDIUM_SIZE_FONT);
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
            
            meteoroidSize = random.nextInt(util.Constants.METEOROID_MEDIUM_SIZE) + util.Constants.METEOROID_SMALL_SIZE;

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
            x = random.nextInt(util.Constants.PANEL_WIDTH);
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

        return new Meteoroid(x, y, size, size, meteoriodsSpeed);

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
        return x + size > util.Constants.PANEL_WIDTH || y + size > util.Constants.PANEL_HEIGHT;
    }

    /**
     * Call update for the spaceship and meteoroids.
     * Here we do the timing for creating meteoroids.
     */
    @Override
    public void update() {
        spaceShip.update();

        // Increase speed/frequency if matched
        if (points - lastTrackedPoints > util.Constants.POINTS_THRESHOLD) {
            if (meteoriodsSpeed < util.Constants.METEOROID_MAX_SPEED) {
                meteoriodsSpeed *= 2;
                meteoroids.forEach(m -> m.increaseExistingMeteoroidSpeed());
            }

            if (meteoriodsSpeed > 1 && meteoriodsSpeed <= util.Constants.METEOROID_MAX_SPEED && meteoroidFrequency > util.Constants.METEOROID_MIN_FREQUENCY) {
                meteoroidFrequency/=2;

                getSpaceShip().upgradeBulletFrequency();
            }

            lastTrackedPoints = points;
        }

        // Update each meteoroid
        meteoroids.forEach(m -> m.update());

        // Create more meteoroids
        if (System.currentTimeMillis() - lastCheck >= meteoroidFrequency) {
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
                GameState.setGameStateGameOver();
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
        meteoroids.removeIf(meteoroid -> meteoroid.getY() > util.Constants.PANEL_HEIGHT);

        for (Meteoroid m : meteoroids) {
            if (spaceShip.getBullets().removeIf(bullet -> bullet.getHitBox().intersects(m.getHitBox()))) {
                if (m.getSize() == 0) {
                    points++;
                } else {
                    points += 2;
                }
        
                pointsLabel.setText("Points: " + points);
                meteoroids.remove(m);
            }
        }
    }

    /**
     * Sets the game state for playing. Can be called on first start or when
     * restarting from other game states.
     */
    public void restart() {
        // Reset game state and elements
        pointsLabel.setBounds(10, (int) (util.Constants.PANEL_HEIGHT - util.Constants.POINTS_LABEL_HEIGHT),
                (int) (util.Constants.PANEL_WIDTH), util.Constants.POINTS_LABEL_HEIGHT);
        points = 0;
        lastTrackedPoints = 0;
        meteoriodsSpeed = util.Constants.METEOROID_DEFAULT_SPEED;
        meteoroidFrequency = util.Constants.METEOROID_DEFAULT_FREQUENCY;
        pointsLabel.setText("Points: 0");
        meteoroids.removeIf(m -> true);
        spaceShip.resetShip();
        GameState.setGameStatePlay();

        // Restart music
        MusicPlayer.stop();
        MusicPlayer.playStartWhistle("Start.mid");
        MusicPlayer.playMusicLoop("Playing.mid");
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
