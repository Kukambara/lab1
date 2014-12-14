package ua.nure.game.objects;

import ua.nure.game.gui.Paintable;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used for holds references to the ua.nure.game objects
 *
 * @author Tomas Jakubco
 */
public class ObjectHolder extends ArrayList<GameObject> implements Paintable {

    /**
     * Default serial version
     */
    private static final long serialVersionUID = 1L;

    /**
     * Reference to car object
     */
    Car actor;
    Image image;

    /**
     * Default constructor
     * <p/>
     * Create objects
     */
    public ObjectHolder(Image image) {
        this.image = image;
        createObjects();
    }

    /**
     * Create instances of all ua.nure.game objects
     */
    private void createObjects() {
        actor = new Car(400, 200, 75 / 2, 75, 90, image);
    }

    /**
     * Overriden paint method from super
     */
    @Override
    public void paint(Graphics2D g2d) {

        for (GameObject obj : this) {
            obj.paint(g2d);
        }

        actor.paint(g2d);

    }

    /**
     * Update position of the objects
     */
    public void update() {
        actor.steer();

        //TODO update camera, update length detector.

    }

}
