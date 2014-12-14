package ua.nure.game.gui;

import ua.nure.game.input.GameKeyListener;
import ua.nure.game.objects.ObjectHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.net.URL;

/**
 * GameCanvas is used to hold all the objects and graphics of the ua.nure.game.
 *
 * @author Tomas Jakubco
 */
public class GameCanvas extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Holds all the object of the ua.nure.game
     */
    ObjectHolder objHolder;

    Image texture;

    /**
     * Reference to graphics tool
     */
    Graphics2D g2d;

    /**
     * Default constructor
     * <p/>
     * Initialize objects and set layout
     */
    public GameCanvas() {

        /**
         * Add key listener to this canvas
         */
        addKeyListener(new GameKeyListener());

        /**
         * Set properties and layout
         */
        setFocusable(true);
        requestFocus();
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        URL imgRes = getClass().getResource("/resources/road.png");
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

        /**
         * Create new instance of objHolder
         */
        objHolder = new ObjectHolder(texture);



    }

    @Override
    protected void paintComponent(Graphics g) {

        /**
         * Cast to 2d graphics tool
         */
        Graphics2D g2d = (Graphics2D) g;

        /**
         * Create rendering hints for better rendering
         */
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        /**
         * Paint super component
         */


        super.paintComponent(g2d);

        g2d.drawImage(texture, 0, 0, 1800, 900, null);

        /**
         * Paint all objects in object holder
         */
        objHolder.paint(g2d);

        /**
         * Dispose graphics tool
         */
        g2d.dispose();

    }

    @Override
    public void addNotify() {

        super.addNotify();

        /**
         * Execute updater thread
         */
        new Updater(objHolder, this).start();
    }

}
