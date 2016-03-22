package server.command.games;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.CreateGameRequestParams;

/**
 * Command for creating a new game
 * 	Server end-point: /games/create POST
 * 
 * @author benthompson
 */
public class CreateCommand extends ACommand {
	
	CreateGameRequestParams params;
	
	
	/**
	 * 
	  	{
		  "randomTiles": "boolean",
		  "randomNumbers": "boolean",
		  "randomPorts": "boolean",
		  "name": "string"
		}
	 * 
	 * 
	 * @param username
	 * @param password
	 * @param title
	 * @param id
	 * @param players
	 */
	public CreateCommand(String userJson, IServerFacade facade, String jsonBody) {
		
		super(userJson, facade);
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		params = new CreateGameRequestParams(
			json.get("title").getAsString(),
			json.get("randomTiles").getAsBoolean(),
			json.get("randomNumbers").getAsBoolean(),
			json.get("randomPorts").getAsBoolean()
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().createGame(params);
	}
}
