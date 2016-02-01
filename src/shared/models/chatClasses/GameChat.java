package shared.models.chatClasses;

import java.util.ArrayList;

public class GameChat {
	/**An array that holds the entries in the game log*/
	ArrayList<Message> entries = new ArrayList<Message>();

	/**
	 * This object keeps track of the chat between players during the course of a game
	 */
	public GameChat() {}
	
	/**
	 * Uses json to add messages to the game chat.
	 */
	public void importChat(String json) {
		
	}
	
	/**Adds a message to the message list*/
	public void addMessage() {}
	
	/**Gets all messages*/
	public void getMessages() {}
}
