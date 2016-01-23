package shared.models.UserManager;

import java.util.List;

import shared.models.UserManager.User;

/**
 * 
 * @author Bo Pace
 *
 */
public class UserManager {
	
	/**
	 * Collection of all users that exist.
	 */
	List<User> users;
	
	UserManager() {
		
	}
	
	/**
	 * Create a user and store it in the list
	 * users.
	 */
	public void createUser(String username, String password, int playerID) {
		users.add(new User(username, password, playerID));
	}
	
	/**
	 * Makes sure that the user exists, and
	 * that the username and password match
	 * with what is stored in the database.
	 * @param user Object of class User that
	 * contains the username and password to
	 * be validated.
	 */
	public void authenticateUser(User user) {
		
	}
}
