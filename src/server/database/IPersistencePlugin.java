package server.database;

/**
 * 
 * @author Bo Pace
 *
 */

public interface IPersistencePlugin {
	
	/**
	 * Starts a transaction to the database.
	 */
	public void startTransaction();
	
	/**
	 * Commits the changes to the database and ends the current transaction.
	 */
	public void endTransaction();
	
	/**
	 * Clears the current transaction.
	 */
	public void clear();
	
	/**
	 * Gets an instance of the game DAO from the current persistence plugin.
	 */
	public void getGameDAO();
	
	/**
	 * Gets an instance of the user DAO from the current persistence plugin.
	 */
	public void getUserDAO();
	
	/**
	 * Gets an instance of the command DAO from the current persistence plugin.
	 */
	public void getCommandDAO();
	
	/**
	 * Determines if the current state of the server has reached the defined delta, causing us to commit the changes.
	 */
	public boolean hasReachedDelta();
}
