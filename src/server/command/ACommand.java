package server.command;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.facade.IServerFacade;
import shared.communication.proxy.Credentials;

public abstract class ACommand {
	
	private Credentials credentials;
	private IServerFacade facade;
	protected String response;


// CONSTRUCTORS
	public ACommand() {
		
	}
	
	public ACommand(String userJson, IServerFacade facade) throws ServerException {
		this.jsonDecode(userJson);
		this.facade = facade;
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
	 * @throws ServerException 
	 */
	private void jsonDecode(String userJson) throws ServerException { // TODO parse the playerid
		try {
			JsonObject jsonObject = new JsonParser().parse(userJson).getAsJsonObject();
			String username;
			if (jsonObject.has("username")) {
				username = jsonObject.get("username").getAsString();
			}
			else {
				username = jsonObject.get("name").getAsString();
			}
			String password = jsonObject.get("password").getAsString();
			this.credentials = new Credentials(username, password);
		} catch (NullPointerException e) {
			throw new ServerException("");
		}
	}


	
// Public METHODS
	/**
	 * Initiate the command. Calls the server facade. Throws an exception if the pre-conditions
	 * 	have not been met.
	 * 
	 * @return
	 * @throws server.ServerException 
	 */
	public abstract void execute() throws server.ServerException;
	
	
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
