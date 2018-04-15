package bl4ckscor3.game.minesweeper.util;

import java.net.URISyntaxException;

import bl4ckscor3.game.minesweeper.Minesweeper;

public class Utilities
{
	/**
	 * Gets the path of the running jar file
	 */
	public static String getJarLocation()
	{
		String path = "";

		try
		{
			path = Minesweeper.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		}
		catch(URISyntaxException e)
		{
			e.printStackTrace();
		}

		if(path.endsWith(".jar"))
			path = path.substring(0, path.lastIndexOf("/"));

		return path;
	}
}
