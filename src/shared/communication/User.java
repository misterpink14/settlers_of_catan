package shared.communication;

/**
 * 
 * @author Bo
 *
 */
public class User {
	/**
	 * Username used for authentication
	 */
	String username;
	/**
	 * Password used for authentication
	 */
	String password;
	/**
	 * The user's player id
	 */
	int playerID;
	
	public User(String username, String password, int playerID) {
		this.username = username;
		this.password = password;
		this.playerID = playerID;
	}
}
