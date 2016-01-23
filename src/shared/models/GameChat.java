package shared.models;

import java.util.ArrayList;

import shared.models.chatClasses.Message;

public class GameChat {
	/**An array that holds the entries in the game log*/
	ArrayList<Message> entries = new ArrayList<Message>();

	/**
	 * This object keeps track of the chat between players during the course of a game
	 */
	public GameChat(String json) {}
	
	/**Adds a message to the message list*/
	public void addMessage() {}
	
	/**Gets all messages*/
	public void getMessages() {}
}
