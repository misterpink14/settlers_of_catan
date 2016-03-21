package server.managers;

import shared.communication.proxy.Credentials;

/**
 * User's are contained within the UserManager class
 * 
 * @author benthompson
 *
 */
public class User {

	Credentials credentials;
	int playerID;
	
	static int nextPlayerID = 1;
	
	
// CONSTRUCTOR
	public User(Credentials credentials) {
		
		this.credentials = credentials;
		this.playerID = this.getNextID();
	}
	

// GETTERS
	public String getUsername() {
		return this.credentials.username;
	}
	
	
	public String getPassword() {
		return this.credentials.password;
	}
	
	

// Public METHODS
	public int login(Credentials credentials) throws InvalidCredentialsException {
		
		if (this.getUsername() == credentials.username && this.getPassword() == credentials.password) {
			return this.playerID;
		}
		throw new InvalidCredentialsException();
	}
	
	
	@Override // TODO: implement this
    public String toString() {
		return "";
	}

	

// Private METHODS
	private int getNextID() {
		
		int id = nextPlayerID;
		nextPlayerID++;
		return id;
	}

}
