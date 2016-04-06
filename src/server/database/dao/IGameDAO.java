package server.database.dao;

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
	
	public void clear();

}
