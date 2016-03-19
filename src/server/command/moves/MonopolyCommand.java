package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for playing a monopoly card
 * 	Server end-point: /moves/Monopoly POST
 * 
 * @author benthompson
 */
public class MonopolyCommand extends ACommand {

	/**
	 * {
		  "type": "Monopoly",
		  "resource": "string",
		  "playerIndex": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public MonopolyCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
