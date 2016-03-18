package server.command;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.facade.IServerFacade;
import shared.communication.proxy.Credentials;

public class AUserCommand {
	
	private Credentials credentials;
	private IServerFacade facade;

	
	public AUserCommand(String json, IServerFacade facade) {
		this.jsonDecode(json);
	}
	
	
	protected Credentials getCredentials() {
		return this.credentials;
	}
	
	
	protected IServerFacade getFacade() {
		return this.facade;
	}
	
	
	private void jsonDecode(String json) {
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		String username = jsonObject.get("username").getAsString();
		String password = jsonObject.get("password").getAsString();
		this.credentials = new Credentials(username, password);
	}

}
