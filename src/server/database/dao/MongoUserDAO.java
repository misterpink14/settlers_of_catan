package server.database.dao;

import server.managers.User;

/**
 * 
 * @author Bo Pace
 *
 */

public class MongoUserDAO implements IUserDAO {

	/**
	 * Gets a user by user ID
	 * @param userID The ID of the user we want to access.
	 * @return The user, from the database.
	 */
	@Override
	public User getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Saves a user to the database.
	 * @param user The user to be saved.
	 */
	@Override
	public void saveUser(String user) {
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
