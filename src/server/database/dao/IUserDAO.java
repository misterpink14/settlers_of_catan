package server.database.dao;

import java.util.List;

import server.managers.User;

/**
 * 
 * @author Bo Pace
 *
 */

public interface IUserDAO {
	
	/**
	 * Gets a user by user ID
	 * @param userID The ID of the user we want to access.
	 * @return The user from the database.
	 */
	public User getUser(int userID);
	
	/**
	 * Gets all users saved in the database.
	 * @return A list of all users saved in the database.
	 */
	public List<User> getAllUsers();
	
	/**
	 * Creates a user in the database.
	 * @param user The user to save to the database
	 */
	public void createUser(User user);

	/**
	 * Clears the users stored in the database.
	 */
	public void clear();
}
