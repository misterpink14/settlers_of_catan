package server.database;

/**
 * 
 * @author Bo Pace
 *
 */

public class Sqlite3Plugin implements IPersistencePlugin {
	
	String sqliteUri;

	/**
	 * Starts a transaction to the database.
	 */
	@Override
	public void startTransaction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Commits the changes to the database and ends the current transaction.
	 */
	@Override
	public void endTransaction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Clears the current transaction.
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets an instance of the game DAO from the current persistence plugin.
	 */
	@Override
	public void getGameDAO() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gets an instance of the user DAO from the current persistence plugin.
	 */
	@Override
	public void getUserDAO() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gets an instance of the command DAO from the current persistence plugin.
	 */
	@Override
	public void getCommandDAO() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Determines if the current state of the server has reached the defined delta, causing us to commit the changes.
	 */
	@Override
	public boolean hasReachedDelta() {
		// TODO Auto-generated method stub
		return false;
	}

}
