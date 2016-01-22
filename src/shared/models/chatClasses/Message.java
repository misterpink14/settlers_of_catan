package shared.models.chatClasses;

public class Message {
	int playerFrom;
	int playerTo;
	String message;

	/**
	 * This class stores a single message between two players.
	 * @param from The author of the message
	 * @param to The intended recipient
	 * @param message What is said
	 */
	public Message(int from, int to, String message) {
		this.playerFrom = from;
		this.playerTo = to;
		this.message = message;
	}

	/**
	 * @return The id of the player the message is from
	 */
	public int getPlayerFrom() {
		return playerFrom;
	}
	/**
	 * @return The id of the player the message is to
	 */
	public int getPlayerTo() {
		return playerTo;
	}
	/**
	 * @return The text contained in this message
	 */
	public String getMessage() {
		return message;
	}

}
