package util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Tools {
    /**
     * Imports a given image given the name of the image
     * within the directory /src/resources folder.
     * 
     * @param imgName
     * @return BufferedImage type
     */
    public static BufferedImage importImg(String imgName) {
        try (InputStream is = Tools.class.getResourceAsStream("/resources/" + imgName);) {
            return ImageIO.read(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
