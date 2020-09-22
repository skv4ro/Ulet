package sk.juvius.ulet.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public final class R {
    private static final ClassLoader cl = R.class.getClassLoader();
    private static final Image defImage = createDefImage();

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

    public static Image getImage(String name) {
        try {
            InputStream is = cl.getResourceAsStream("image/" + name);
            if(is == null) return defImage;
            Image image = ImageIO.read(is);
            if(image == null) return defImage;
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return defImage;
        }
    }
}
