package server.command.moves;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.RollNumber;

/**
 * Command for rolling a number
 * 	Server end-point: /moves/rollNumber POST
 * 
 * @author benthompson & Bo Pace
 */
public class RollNumberCommand extends ACommand {
	
	RollNumber rollNumber;

	/**
	 * {
		  "type": "rollNumber",
		  "playerIndex": "integer",
		  "number": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public RollNumberCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		rollNumber = new RollNumber(
			json.get("playerIndex").getAsInt()	
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().rollNumber(rollNumber);
	}

}
