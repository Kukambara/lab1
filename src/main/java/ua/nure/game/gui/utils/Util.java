package ua.nure.game.gui.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Utility functions for the application
 * 
 * @author Tomas Jakubco
 * 
 */
public class Util {

	/**
	 * Centers the window to the screen
	 * 
	 * @param window
	 *            - JFrame object
	 */
	public static void centerScreen(JFrame window) {

		/**
		 * Get the size of the screen
		 */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		/**
		 * Determine the new location of the window
		 */
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		/**
		 * Move the window
		 */
		window.setLocation(x, y);

	}
}
