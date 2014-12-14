package ua.nure.game.gui;

import ua.nure.game.objects.ObjectHolder;

/**
 * Updater updates all the ua.nure.game objects and repaint
 * 
 * @author jakubco
 * 
 */
public class Updater extends Thread {

	/**
	 * Delay for updater
	 */
	private final int DELAY = 25;

	/**
	 * Reference to object holder
	 */
	ObjectHolder objHolder;

	/**
	 * Reference to ua.nure.game canvas
	 */
	GameCanvas canvas;

	/**
	 * Constructor
	 * 
	 * @param objHolder
	 *            - ObjectHolder
	 * @param canvas
	 *            - GameCanvas
	 */
	public Updater(ObjectHolder objHolder, GameCanvas canvas) {
		this.objHolder = objHolder;
		this.canvas = canvas;
	}

	/**
	 * Update method
	 * 
	 * Updates the object holder and repaint all components
	 */
	private void update() {
		objHolder.update();
		canvas.repaint();
	}

	/**
	 * Run method
	 */
	@Override
	public void run() {

		/**
		 * Game update-rate stuff
		 */
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();

		while (true) {

			update();

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
