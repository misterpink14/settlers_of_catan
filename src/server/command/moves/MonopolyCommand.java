package server.command.moves;

import java.util.Map;

import server.ServerException;
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
	 * @param cookies
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public MonopolyCommand(Map<String, String> cookies, IServerFacade facade, String jsonBody) throws ServerException {
		super(cookies.get("catan.user"), facade, Integer.parseInt(cookies.get("catan.game"))); 
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
