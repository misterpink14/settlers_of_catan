package server.command.game;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for adding an AI to a given game
 * 	Server end-point: /game/addAI POST
 * 
 * @author benthompson
 */
public class AddAICommand extends ACommand {

	/**
	 * {
		  "AIType": "string"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public AddAICommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		return null;
	}

}
