package server.database.dao;

import server.managers.User;

/**
 * 
 * @author Bo Pace
 *
 */

public class IUserDAO {
	
	/**
	 * Gets a user by user ID
	 * @param userID The ID of the user we want to access.
	 * @return The user, from the database.
	 */
	public User getUser(int userID) {
		return null;
	}
	
	/**
	 * Saves a user to the database.
	 * @param user The user to be saved.
	 */
	public void saveUser(String user) {
		
	}
	
	/**
	 * Returns the number of changes done to the server.
	 * @return
	 */
	public int getDeltaCount() {
		return 0;
	}
	
	/**
	 * Adds a change to the database.
	 */
	public void addDelta() {
		
	}

}
