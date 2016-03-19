package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for robbing a given player
 * 	Server end-point: /moves/robPlayer POST
 * 
 * @author benthompson
 */
public class RobPlayerCommand extends ACommand {

	/**
	 * {
		  "type": "robPlayer",
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
	 */
	public RobPlayerCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
