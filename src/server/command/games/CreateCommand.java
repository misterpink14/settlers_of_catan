package server.command.games;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for creating a new game
 * 	Server end-point: /games/create POST
 * 
 * @author benthompson
 */
public class CreateCommand extends ACommand {

	/**
	 * 
	  	{
		  "title": "string",
		  "id": "integer",
		  "players": [
		    {}
		  ]
		}
	 * 
	 * 
	 * @param username
	 * @param password
	 * @param title
	 * @param id
	 * @param players
	 */
	public CreateCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		return null;
	}

}
