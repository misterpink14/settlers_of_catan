package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for finishing a player's turn
 * 	Server end-point: /moves/finishTurn POST
 * 
 * @author benthompson
 */
public class FinishTurnCommand extends ACommand {

	/**
	 * {
		  "type": "finishTurn",
		  "playerIndex": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public FinishTurnCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
