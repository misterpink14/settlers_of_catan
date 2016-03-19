package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for accepting a trade
 * 	Server end-point: /moves/acceptTrade POST
 * 
 * @author benthompson
 */
public class AcceptTradeCommand extends ACommand {

	/**
	 * {
		  "type": "acceptTrade",
		  "playerIndex": "integer",
		  "willAccept": "boolean"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public AcceptTradeCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
