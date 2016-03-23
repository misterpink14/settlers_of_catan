package server.command.moves;


import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.YearOfPlenty;

/**
 * Command for for playing a year of plenty card
 * 	Server end-point: /moves/Year_Of_Plenty POST
 * 
 * @author benthompson & Bo Pace
 */
public class YearOfPlentyCommand extends ACommand {
	
	YearOfPlenty yearOfPlenty;

	/**
	 * {
		  "type": "Year_of_Plenty",
		  "playerIndex": "integer",
		  "resource1": "Resource",
		  "resource2": "Resource"
		}
	 * 
	 * @param cookies
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public YearOfPlentyCommand(Map<String, String> cookies, IServerFacade facade, String jsonBody) throws ServerException {
		super(cookies.get("catan.user"), facade, Integer.parseInt(cookies.get("catan.game")));
		 
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		yearOfPlenty = new YearOfPlenty(
			json.get("playerIndex").getAsInt(),
			json.get("resource1").getAsString(),
			json.get("resource2").getAsString()
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().yearOfPlenty(yearOfPlenty);
	}

}
