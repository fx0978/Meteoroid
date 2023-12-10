package util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
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

    /**
     * Created with help from Chat GPT
     * @param path
     * @return font of the file in the given path
     */
    public static Font getFont(String path) {
        try {
            File fontFile = new File(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            return font;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Font("Serif", Font.PLAIN, 12);
        }
    }

}
