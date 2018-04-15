package bl4ckscor3.game.minesweeper;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bl4ckscor3.game.minesweeper.game.Game;

public class Minesweeper
{
	public static Game game;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Minesweeper();
			}
		});
	}

	public Minesweeper()
	{
		JFrame frame = new JFrame();

		frame.setTitle("GameDev");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		game = new Game();
		frame.add(game);
		frame.setVisible(true);
	}
}
