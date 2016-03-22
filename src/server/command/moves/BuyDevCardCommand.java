package server.command.moves;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.BuyDevCard;

/**
 * Command for buying a dev card
 * 	Server end-point: /moves/buyDevCard POST
 * 
 * @author benthompson & Bo Pace
 */
public class BuyDevCardCommand extends ACommand {
	
	BuyDevCard buyDevCard;

	/**
	 * {
		  "type": "buyDevCard",
		  "playerIndex": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public BuyDevCardCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		buyDevCard = new BuyDevCard(
			json.get("playerIndex").getAsInt()
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().buyDevCard(buyDevCard);
	}

}
