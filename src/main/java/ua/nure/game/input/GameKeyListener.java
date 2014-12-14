package ua.nure.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listen keys from keyboard
 * 
 * @author Tomas Jakubco
 * 
 */
public class GameKeyListener implements KeyListener {

	/**
	 * Reference to KeyList
	 */
	KeyList keylist;

	/**
	 * Default constructor
	 */
	public GameKeyListener() {
		this.keylist = KeyList.getInstance();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/**
		 * Add pressed key to the list
		 */
		keylist.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/**
		 * Remove released key from the list
		 */
		keylist.remove(e.getKeyCode());
	}

}
