package server.command.moves;

import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.Monopoly;

/**
 * Command for playing a monopoly card
 * 	Server end-point: /moves/Monopoly POST
 * 
 * @author benthompson & Bo Pace
 */
public class MonopolyCommand extends ACommand {
	
	Monopoly monopoly;

	/**
	 * {
		  "type": "Monopoly",
		  "resource": "string",
		  "playerIndex": "integer"
		}
	 * 
	 * @param cookies
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public MonopolyCommand(Map<String, String> cookies, IServerFacade facade, String jsonBody) throws ServerException {
		super(cookies.get("catan.user"), facade, Integer.parseInt(cookies.get("catan.game"))); 
		
		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		monopoly = new Monopoly(
			json.get("playerIndex").getAsInt(),
			json.get("resource").getAsString()
		);
	}

	@Override
	public void execute() throws ServerException {
		this.response = this.getFacade().playMonopolyCard(monopoly, this.getGameID());
	}

}
