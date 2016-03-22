package server.command.moves;


import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for sending a chat
 * 	Server end-point: /moves/sendChat POST
 * 
 * @author benthompson
 */
public class SendChatCommand extends ACommand {

	/**
	 * {
		  "type": "sendChat",
		  "playerIndex": "integer",
		  "content": "string"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public SendChatCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
