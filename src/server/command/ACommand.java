package server.command;

import java.rmi.ServerException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.facade.IServerFacade;
import shared.communication.proxy.Credentials;

public abstract class ACommand {
	
	private Credentials credentials;
	private IServerFacade facade;
	private String response;


// CONSTRUCTORS
	public ACommand() {
		
	}
	
	public ACommand(String userJson, IServerFacade facade) {
		this.jsonDecode(userJson);
	}
	

// Protected GETTERS
	protected Credentials getCredentials() {
		return this.credentials;
	}
	
	
	protected IServerFacade getFacade() {
		return this.facade;
	}
	
	

// Private METHODS
	/**
	 * Decodes the user credentials from the catan.user json within the cookie
	 * 
	 * @param userJson
	 */
	private void jsonDecode(String userJson) { // TODO parse the playerid
		JsonObject jsonObject = new JsonParser().parse(userJson).getAsJsonObject();
		String username;
		if (jsonObject.has("username")) {
			username = jsonObject.get("username").getAsString();
		}
		else {
			username = jsonObject.get("user").getAsString();
		}
		String password = jsonObject.get("password").getAsString();
		this.credentials = new Credentials(username, password);
	}


	
// Public METHODS
	/**
	 * Initiate the command. Calls the server facade. Throws an exception if the pre-conditions
	 * 	have not been met.
	 * 
	 * @return
	 */
	public abstract void execute();
	
	
	/**
	 * Returns the response body for the server to use
	 * 
	 * @return
	 */
	public String getResponse() {
		return this.response;
	}
	
	
	/**
	 * If appropriate, returns the cookie for the server to set on the client.
	 * 	If not, throws an exception
	 * 
	 * @return
	 * @throws ServerException
	 */
	public String getCookie() throws ServerException {
		throw new ServerException("Invalid call");
	}
}
