package server.command.moves;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.RobPlayer;
import shared.locations.HexLocation;

/**
 * Command for robbing a given player
 * 	Server end-point: /moves/robPlayer POST
 * 
 * @author benthompson & Bo Pace
 */
public class RobPlayerCommand extends ACommand {
	
	RobPlayer robPlayer;

	/**
	 * {
		  "type": "robPlayer",
		  "playerIndex": "integer",
		  "victimIndex": "integer",
		  "location": {
		    "x": "string",
		    "y": "string"
		  }
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public RobPlayerCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		robPlayer = new RobPlayer(
			json.get("playerIndex").getAsInt(),
			json.get("victimIndex").getAsInt(),
			new HexLocation(json.get("x").getAsInt(), json.get("y").getAsInt())
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().robPlayer(robPlayer);
	}

}
