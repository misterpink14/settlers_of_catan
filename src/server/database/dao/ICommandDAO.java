package server.database.dao;

import java.util.List;

import server.command.ACommand;
import server.database.DatabaseException;

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
	public void createCommand(ACommand command) throws DatabaseException;
	
	/**
	 * Gets all of the commands from all games in the database.
	 * @return Returns a list of the commands.
	 */
	public List<ACommand> getAllCommands() throws DatabaseException;
	
	/**
	 * Clears all of the commands for the specified game in the database
	 * @param gameID The ID of the game we are clearing the commands for
	 */
	public void clearCommands(int gameID);
	
	/**
	 * Clears all of the commands from the database
	 */
	public void clear();
}
