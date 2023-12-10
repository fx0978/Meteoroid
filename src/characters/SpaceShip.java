package characters;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class SpaceShip extends Entity {
    private boolean left, up, down, right, attack;
    private float playerSpeed = 2.0f;
    private long currentTime = System.currentTimeMillis();
    private long previousTime = currentTime;

    private List<Bullet> bullets = new ArrayList<>();

    public SpaceShip(float x, float y, float width, float height) {
        super(x, y, width, height);
        img = util.Tools.importImg("space_ship.png");
    }

    /**
     * Calls update to re-draw spaceship and any bullets
     */
    public void update() {
        updateSpaceShip();
        updateHitBox();
        for (Bullet b : bullets) {
            b.update();
        }
    }

    /**
     * @return bullets List
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Metod to draw the updated coordinates when the spaceship moves 
     * and when it fires a bullet create the bullet object.
     */
    private void updateSpaceShip() {
        if (this.left && !this.right && !isOutOfBoundsX()){
            x-=playerSpeed;
        } else if (this.right && !this.left && !isOutOfBoundsX()) {
            x+=playerSpeed;
        }

        if (this.up && !this.down && !isOutOfBoundsY()) {
            y-=playerSpeed;
        } else if (this.down && !this.up && !isOutOfBoundsY()) {
            y+=playerSpeed;
        }

        // Check if new bullet needs to be created
        currentTime = System.currentTimeMillis();
        if (this.attack && ((currentTime - previousTime) >= util.Constants.bulletFrequency)) {
            bullets.add(new Bullet(this.x, this.y, util.Constants.bulletSize, util.Constants.bulletSize));
            previousTime = currentTime;
        }
    }

    /**
     * Checks if the spaceship's x axis is out of bounds
     * @return Boolean
     */
    private Boolean isOutOfBoundsX(){
        if (this.left && x <= 0) {
            return true;
        } else if (this.right && x + util.Constants.spaceShipSize >= util.Constants.panelWidth) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the spaceship's y axis is out of bounds
     * @return Boolean
     */
    private Boolean isOutOfBoundsY(){
        if (this.up && y + util.Constants.spaceShipTopOffset <= 0) {
            return true;
        } else if (this.down && y + util.Constants.spaceShipSize - util.Constants.spaceShipBottomOffset > util.Constants.panelHeight) {
            return true;
        }
        return false;
    }

    
    /** 
     * Draws the bullet image, checks if bullet has passed the threshold 
     * and if so remove the bullet.
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(img, (int) x, (int) y, (int)width, (int)height, null);
        drawHitBox(g);
        bullets.removeIf(bullet -> bullet.y+bullet.height <= 0);
        for (Bullet b : bullets) {
            b.render(g);
        }
    }

    /**
     * Sets the spaceship's right direction.
     * Referenced when moving the spaceship right.
     * @param b
     */
    public void setDirRight(boolean b) {
        this.right = b;
    }

    /**
     * Sets the spaceship's left direction.
     * Referenced when moving the spaceship left.
     * @param b
     */
    public void setDirLeft(boolean b) {
        this.left = b;
    }

    /**
     * Sets the spaceship's up direction.
     * Referenced when moving the spaceship up. 
     * @param b
     */
    public void setDirUp(boolean b) {
        this.up = b;
    }


    /**
     * Sets the spaceship's down direction.
     * Referenced when moving the spaceship down.
     * @param b
     */
    public void setDirDown(boolean b) {
        this.down = b;
    }

    /**
     * Sets the spaceship's attack status.
     * Referenced when creating the bullet object
     * @param b
     */
    public void setAttack(boolean b) {
        this.attack = b;
    }

    /**
     * Sets all spaceship statuses (moving direction, attacking, etc.) to false.
     */
    public void stopShip() {
        this.right = false;
        this.left = false;
        this.up = false;
        this.down = false;
        this.attack = false;
    }

    /**
     * Stops the ship and resets the spaceship/bullets
     */
    public void resetShip() {
        stopShip();
        bullets.removeIf(bullet -> true);
        this.x = util.Constants.spaceShipSpawnX;
        this.y = util.Constants.spaceShipSpawnY;
    }
}
