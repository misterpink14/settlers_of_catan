package server.database.dao;

/**
 * 
 * @author Bo Pace
 *
 */

public interface IUserDAO {
	
	/**
	 * Gets a user by user ID
	 * @param userID The ID of the user we want to access.
	 * @return The serialized user from the database, in the form of JSON.
	 */
	public String getUser(int userID);
	
	/**
	 * Gets all users saved in the database.
	 * @return A JSON string of all users saved in the database.
	 */
	public String getAllUsers();
	
	/**
	 * Creates a user in the database.
	 * @param userID The ID of the user being created in the database.
	 * @param userJson The serialized user in the form of JSON.
	 */
	public void createUser(int userID, String userJson);

	/**
	 * Clears the users stored in the database.
	 */
	public void clear();
}
