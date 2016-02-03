package shared.models.playerClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;

public class GamePlayers {
	ArrayList<Player> players = new ArrayList<Player>();
	
	int longestRoad = 0;
	int largestArmy = 0;
	
	public GamePlayers() {}

	/**
	 * This gets the number of players currently registered to this game
	 * @return the number of players currently registered to this game.
	 */
	public int getNumberOfPlayers() {
		return players.size();
	}
	
	/**
	 * This accepts a client and adds it as a player object to an array.
	 * @throws Exception 
	 */
	public void addPlayer(int playerID, String name, CatanColor color) throws Exception {
		if(this.getNumberOfPlayers() == 4) {
			throw new Exception("There are already four players in this game");
		}
		Player newPlayer = new Player(playerID, name, color);
		players.add(newPlayer);
	}
	
	/**
	 * Overloaded the addPlayer method to add a player that has been
	 * deserialized.
	 */
	public void addPlayer(Player player) throws Exception {
		players.add(player);
	}
	
	/**
	 * This returns a player in connection with the given playerIndex
	 * @param playerIndex the index connected with this player
	 * @return A player object
	 */
	public Player getPlayerByIndex(int playerIndex) {
		return players.get(playerIndex);
	}	
	
	/**
	 * End a player's turn and mark that it is the
	 * next player's turn.
	 */
	public int finishTurn(int playerIndex) {
		//finish the current players turn
		players.get(playerIndex).finishTurn();
		
		playerIndex++;
		if(playerIndex > 4) {
			playerIndex = 0;
		}
		
		//start the next player's turn.
		players.get(playerIndex).startTurn();
		return playerIndex;
	}
	
	public int checkForLargestArmy() {
		int playerIndex = 0;
		for (Player p : players) {
			if(p.getArmy() > largestArmy && largestArmy > 2) {
				largestArmy = p.getArmy();
				return playerIndex;
			}
			playerIndex++;
		}
		return -1;
	}
	
	public int checkForWinner() {
		int playerIndex = 0;
		for (Player p : players) {
			if(p.getVictoryPoints() > 9) {
				return playerIndex;
			}
			playerIndex++;
		}
		return -1;
	}
}
