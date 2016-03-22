package server.command.moves;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;
import shared.communication.proxy.SendChat;

/**
 * Command for sending a chat
 * 	Server end-point: /moves/sendChat POST
 * 
 * @author benthompson & Bo Pace
 */
public class SendChatCommand extends ACommand {
	
	SendChat sendChat;

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

		JsonObject json = new JsonParser().parse(jsonBody).getAsJsonObject();
		sendChat = new SendChat(
			json.get("playerIndex").getAsInt(),
			json.get("content").getAsString()
		);
	}

	@Override
	public void execute() {
		this.response = this.getFacade().sendChat(sendChat);
	}

}