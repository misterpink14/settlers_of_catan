package server.command.moves;


import java.util.Map;

import server.ServerException;
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
	 * @param cookies
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public MaritimeTradeCommand(Map<String, String> cookies, IServerFacade facade, String jsonBody) throws ServerException {
		super(cookies.get("catan.user"), facade, Integer.parseInt(cookies.get("catan.game"))); 
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
