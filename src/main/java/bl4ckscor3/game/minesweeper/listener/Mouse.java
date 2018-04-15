package bl4ckscor3.game.minesweeper.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bl4ckscor3.game.minesweeper.Minesweeper;
import bl4ckscor3.game.minesweeper.game.Game;
import bl4ckscor3.game.minesweeper.game.Tile;
import bl4ckscor3.game.minesweeper.game.TileState;

public class Mouse implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent event) {}

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mousePressed(MouseEvent event)
	{
		if(event.getButton() == 2 || Game.ended)
		{
			Minesweeper.game.reset();
			return;
		}

		int row = (event.getX() - Game.START_X) / Game.TILE_SIZE;
		int col = (event.getY() - Game.START_Y) / Game.TILE_SIZE;

		if(row < 0 || row >= Game.BOARD_X || col < 0 || col >= Game.BOARD_Y || Game.TILE_STATES[row][col] == TileState.REVEALED)
			return;

		TileState tileState = Game.TILE_STATES[row][col];

		if(event.getButton() == 1) //lmb
		{
			if(tileState == TileState.UNREVEALED)
			{
				Tile tile = Game.TILES[row][col];

				if(tile == Tile.MINE) //losing the game
				{
					Game.TILES[row][col] = Tile.HIT;

					for(int r = 0; r < Game.TILES.length; r++)
					{
						for(int c = 0; c < Game.TILES[r].length; c++)
						{
							if(Game.TILES[r][c] == Tile.MINE)
								Game.TILE_STATES[r][c] = TileState.REVEALED;
						}
					}

					Game.ended = true;
					Game.TILE_STATES[row][col] = TileState.REVEALED;
				}
				else if(tile == Tile.EMPTY)
					clearRecursive(row, col);
				else
					Game.TILE_STATES[row][col] = TileState.REVEALED;
			}
		}
		else if(event.getButton() == 3) //rmb
			Game.TILE_STATES[row][col] = tileState == TileState.UNREVEALED ? TileState.FLAGGED : TileState.UNREVEALED;

		Minesweeper.game.repaint();
	}

	/**
	 * This method will clear all empty spaces and the adjacent numbers
	 * @param row The row to start from
	 * @param col The column to start from
	 */
	private void clearRecursive(int row, int col)
	{
		Game.TILE_STATES[row][col] = TileState.REVEALED;

		revealAdjacentNumbers(row, col);

		if(row - 1 >= 0 && Game.TILES[row - 1][col] == Tile.EMPTY && Game.TILE_STATES[row - 1][col] == TileState.UNREVEALED)
			clearRecursive(row - 1, col);

		if(col - 1 >= 0 && Game.TILES[row][col - 1] == Tile.EMPTY && Game.TILE_STATES[row][col - 1] == TileState.UNREVEALED)
			clearRecursive(row, col - 1);

		if(col + 1 < Game.TILES.length && Game.TILES[row][col + 1] == Tile.EMPTY && Game.TILE_STATES[row][col + 1] == TileState.UNREVEALED)
			clearRecursive(row, col + 1);

		if(row + 1 < Game.TILES.length && Game.TILES[row + 1][col] == Tile.EMPTY && Game.TILE_STATES[row + 1][col] == TileState.UNREVEALED)
			clearRecursive(row + 1, col);
	}

	/**
	 * Checks for surrounding number tiles and reveals them. Used for revealing them around empty space
	 * @param row The row to check from
	 * @param col The column to check from
	 */
	private void revealAdjacentNumbers(int row, int col)
	{
		if(row - 1 >= 0)
		{
			if(col - 1 >= 0 && tileIsNumber(row - 1, col - 1) && Game.TILE_STATES[row - 1][col - 1] == TileState.UNREVEALED)
				Game.TILE_STATES[row - 1][col - 1] = TileState.REVEALED;

			if(tileIsNumber(row - 1, col) && Game.TILE_STATES[row - 1][col] == TileState.UNREVEALED)
				Game.TILE_STATES[row - 1][col] = TileState.REVEALED;

			if(col + 1 < Game.TILES.length && tileIsNumber(row - 1, col + 1) && Game.TILE_STATES[row - 1][col + 1] == TileState.UNREVEALED)
				Game.TILE_STATES[row - 1][col + 1] = TileState.REVEALED;
		}

		if(col - 1 >= 0 && tileIsNumber(row, col - 1) && Game.TILE_STATES[row][col - 1] == TileState.UNREVEALED)
			Game.TILE_STATES[row][col - 1] = TileState.REVEALED;

		if(col + 1 < Game.TILES.length && tileIsNumber(row, col + 1) && Game.TILE_STATES[row][col + 1] == TileState.UNREVEALED)
			Game.TILE_STATES[row][col + 1] = TileState.REVEALED;

		if(row + 1 < Game.TILES.length)
		{
			if(col - 1 >= 0 && tileIsNumber(row + 1, col - 1) && Game.TILE_STATES[row + 1][col - 1] == TileState.UNREVEALED)
				Game.TILE_STATES[row + 1][col - 1] = TileState.REVEALED;

			if(tileIsNumber(row + 1, col) && Game.TILE_STATES[row + 1][col] == TileState.UNREVEALED)
				Game.TILE_STATES[row + 1][col] = TileState.REVEALED;

			if(col + 1 < Game.TILES.length && tileIsNumber(row + 1, col + 1) && Game.TILE_STATES[row + 1][col + 1] == TileState.UNREVEALED)
				Game.TILE_STATES[row + 1][col + 1] = TileState.REVEALED;
		}
	}

	/**
	 * Checks if a tile is a numbered tile
	 * @param row The row to check from
	 * @param col The column to check from
	 * @returns true if the tile is a numbered tile, false otherwise
	 */
	private boolean tileIsNumber(int row, int col)
	{
		Tile tile = Game.TILES[row][col];

		return tile == Tile.ONE || tile == Tile.TWO || tile == Tile.THREE || tile == Tile.FOUR ||  tile == Tile.FIVE ||  tile == Tile.SIX ||  tile == Tile.SEVEN ||  tile == Tile.EIGHT;
	}

	@Override
	public void mouseReleased(MouseEvent event) {}
}
