package ua.nure.game.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

public class Camera {

    private int x;
    private int y;
    private int width;
    private int height;
    private int cameraPixelSize;

    private int[] pixelsVector;

    private CameraRectangle cameraRectangle;

    public Camera(Car car, int cameraPixelSize) {
        this.cameraPixelSize = cameraPixelSize;
        width = (int) (car.width * 7);
        height = (int) (car.height * 3.5);

        System.out.println(width * height);
        cameraRectangle = new CameraRectangle(x, y, width, height, 0);
        calculateCameraPosition(car);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCameraPixelSize() {
        return cameraPixelSize;
    }

    public void setCameraPixelSize(int cameraPixelSize) {
        this.cameraPixelSize = cameraPixelSize;
    }

    public void calculateCameraPosition(Car car) {
        double x = car.x + car.width / 2;
        double centerY = car.y + car.height / 2;
        double y = centerY + car.getHeight() / 3;
        this.x = (int) (x - width / 2);
        this.y = (int) (y - height / 2);
        cameraRectangle.x = this.x;
        cameraRectangle.y = this.y;

    }


    public ArrayList<Double> getPixels(Image image) {

        ArrayList<Double> resultVector = new ArrayList<Double>();
        if (image == null) {
            return resultVector;
        }

        if (image.getWidth(null) == -1 || image.getHeight(null) == -1) {
            return resultVector;
        }
        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);

        // Draw the image on to the buffered image
        Graphics2D bGr = bi.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();


        final byte[] pixels = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();

        int[][] result = new int[900][1800];
        final int pixelLength = 3;
        for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += -16777216; // 255 alpha
            argb += ((int) pixels[pixel] & 0xff); // blue
            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            result[row][col] = argb;
            col++;
            if (col == 1800) {
                col = 0;
                row++;
            }
        }


        int pointer = 0;
        int step = 7;
        int di = width / step;
        int dy = height / step;
        for (int i = x; i < x+width; i += di) {
            for (int j = y; j < y+height; j += dy) {
                resultVector.add((double) result[j][i]);
            }
        }

        return resultVector;
    }

    public void paint(Graphics2D g) {
        cameraRectangle.paint(g);
    }
}
