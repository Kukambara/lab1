package ua.nure.game.gui;

import static ua.nure.game.gui.utils.Util.centerScreen;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;

/**
 * Main window of the ua.nure.game
 * 
 * @author Tomas Jakubco
 * 
 */
public class GameWindow {

	private JFrame frmSimpleGame;
	private GameCanvas canvas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frmSimpleGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimpleGame = new JFrame();
		frmSimpleGame.setTitle("Simple Game");
		frmSimpleGame.setBounds(100, 100, 800, 600);
		frmSimpleGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		centerScreen(frmSimpleGame);

		canvas = new GameCanvas();
		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(
				frmSimpleGame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(12)
						.addComponent(canvas, GroupLayout.DEFAULT_SIZE, 422,
								Short.MAX_VALUE).addGap(12)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(12)
						.addComponent(canvas, GroupLayout.DEFAULT_SIZE, 249,
								Short.MAX_VALUE).addGap(12)));
		frmSimpleGame.getContentPane().setLayout(groupLayout);

		frmSimpleGame.setVisible(true);
		
	}

}
