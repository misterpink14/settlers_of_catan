package server.database.dao;

import shared.models.Game;

/**
 * 
 * @author Bo Pace
 *
 */

public interface IGameDAO {
	
	/**
	 * Gets a game by game ID
	 * @param gameID The ID of the game we want to access.
	 * @return The game, from the database.
	 */
	public Game getGame(int gameID);
	
	/**
	 * Saves a game to the database.
	 */
	public void saveGame();
	
	/**
	 * Returns the number of changes done to the server.
	 * @return
	 */
	public int getDeltaCount();
	
	/**
	 * Adds a change to the database.
	 */
	public void addDelta();

}
