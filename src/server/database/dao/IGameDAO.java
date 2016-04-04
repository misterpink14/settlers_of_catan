package server.database.dao;

import server.command.ACommand;

/**
 * 
 * @author Bo Pace
 *
 */

public interface IGameDAO {
	
	/**
	 * Gets a game by game ID
	 * @param gameID The ID of the game we want to access.
	 * @return The serialized game from the database in the form of a JSON string.
	 */
	public String getGame(int gameID);
	
	/**
	 * Saves a game to the database.
	 * @param gameID The ID of the game we're saving to the database.
	 * @param gameJson The serialized game in the form of JSON.
	 */
	public void saveGame(int gameID, String gameJson);
	
	/**
	 * Gets all games saved in the database.
	 * @return A JSON string of all of the games in the database.
	 */
	public String getAllGames();
	
	/**
	 * Returns the number of commands performed on the game in the database.
	 * @param gameID The ID of the game whose command count we need to access.
	 * @return The number of commands performed on the specified game.
	 */
	public int getCommandCount(int gameID);
	
	/**
	 * Creates a command in the database.
	 * @param command The command we're creating in the database.
	 */
	public void createCommand(ACommand command);

}
