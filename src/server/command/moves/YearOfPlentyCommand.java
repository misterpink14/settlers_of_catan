package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for for playing a year of plenty card
 * 	Server end-point: /moves/Year_Of_Plenty POST
 * 
 * @author benthompson
 */
public class YearOfPlentyCommand extends ACommand {

	/**
	 * {
		  "type": "Year_of_Plenty",
		  "playerIndex": "integer",
		  "resource1": "Resource",
		  "resource2": "Resource"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public YearOfPlentyCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
