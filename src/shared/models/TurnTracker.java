package shared.models;


public class TurnTracker {
	
	/**The index of the player whose turn it is currently*/
	int currentPlayerTurn;

	public TurnTracker() {}

	/** Determine whether it's a player's turn or not
	 * 
	 * @param a player index
	 * @return whether it's their turn or not
	 */
	public Boolean isTheirTurn(int playerIndex) {
		return null;
	}
	
	/** Get the player who is currently taking their turn
	 * 
	 * @return the index of the current player, 0-3
	 */
	public int currentPlayerTurn() {
		return null;
	}
	
	
}
