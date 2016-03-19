package server.command.moves;

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
	 */
	public MonumentCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
