package bl4ckscor3.game.minesweeper.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import bl4ckscor3.game.minesweeper.util.Utilities;

/**
 * Holds all possible tiles in the game. FLAG and UNREVEALED are only there to easier manage the textures, they shouldn't be used within the completely initialized TILES array
 */
public enum Tile
{
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), MINE, HIT, FLAG, EMPTY, UNKNOWN;

	private Image texture;
	private int val;

	private Tile()
	{
		this(-1);
	}

	/**
	 * Sets up this tile
	 * @param v The value of the tile. -1 if it doesn't have a value
	 */
	private Tile(int v)
	{
		texture = new ImageIcon(Utilities.getJarLocation() + name().toLowerCase() + ".png").getImage();
		val = v;
	}

	/**
	 * @return The value of this tile. -1 if it doesn't have a value
	 */
	public int getVal()
	{
		return val;
	}

	/**
	 * @return true if this tile is a numbered tile, false otherwise
	 */
	public boolean isNumber()
	{
		return val != -1;
	}

	/**
	 * Draws the {@link bl4ckscor3.game.minesweeper.game.Tile#UNREVEALED} tile to the
	 * screen
	 *
	 * @param g The graphics instance to draw with
	 * @param x The x position to draw the tile at
	 * @param y The y position to draw the tile at
	 */
	public static void drawFilled(Graphics g, int x, int y)
	{
		g.drawImage(UNKNOWN.texture, x, y, Game.TILE_SIZE, Game.TILE_SIZE, null);
	}

	/**
	 * Draws the {@link bl4ckscor3.game.minesweeper.game.Tile#FLAG} tile to the
	 * screen
	 *
	 * @param g The graphics instance to draw with
	 * @param x The x position to draw the tile at
	 * @param y The y position to draw the tile at
	 */
	public static void drawFlag(Graphics g, int x, int y)
	{
		g.drawImage(FLAG.texture, x, y, Game.TILE_SIZE, Game.TILE_SIZE, null);
	}

	/**
	 * Draws this {@link bl4ckscor3.game.minesweeper.game.Tile} to the screen
	 *
	 * @param g The graphics instance to draw with
	 * @param x The x position to draw the tile at
	 * @param y The y position to draw the tile at
	 */
	public void draw(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, Game.TILE_SIZE, Game.TILE_SIZE, null);
	}
}
