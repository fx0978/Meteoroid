package characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected float x, y, width, height;
    protected Rectangle hitBox;
    BufferedImage img;
    
    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox();
    }

    // Debugging hit box
    protected void drawHitBox(Graphics g) {
        // g.setColor(Color.PINK);
        // g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    /**
     * Initializes the hitboxes with the same axis as the entity
     */
    private void initHitBox() {
        hitBox = new Rectangle((int)x, (int)y, (int) width, (int) height);
    }

    /**
     * Updates the axis of the hitbox to be same as the entity
     */
    protected void updateHitBox() {
        hitBox.x = (int) x;
        hitBox.y = (int) y;

    }

    /**
     * @return hitBox
     */
    public Rectangle getHitBox() {
        return hitBox;
    }
}
