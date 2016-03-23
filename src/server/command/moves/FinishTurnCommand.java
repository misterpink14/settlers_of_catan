package server.command.moves;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.FinishTurn;

/**
 * Command for finishing a player's turn
 * 	Server end-point: /moves/finishTurn POST
 * 
 * @author benthompson & Bo Pace
 */
public class FinishTurnCommand extends ACommand {
	
	FinishTurn finishTurn;

	/**
	 * {
		  "type": "finishTurn",
		  "playerIndex": "integer"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public FinishTurnCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		finishTurn = new FinishTurn(
			json.get("playerIndex").getAsInt()
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().finishTurn(finishTurn);
	}

}
