package server.database.dao;

import server.command.ACommand;

public interface ICommandDAO {

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
	
	/**
	 * Gets all of the commands from all games in the database.
	 * @return Returns a JSON string of the commands.
	 */
	public String getAllCommands();
	
	/**
	 * 
	 */
	public void clear();
}
