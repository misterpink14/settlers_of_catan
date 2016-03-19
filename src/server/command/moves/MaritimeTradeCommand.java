package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for maritime trade
 * 	Server end-point: /moves/maritimeTrade POST
 * 
 * @author benthompson
 */
public class MaritimeTradeCommand extends ACommand {

	/**
	 * {
		  "type": "maritimeTrade",
		  "playerIndex": "integer",
		  "ratio": "integer",
		  "inputResource": "String",
		  "outputResource": "String"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public MaritimeTradeCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
