package shared.models.playerClasses;

public class TurnTracker {
	/**The players in the game*/
	private GamePlayers players = null;
	/**The index of the player whose turn it is currently*/
	private int currentPlayerTurn;
	  
	public TurnTracker(String json, GamePlayers players) {
		this.players = players;
	}
	  
	/**
	 * @return whether it's their turn or not
	 */
	public Boolean isTheirTurn(int playerID) {
		return players.isTurn(playerID);
	}
}
