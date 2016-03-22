package server.command.game;

import server.ServerException;
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
	 * @throws ServerException 
	 */
	public AddAICommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public void execute() {
		return;
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
