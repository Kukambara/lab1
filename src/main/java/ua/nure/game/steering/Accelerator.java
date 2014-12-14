package ua.nure.game.steering;

import ua.nure.game.objects.GameObject;

/**
 * Accelerator is used for increase or decrease speed, slowdown or break down
 * etc.
 * 
 * @author Tomas Jakubco
 * 
 */
public class Accelerator extends Thread {

	/**
	 * Delay for increase / decrease speed
	 */
	private final int DELAY = 100;

	/**
	 * Max speed constant
	 */
	private static final int MAX_SPEED = 10;

	/**
	 * Constant for decrease speed
	 */
	private static final int BREAK_CONST = 2;

	/**
	 * State of the accelerator
	 * 
	 * @author Tomas Jakubco
	 * 
	 */
	public enum AccState {
		INCREASE_SPEED, DECREASE_SPEED, SLOW_DOWN, BREAK_DOWN, STOPPED
	}

	/**
	 * Reference to the state of accelerator
	 */
	private AccState state;

	/**
	 * Reference to ua.nure.game object which is accelerated
	 */
	GameObject obj;

	/**
	 * Speed of the object
	 */
	private double speed;

	/**
	 * Constructor
	 * 
	 * @param obj
	 *            - GameObject
	 */
	public Accelerator(GameObject obj) {
		this.obj = obj;
		this.state = AccState.STOPPED;
	}

	/**
	 * Returns actual speed
	 * 
	 * @return speed
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Sets accelerator's state
	 * 
	 * @param state
	 */
	public void setState(AccState state) {
		this.state = state;
	}

	/**
	 * Increase speed
	 */
	private void increaseSpeed() {
		if (speed < MAX_SPEED) {
			speed++;
		}
	}

	/**
	 * Decrease speed
	 */
	private void decreaseSpeed() {
		if (speed > -MAX_SPEED) {
			speed--;
		}
	}

	/**
	 * Slow down
	 */
	private void slowDown() {
		if (speed > 0) {
			speed--;
		} else if (speed < 0) {
			speed++;
		}
	}

	/**
	 * Break down
	 */
	private void breakDown() {
		if (speed > 0) {
			speed -= BREAK_CONST;
			if (speed < 0) {
				speed = 0;
			}
		}
		if (speed < 0) {
			speed += BREAK_CONST;
			if (speed > 0) {
				speed = 0;
			}
		}
	}

	/**
	 * 
	 */
	private void immediateStop() {
		speed = 0;
	}

	@Override
	public void run() {

		/**
		 * Game update-rate stuff
		 */
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();

		/**
		 * Infinity cycle
		 */
		while (true) {
			switch (state) {
			case INCREASE_SPEED:
				increaseSpeed();
				break;
			case DECREASE_SPEED:
				decreaseSpeed();
				break;
			case SLOW_DOWN:
				slowDown();
				break;
			case BREAK_DOWN:
				breakDown();
				break;
			case STOPPED:
				immediateStop();
				break;
			default:
				break;
			}

			/**
			 * Calculates ua.nure.game update-rate
			 */
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			if (sleep < 0)
				sleep = 2;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
			beforeTime = System.currentTimeMillis();
		}
	}

}
