package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for offering a trade
 * 	Server end-point: /moves/offerTrade POST
 * 
 * @author benthompson
 */
public class OfferTradeCommand extends ACommand {

	/**
	 * {
		  "type": "offerTrade",
		  "playerIndex": "integer",
		  "offer": {
		    "brick": "integer",
		    "ore": "integer",
		    "sheep": "integer",
		    "wheat": "integer",
		    "wood": "integer"
		  },
		  "receiver": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public OfferTradeCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
