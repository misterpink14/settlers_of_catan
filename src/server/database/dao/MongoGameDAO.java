package server.database.dao;

import shared.models.Game;

/**
 * 
 * @author Bo Pace
 *
 */
public class MongoGameDAO implements IGameDAO {

	/**
	 * Gets a game by game ID
	 * @param gameID The ID of the game we want to access.
	 * @return The game, from the database.
	 */
	@Override
	public Game getGame(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Saves a game to the database.
	 */
	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the number of changes done to the server.
	 * @return
	 */
	@Override
	public int getDeltaCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Adds a change to the database.
	 */
	@Override
	public void addDelta() {
		// TODO Auto-generated method stub
		
	}

}
