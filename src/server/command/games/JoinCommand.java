package server.command.games;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for joining a specific game
 * 	Server end-point: /games/join POST
 * 
 * @author benthompson
 */
public class JoinCommand extends ACommand {

	/**
	 * {
		  "id": "integer",
		  "color": "string"
		}
	 * 
	 * 
	 * @param username
	 * @param password
	 * @param gameID
	 * @param color
	 */
	public JoinCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		return null;
	}

}
