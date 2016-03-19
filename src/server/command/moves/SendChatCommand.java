package server.command.moves;

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
	 */
	public SendChatCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
