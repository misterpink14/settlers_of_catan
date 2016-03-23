package server.command.moves;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.RoadBuilding;

/**
 * Command for playing a road building card
 * 	Server end-point: /moves/Road_Building POST
 * 
 * @author benthompson & Bo Pace
 */
public class RoadBuildingCommand extends ACommand {
	
	RoadBuilding roadBuilding;

	/**
	 * {
		  "type": "Road_Building",
		  "playerIndex": "integer",
		  "spot1": {
		    "x": "integer",
		    "y": "integer",
		    "direction": "string"
		  },
		  "spot2": {
		    "x": "integer",
		    "y": "integer",
		    "direction": "string"
		  }
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public RoadBuildingCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		roadBuilding = new RoadBuilding(
			json.get("playerIndex").getAsInt(),
			json.getAsJsonObject("spot1").get("x").getAsInt(),
			json.getAsJsonObject("spot1").get("y").getAsInt(),
			json.getAsJsonObject("spot1").get("direction").getAsString(),
			json.getAsJsonObject("spot2").get("x").getAsInt(),
			json.getAsJsonObject("spot2").get("y").getAsInt(),
			json.getAsJsonObject("spot2").get("direction").getAsString()
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().roadBuilding(roadBuilding);
	}

}
