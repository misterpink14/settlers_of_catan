package server.database.dao;

/**
 * 
 * @author Bo Pace
 *
 */
public class SqlUserDAO implements IUserDAO {

	/**
	 * Gets a user by user ID
	 * @param userID The ID of the user we want to access.
	 * @return The serialized user from the database, in the form of JSON.
	 */
	public String getUser(int userID) {
		return null;
	}
	
	/**
	 * Gets all users saved in the database.
	 * @return A JSON string of all users saved in the database.
	 */
	public String getAllUsers() {
		return null;
	}
	
	/**
	 * Creates a user in the database.
	 * @param userID The ID of the user being created in the database.
	 * @param userJson The serialized user in the form of JSON.
	 */
	public void createUser(int userID, String userJson) {
		
	}

}
