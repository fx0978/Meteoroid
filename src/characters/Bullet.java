package characters;

import java.awt.Graphics;
import java.awt.Rectangle; // For debugging

public class Bullet extends Entity {
    private float bulletSpeed = 5.0f;

    Bullet(float x, float y, float width, float height) {
        super(x, y, width, height);

        img = util.Tools.importImg("bullet.png");
    }

    /**
     * Calls updateBullet
     */
    public void update() {
        updateBullet();
    }

    /**
     * render the bullet graphic
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(img, (int)x+util.Constants.BULLET_CENTER_TO_SHIP, (int)y, (int)width, (int)height, null);
        drawHitBox(g);
    }

    /**
     * Updates the bullet y axis. Note: bullets only move up!
     */
    public void updateBullet() {
        this.y-=bulletSpeed;
        this.updateHitBox();
    }

    /**
     * Overrides the updateHitBox method in entity to adjust the hitbox with the bullet as it moves
     */
    public void updateHitBox() {
        hitBox.x = (int)(x+util.Constants.BULLET_CENTER_TO_SHIP);
        hitBox.y = (int) y;
    }
}
