package ua.nure.game.objects;

import ua.nure.game.gui.Paintable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.net.URL;

/**
 * This is top parent class for ua.nure.game objects
 *
 * @author Tomas Jakubco
 */
public class GameObject extends Rectangle2D.Double implements Paintable {

    /**
     * Default serial version
     */
    private static final long serialVersionUID = 1L;

    /**
     * Represents angle of the object
     */
    public double a;

    /**
     * Represents texture of the object
     */
    private Image texture;

    /**
     * Transformation of object
     */
    protected AffineTransform at;

    /**
     * Default transformation
     */
    protected AffineTransform dt;

    /**
     * Constructor
     *
     * @param x      - Position X of ua.nure.game object
     * @param y      - Position Y of ua.nure.game object
     * @param width  - Width of ua.nure.game object
     * @param height - Height of ua.nure.game object
     * @param angle  - Angle of ua.nure.game object
     */
    public GameObject(double x, double y, double width, double height,
                      double angle) {
        /**
         * Initialize properties
         */
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.a = angle;

        /**
         * Create new transformation
         */
        at = new AffineTransform();
        dt = new AffineTransform();
    }

    /**
     * Set texture for the object
     *
     * @param path
     */
    public void setTexture(String path) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        URL imgRes = getClass().getResource(path);
        texture = toolkit.getImage(imgRes);

        ImageFilter filter = new RGBImageFilter() {

            // public int markerRGB = color.getRGB() | 0xFFFFFFFF;
            public int markerRGB = 0 | 0xFFFFFFFF;

            @Override
            public int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };
        ImageProducer ip = new FilteredImageSource(texture.getSource(), filter);

        texture = toolkit.createImage(ip);
    }

    public void setTexture(Image image) {
        texture = image;
    }

    /**
     * Paint component
     */
    @Override
    public void paint(Graphics2D g2d) {

        at = AffineTransform.getRotateInstance(Math.toRadians(a), getCenterX(),
                getCenterY() + (getHeight() / 3));

        dt = (AffineTransform) g2d.getTransform().clone();

        g2d.setTransform(at);
        g2d.drawImage(texture, (int) x, (int) y, (int) width, (int) height,
                null);
        g2d.setTransform(dt);
    }

}
