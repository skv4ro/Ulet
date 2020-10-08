package sk.juvius.ulet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public final class R {
    private static final ClassLoader cl = R.class.getClassLoader();
    private static final Image defImage = createDefImage();
    private static final Logger log = LoggerFactory.getLogger(R.class);

    private static Image createDefImage() {
        int width = 16, height = 16;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.black);
        g2d.fillOval(0, 0, width, height);
        g2d.setColor(Color.MAGENTA);
        g2d.drawString("U", 4, 13);
        return bufferedImage;
    }

    public static URL getResource(String path) {
        return cl.getResource(path);
    }

    public static String getResPath() {
        URL url = getResource("");
        if(url == null) {
            log.error("Classpath not set to resource dir.");
            return null;
        }
        return url.getPath();
    }

    public static Image getImage(String name) {
        try {
            String path = "image/" + name;
            InputStream is = cl.getResourceAsStream(path);
            Image image;
            if(is == null) {
                URL url = cl.getResource(path);
                if(url == null) {
                    imageNotFoundLog(name);
                    return defImage;
                } else {
                    image = ImageIO.read(url);
                }
            } else {
                image = ImageIO.read(is);
            }
            if(image == null) {
                imageNotFoundLog(name);
                return defImage;
            }
            return image;
        } catch (IOException e) {
            imageNotFoundLog(name);
            return defImage;
        }
    }

    private static void imageNotFoundLog(String notFoundImage) {
        log.warn("Image " + notFoundImage + " was not found. Using default image.");
    }
}
