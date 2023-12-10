package characters;

import java.awt.Graphics;

public class Meteoroid extends Entity {
    private float meteoriodSpeed = 0.2f;
    private int size;

    public Meteoroid(float x, float y, float width, float height) {
        super(x, y, width, height);

        if (width > util.Constants.meteoroidSmallSize + 100) {
            img = util.Tools.importImg("medium_meteoroid.png");
            size = 1;
        } else {
            img = util.Tools.importImg("small_meteoroid.png");
            size = 0;
        }
        
    }

    /**
     * Moves the meteoroid down the y axis
     */
    public void update() {
        this.y+=meteoriodSpeed;
        this.updateHitBox();
    }

    /** 
     * Draws the Meteoroid
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(img, (int) x, (int) y, (int)width, (int)height, null);
        drawHitBox(g);
    }

    /**
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * @return size
     */
    public int getSize() {
        return size;
    }
    
}
