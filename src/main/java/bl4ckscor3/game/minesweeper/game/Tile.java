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
	ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINE, HIT, FLAG, EMPTY, UNKNOWN;

	private Image texture;

	private Tile()
	{
		texture = new ImageIcon(Utilities.getJarLocation() + "/src/main/resources/" + name().toLowerCase() + ".png").getImage();
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
