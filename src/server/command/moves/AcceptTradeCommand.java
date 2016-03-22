package server.command.moves;

import server.ServerException;
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
	 * @throws ServerException 
	 */
	public AcceptTradeCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCookie() {
		// TODO Auto-generated method stub
		return null;
	}

}
