package ua.nure.game.steering;

import ua.nure.game.input.KeyList;
import ua.nure.game.objects.GameObject;
import ua.nure.game.steering.Accelerator.AccState;

import java.awt.Component;

/**
 * Steering is class which performs steering of the object
 * 
 * @author Tomas Jakubco
 * 
 */
public class Steering extends Component {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Reference to the ua.nure.game object
	 */
	GameObject obj;

	/**
	 * Reference to accelerator
	 */
	public Accelerator accelerator;

	/**
	 * Stores the last key pressed
	 */
	int lastKey;

	/**
	 * Constructor
	 * 
	 * @param obj
	 *            - GameObject
	 */
	public Steering(GameObject obj) {
		this.obj = obj;
		accelerator = new Accelerator(obj);
		accelerator.start();
	}

	/**
	 * Steer method groups other partial methods
	 */
	public void steer() {

		speedUp();

		slowDown();

		setAngle();

		performMove();

	}

	/**
	 * If UP-arrow or DOWN-arrow key is pressed, increases the speed
	 */
	private void speedUp() {

		if (KeyList.up()) {
			if (accelerator.getSpeed() < 0) {
				accelerator.setState(AccState.BREAK_DOWN);
			} else {
				accelerator.setState(AccState.INCREASE_SPEED);
			}
			lastKey = 1;
		}

		if (KeyList.down()) {
			if (accelerator.getSpeed() > 0) {
				accelerator.setState(AccState.BREAK_DOWN);
			} else {
				accelerator.setState(AccState.DECREASE_SPEED);
			}
			lastKey = 2;
		}

	}

	/**
	 * If nothing is pressed, slows the vehicle down
	 */
	private void slowDown() {

		if (!KeyList.up() && !KeyList.down() && !KeyList.space()) {
			accelerator.setState(AccState.SLOW_DOWN);
		}
	}

	/**
	 * Angle correction
	 */
	private void angleCorrection() {

		if (obj.a > 360) {
			obj.a = 0;
		} else if (obj.a < 0) {
			obj.a = 360;
		}

		obj.a = Math.abs(obj.a);

	}

	/**
	 * Sets the angle of vehicle
	 */
	private void setAngle() {

		if ((accelerator.getSpeed() != 0) && KeyList.left()) {
			obj.a += -accelerator.getSpeed() / 3;
		}

		if ((accelerator.getSpeed() != 0) && KeyList.right()) {
			obj.a += accelerator.getSpeed() / 3;
		}

		angleCorrection();

	}

	/**
	 * Move calculation of the vehicle
	 */
	private void performMove() {

		if (lastKey == 1 || lastKey == 2) {

			double cx = accelerator.getSpeed()
					* Math.abs(Math.sin(Math.toRadians(obj.a)));
			double cy = accelerator.getSpeed()
					* Math.abs(Math.cos(Math.toRadians(obj.a)));

			// Move north-west
			if ((obj.a > 270 && obj.a < 360) || obj.a == 0) {
				obj.x += -cx;
				obj.y += -cy;
			}

			// Move north-east
			if ((obj.a > 0 && obj.a < 90)) {
				obj.x += cx;
				obj.y += -cy;
			}

			// Move south-west
			if ((obj.a > 180 && obj.a < 270)) {
				obj.x += -cx;
				obj.y += cy;
			}

			// Move south-east
			if ((obj.a > 90 && obj.a < 180)) {
				obj.x += cx;
				obj.y += cy;
			}

		}
	}
}
