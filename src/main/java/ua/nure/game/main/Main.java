package ua.nure.game.main;

import ua.nure.game.gui.GameWindow;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameWindow();
			}
		});
	}
}
