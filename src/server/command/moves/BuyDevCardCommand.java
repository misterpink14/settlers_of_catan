package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for buying a dev card
 * 	Server end-point: /moves/buyDevCard POST
 * 
 * @author benthompson
 */
public class BuyDevCardCommand extends ACommand {

	/**
	 * {
		  "type": "buyDevCard",
		  "playerIndex": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public BuyDevCardCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
