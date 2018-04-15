package bl4ckscor3.game.minesweeper.game;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import bl4ckscor3.game.minesweeper.listener.Mouse;

public class Game extends JPanel
{
	private static final long serialVersionUID = 1654230156331372138L;
	private static final Random r = new Random();
	public static final int BOARD_X = 30;
	public static final int BOARD_Y = 30;
	public static final int START_X = 50;
	public static final int START_Y = 50;
	public static final int BOARD_SIZE = BOARD_X * BOARD_Y;
	/** X and Y length in pixels */
	public static final int TILE_SIZE = 20;
	public static final float MINE_PERCENTAGE = 0.1F;
	public static final float MINE_AMOUNT = BOARD_SIZE * MINE_PERCENTAGE;
	public static Tile[][] TILES = new Tile[BOARD_X][BOARD_Y];
	public static TileState[][] TILE_STATES = new TileState[BOARD_X][BOARD_Y];
	public static boolean ended = false;

	public Game()
	{
		addMouseListener(new Mouse());
		reset();
	}

	/**
	 * Makes a new field of mines
	 */
	public void reset()
	{
		TILES = new Tile[BOARD_X][BOARD_Y];
		TILE_STATES = new TileState[BOARD_X][BOARD_Y];

		int minesSoFar = 0;

		for(int row = 0; row < TILES.length; row++)
		{
			for(int col = 0; col < TILES[row].length; col++)
			{
				boolean mine = r.nextInt(BOARD_SIZE) < MINE_AMOUNT;

				if(mine && minesSoFar < MINE_AMOUNT)
				{
					TILES[row][col] = Tile.MINE;
					minesSoFar++;
				}

				TILE_STATES[row][col] = TileState.UNREVEALED;
			}
		}

		for(int row = 0; row < TILES.length; row++)
		{
			for(int col = 0; col < TILES[row].length; col++)
			{
				if(TILES[row][col] == Tile.MINE)
					continue;

				int adjacentMines = 0;

				if(row - 1 >= 0)
				{
					if(col - 1 >= 0 && TILES[row - 1][col - 1] == Tile.MINE)
						adjacentMines++;

					if(TILES[row - 1][col] == Tile.MINE)
						adjacentMines++;

					if(col + 1 < TILES.length && TILES[row - 1][col + 1] == Tile.MINE)
						adjacentMines++;
				}

				if(col - 1 >= 0 && TILES[row][col - 1] == Tile.MINE)
					adjacentMines++;

				if(col + 1 < TILES.length && TILES[row][col + 1] == Tile.MINE)
					adjacentMines++;

				if(row + 1 < TILES.length)
				{
					if(col - 1 >= 0 && TILES[row + 1][col - 1] == Tile.MINE)
						adjacentMines++;

					if(TILES[row + 1][col] == Tile.MINE)
						adjacentMines++;

					if(col + 1 < TILES.length && TILES[row + 1][col + 1] == Tile.MINE)
						adjacentMines++;
				}

				switch(adjacentMines)
				{
					case 0: TILES[row][col] = Tile.EMPTY; break;
					case 1: TILES[row][col] = Tile.ONE; break;
					case 2: TILES[row][col] = Tile.TWO; break;
					case 3: TILES[row][col] = Tile.THREE; break;
					case 4: TILES[row][col] = Tile.FOUR; break;
					case 5: TILES[row][col] = Tile.FIVE; break;
					case 6: TILES[row][col] = Tile.SIX; break;
					case 7: TILES[row][col] = Tile.SEVEN; break;
					case 8: TILES[row][col] = Tile.EIGHT; break;
				}
			}
		}

		ended = false;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		for(int row = 0; row < TILE_STATES.length; row++)
		{
			for(int col = 0; col < TILE_STATES[row].length; col++)
			{
				if(TILE_STATES[row][col] == null)
					continue;

				switch(TILE_STATES[row][col])
				{
					case UNREVEALED: Tile.drawFilled(g, row * Game.TILE_SIZE + START_X, col * Game.TILE_SIZE + START_Y); break;
					case FLAGGED: Tile.drawFlag(g, row * Game.TILE_SIZE + START_X, col * Game.TILE_SIZE + START_Y); break;
					case REVEALED: TILES[row][col].draw(g, row * Game.TILE_SIZE + START_X, col * Game.TILE_SIZE + START_Y); break;
				}
			}
		}
	}
}
