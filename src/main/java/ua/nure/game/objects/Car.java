package ua.nure.game.objects;

import org.neuroph.core.data.DataSetRow;
import ua.nure.game.steering.Steerable;
import ua.nure.game.steering.Steering;

import java.awt.*;
import java.util.ArrayList;

/**
 * Car class
 *
 * @author Tomas Jakubco
 */
public class Car extends GameObject implements Steerable {

    /**
     * Default serial version
     */
    private static final long serialVersionUID = 1L;

    Camera camera;
    Image image;

    /**
     * Steering object is used for Car steering
     */
    private Steering steering;

    /**
     * Constructor
     *
     * @param x      - Position X of car
     * @param y      - Position Y of car
     * @param width  - Width of car
     * @param height - Height of car
     * @param angle  - Angle of car
     */
    public Car(double x, double y, double width, double height, double angle, Image image) {
        super(x, y, width, height, angle);
        this.image = image;
        camera = new Camera(this, 1);
        setTexture("/resources/car.png");
        steering = new Steering(this);
    }

    /**
     * Overriden method from super class
     * <p/>
     * Used for steering
     */
    @Override
    public void steer() {
        double oldSpeed = steering.accelerator.getSpeed();
        double oldAngle = a;

        steering.steer();
        camera.calculateCameraPosition(this);
        ArrayList<java.lang.Double> input = camera.getPixels(image);
        input.add(oldSpeed);
        input.add(oldAngle);
        System.out.println(input.size());

        DataSetRow dataSetRow = new DataSetRow(input, new ArrayList<java.lang.Double>() {{
            add(steering.accelerator.getSpeed());
            add(a);
        }});
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void paint(Graphics2D g2d) {
        super.paint(g2d);

        // Paint angle information
        String strX = String.format("%.1f", x);
        String strY = String.format("%.1f", y);
        String strA = String.format("%.1f", a);
        g2d.setFont(new Font("Courier New", 15, 15));
        g2d.drawString("X: " + strX + ";   Y: " + strY + ";   Angle: " + strA
                + "�", 10, 20);

        camera.paint(g2d);


    }
}
