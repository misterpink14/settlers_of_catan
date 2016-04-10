package server.database;

import server.database.dao.ICommandDAO;
import server.database.dao.IGameDAO;
import server.database.dao.IUserDAO;
import server.database.dao.SqlCommandDAO;
import server.database.dao.SqlGameDAO;
import server.database.dao.SqlUserDAO;

/**
 * 
 * @author Bo Pace, Ben Thompson
 *
 */

public class Sqlite3Plugin implements IPersistencePlugin {
	
	String sqliteUri;
	
	IGameDAO GameDAO;
	IUserDAO UserDAO;
	ICommandDAO CommandDAO;
	
	DatabaseRepresentation db = new DatabaseRepresentation();;
	
	public Sqlite3Plugin (int delta) {

		GameDAO = new SqlGameDAO(this.db);
		UserDAO = new SqlUserDAO(this.db);
		CommandDAO = new SqlCommandDAO(delta, this.db);
		
		// Initialize the database representation class
		try
		{
			DatabaseRepresentation.initialize();
		} catch (DatabaseException e1)
		{
			System.out.println("Failed to initialize the Database");
			e1.printStackTrace();
		}
	}
	

	/**
	 * Starts a transaction to the database.
	 * @throws DatabaseException 
	 */
	@Override
	public void startTransaction() {
		try {
			db.startTransaction();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Commits the changes to the database and ends the current transaction.
	 */
	@Override
	public void endTransaction() {
		db.endTransaction(true);
	}

	/**
	 * Clears the current transaction.
	 */
	@Override
	public void clear() {
		DatabaseRepresentation.dropAndRecreateTables();
	}

	/**
	 * Gets an instance of the game DAO from the current persistence plugin.
	 * @return 
	 */
	@Override
	public IGameDAO getGameDAO() {
		return this.GameDAO;
	}

	/**
	 * Gets an instance of the user DAO from the current persistence plugin.
	 * @return 
	 */
	@Override
	public IUserDAO getUserDAO() {
		return this.UserDAO;
	}
	
	/**
	 * Gets an instance of the command DAO from the current persistence plugin.
	 * @return 
	 */
	@Override
	public ICommandDAO getCommandDAO() {
		return this.CommandDAO;
	}

}
