package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for discarding cards
 * 	Server end-point: /moves/discardCards POST
 * 
 * @author benthompson
 */
public class DiscardCardsCommand extends ACommand {

	/**
	 * {
		  "type": "discardCards",
		  "playerIndex": "integer",
		  "discardedCards": {
		    "brick": "integer",
		    "ore": "integer",
		    "sheep": "integer",
		    "wheat": "integer",
		    "wood": "integer"
		  }
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public DiscardCardsCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
