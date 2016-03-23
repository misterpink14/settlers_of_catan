package server.command.games;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.JoinGameRequestParams;

/**
 * Command for joining a specific game
 * 	Server end-point: /games/join POST
 * 
 * @author benthompson
 */
public class JoinCommand extends ACommand {

	JoinGameRequestParams params;
	/**
	 * {
		  "id": "integer",
		  "color": "string"
		}
	 * 
	 * 
	 * @param username
	 * @param password
	 * @param gameID
	 * @param color
	 * @throws ServerException 
	 */
	public JoinCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		// TODO parse the jsonBody
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		params = new JoinGameRequestParams(json.get("id").getAsInt(), json.get("color").getAsString());
	}

	@Override
	public void execute() {
		this.response = this.getFacade().joinGame(params);
	}

	@Override
	public String getCookie() {
		String cookie = "catan.game=";
		
		cookie += ";Path=/;";
		return cookie;
	}

}
