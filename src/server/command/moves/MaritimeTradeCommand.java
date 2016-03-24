package server.command.moves;


import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.MaritimeTrade;
import shared.definitions.ResourceType;

/**
 * Command for maritime trade
 * 	Server end-point: /moves/maritimeTrade POST
 * 
 * @author benthompson & Bo Pace
 */
public class MaritimeTradeCommand extends ACommand {
	
	MaritimeTrade maritimeTrade;

	/**
	 * {
		  "type": "maritimeTrade",
		  "playerIndex": "integer",
		  "ratio": "integer",
		  "inputResource": "String",
		  "outputResource": "String"
		}
	 * 
	 * @param cookies
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public MaritimeTradeCommand(Map<String, String> cookies, IServerFacade facade, String jsonBody) throws ServerException {
		super(cookies.get("catan.user"), facade, Integer.parseInt(cookies.get("catan.game"))); 
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		ResourceType inputResource = ResourceType.BRICK;
		switch(json.get("inputResource").getAsString()) {
		case "Brick":
			inputResource = ResourceType.BRICK;
			break;
		case "Ore":
			inputResource = ResourceType.ORE;
			break;
		case "Sheep":
			inputResource = ResourceType.SHEEP;
			break;
		case "Wheat":
			inputResource = ResourceType.WHEAT;
			break;
		case "Wood":
			inputResource = ResourceType.WOOD;
			break;
		}
		ResourceType outputResource = ResourceType.BRICK;
		switch(json.get("outputResource").getAsString()) {
		case "Brick":
			outputResource = ResourceType.BRICK;
			break;
		case "Ore":
			outputResource = ResourceType.ORE;
			break;
		case "Sheep":
			outputResource = ResourceType.SHEEP;
			break;
		case "Wheat":
			outputResource = ResourceType.WHEAT;
			break;
		case "Wood":
			outputResource = ResourceType.WOOD;
			break;
		}
		
		maritimeTrade = new MaritimeTrade(
			inputResource,
			outputResource,
			json.get("ratio").getAsInt(),
			json.get("playerIndex").getAsInt()
		);
	}

	@Override
	public void execute() throws ServerException {
		this.response = this.getFacade().maritimeTrade(maritimeTrade, this.getGameID());
	}

}
