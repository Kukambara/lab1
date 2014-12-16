package ua.nure.game.input;

import java.awt.event.KeyEvent;
import java.util.HashSet;

/**
 * This class is used for hold pressed keys and release released keys
 * 
 * @author Tomas Jakubco
 * 
 */
public class KeyList extends HashSet<Integer> {

	private static final long serialVersionUID = 1L;

	/**
	 * Store instance of KeyList
	 */
	private static final KeyList keylist = new KeyList();

	/**
	 * Private constructor
	 */
	private KeyList() {
	}

	/**
	 * Returns the instance of KeyList
	 * 
	 * @return KeyList
	 */
	public static KeyList getInstance() {
		return keylist;
	}

	/**
	 * Returns if left-arrow key is pressed
	 * 
	 * @return Boolean
	 */
	public static boolean left() {
		return keylist.contains(KeyEvent.VK_LEFT);
	}

	/**
	 * Returns if right-arrow key is pressed
	 * 
	 * @return Boolean
	 */
	public static boolean right() {
		return keylist.contains(KeyEvent.VK_RIGHT);
	}

	/**
	 * Returns if up-arrow key is pressed
	 * 
	 * @return Booelan
	 */
	public static boolean up() {
		return keylist.contains(KeyEvent.VK_UP);
	}

	/**
	 * Returns if down-arrow key is pressed
	 * 
	 * @return Boolean
	 */
	public static boolean down() {
		return keylist.contains(KeyEvent.VK_DOWN);
	}

	/**
	 * Returns if shift key is pressed
	 * 
	 * @return Boolean
	 */
	public static boolean shift() {
		return keylist.contains(KeyEvent.VK_SHIFT);
	}

	/**
	 * Returns if space key is pressed
	 * 
	 * @return Boolean
	 */
	public static boolean space() {
		return keylist.contains(KeyEvent.VK_SPACE);
	}


	public static boolean cancel(){
		return keylist.contains(KeyEvent.VK_ESCAPE);
	}
}
