package server.command.moves;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for playing a monument card
 * 	Server end-point: /moves/ POST
 * 
 * @author benthompson
 */
public class MonumentCommand extends ACommand {

	/**
	 * {
		  "type": "Monument",
		  "playerIndex": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public MonumentCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
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
