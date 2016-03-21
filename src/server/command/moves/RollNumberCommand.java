package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for rolling a number
 * 	Server end-point: /moves/rollNumber POST
 * 
 * @author benthompson
 */
public class RollNumberCommand extends ACommand {

	/**
	 * {
		  "type": "rollNumber",
		  "playerIndex": "integer",
		  "number": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public RollNumberCommand(String userJson, IServerFacade facade, String jsonBody) {
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
