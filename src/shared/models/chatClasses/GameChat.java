package shared.models.chatClasses;

import java.util.ArrayList;
import java.util.List;

public class GameChat {
	/**An array that holds the messages in the game chat*/
	ArrayList<Message> messages = new ArrayList<Message>();

	/**
	 * This object keeps track of the chat between players during the course of a game
	 */
	public GameChat() {}
	
	/**
	 * Uses json to add messages to the game chat.
	 */
	public void importChat(String json) {
		
	}
	
	/**
	 * Adds a message to the message list
	 *
	 * @param message The message to be added to the chat
	 */
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	/**Gets all messages*/
	public List<Message> getMessages() {
		return messages;
	}
}
