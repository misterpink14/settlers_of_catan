package server.command;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.facade.IServerFacade;
import shared.communication.proxy.Credentials;

public abstract class ACommand {
	
	private Credentials credentials;
	private IServerFacade facade;

	
	public ACommand() {
		
	}
	
	public ACommand(String userJson, IServerFacade facade) {
		this.jsonDecode(userJson);
	}
	
	
	protected Credentials getCredentials() {
		return this.credentials;
	}
	
	
	protected IServerFacade getFacade() {
		return this.facade;
	}
	
	
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


	
	/**
	 * Initiate the command. Calls the server facade and then returns the json 
	 * 	string to return to the user
	 * 
	 * @return
	 */
	public abstract String execute();
}
