package server.database.dao;

import server.command.ACommand;

/**
 * 
 * @author Bo Pace
 *
 */
public class SqlGameDAO implements IGameDAO {

	/**
	 * Gets a game by game ID
	 * @param gameID The ID of the game we want to access.
	 * @return The serialized game from the database in the form of a JSON string.
	 */
	@Override
	public String getGame(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Saves a game to the database.
	 * @param gameID The ID of the game we're saving to the database.
	 * @param gameJson The serialized game in the form of JSON.
	 */
	@Override
	public void saveGame(int gameID, String gameJson) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets all games saved in the database.
	 * @return A JSON string of all of the games in the database.
	 */
	public String getAllGames() {
		return null;
	}
	
	/**
	 * Returns the number of commands performed on the game in the database.
	 * @param gameID The ID of the game whose command count we need to access.
	 * @return The number of commands performed on the specified game.
	 */
	public int getCommandCount(int gameID) {
		return -1;
	}
	
	/**
	 * Creates a command in the database.
	 * @param command The command we're creating in the database.
	 */
	public void createCommand(ACommand command) {
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
