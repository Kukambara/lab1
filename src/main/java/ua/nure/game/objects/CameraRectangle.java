package ua.nure.game.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CameraRectangle {

    public double x, y, width, height, angle;
    Image image;

    public CameraRectangle(double x, double y, double width, double height, double angle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        setTexture();
    }


    public void setTexture() {
        BufferedImage img = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
        final Graphics graphics = img.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawLine(0, 0, (int) (width - 1), 0);
        graphics.drawLine((int) (width - 1), 0, (int) (width - 1), (int) (height - 1));
        graphics.drawLine((int) (width - 1), (int) (height - 1), 0, (int) (height - 1));
        graphics.drawLine(0, (int) (height - 1), 0, 0);

        image = img;
    }

    public void paint(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}
