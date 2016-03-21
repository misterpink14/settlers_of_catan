package server.managers;

import java.util.HashMap;
import java.util.Map;

import shared.communication.proxy.Credentials;



/**
 * UserManager class
 * @author Skyler
 *
 */
public class UserManager {
	
	private Map<String, User> users;
	
	public UserManager()
	{
		users = new HashMap<String, User>();
	}
	
	/**
	 * Adds a user to the list of users
	 * @param user
	 * @throws InvalidCredentialsException 
	 */
	public void addUser(Credentials userCredentials) throws InvalidCredentialsException
	{
		if (users.containsKey(userCredentials.username)){
			throw new InvalidCredentialsException("username already exists");
		}
		users.put(userCredentials.username, new User(userCredentials));
	}
	
	
	public User getUser(String username) {
		return this.users.get(username);
	}
	

	/**
	 * Returns the list of users
	 * @return List<User>
	 */
	public Map<String, User> getUsers()
	{
		return users;
	}
	
	
	public int login(Credentials credentials) throws InvalidCredentialsException {
		
		if (!this.users.containsKey(credentials.username)) {
			throw new InvalidCredentialsException("Failed to login - bad username or password.");
		}
		return this.getUser(credentials.username).login(credentials);
	}
}
