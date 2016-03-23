package server.command.moves;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for playing a soldier card
 * 	Server end-point: /moves/Soldier POST
 * 
 * @author benthompson
 */
public class SoldierCommand extends ACommand {

	/**
	 * {
		  "type": "Soldier",
		  "playerIndex": "integer",
		  "victimIndex": "integer",
		  "location": {
		    "x": "string",
		    "y": "string"
		  }
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public SoldierCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		return;
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
