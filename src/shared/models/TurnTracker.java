package shared.models;

public class TurnTracker {
	/**The index of the player whose turn it is currently*/
	private int currentPlayerTurn;
	  
	public TurnTracker(String json) {}
	  
	/**
	 * @return whether it's their turn or not
	 */
	public Boolean isTheirTurn(int playerIndex) {
		return playerIndex == currentPlayerTurn;
	}
	  	
  	/** Get the player who is currently taking their turn
  	 * @return the index of the current player, 0-3
  	 */
  	public int currentPlayerTurn() {
  		return currentPlayerTurn;
  	}
}