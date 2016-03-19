package server.command.moves;

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
	 */
	public SoldierCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
