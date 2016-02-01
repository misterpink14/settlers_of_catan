package shared.models.playerClasses;

import java.util.HashMap;
import java.util.Map;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;

public class GamePlayers {
	
	/**The players array contains four players that will be used by four clients.*/
	Map<Integer, Player> players = new HashMap<Integer, Player>();	
	
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
	public void addPlayer(int clientID) throws Exception {
		if(this.getNumberOfPlayers() == 4) {
			throw new Exception("There are already four players in this game");
		}
		Player newPlayer = new Player(clientID);
		players.put(clientID, newPlayer);
	}
	
	/**
	 * This returns a player in connection with the given playerID
	 * @param playerID the client ID connected with this player
	 * @return A player object
	 */
	public Player getPlayer(int playerID) {
		return players.get(playerID);
	}
	
	/**
	 * Start a player's turn and mark that it is the
	 */
	public void startTurn(int playerID) {
		players.get(playerID).startTurn();
	}
	
	/** This function checks a specific player to see
	 * if it is currently his/her turn.
	 * @return Returns true if it's the player's turn,
	 * otherwise, returns false;
	 */
	public boolean isTurn(int playerID) {
		return players.get(playerID).isTurn();
	}
	
	/**
	 * Checks if the specified player can roll
	 * the dice.
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice(int playerID) {
		return players.get(playerID).canRollDice();
	}
	
	/**
	 * Adds resources to a player's resourceCards object
	 * @param playerID the id of the player to add resources to
	 * @param type the type of resource to add.
	 * @param num the number to add.
	 */
	public void addResourceToHand(int playerID, ResourceType type, int num) {
		players.get(playerID).addResourceToHand(type, num);
	}
	
	/**
	 *Add a specified development card from this player
	 */
	public void drawDevCard(int playerID, DevCardType type) {
		players.get(playerID).drawDevCard(type);
	}
	
	/**
	 * Checks to see if a specified player can
	 * build a road at the specified location.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildRoad(int playerID) {
		return players.get(playerID).canBuildRoad();
	}
	
	/**
	 * Checks to see if a specified player can
	 * build a settlement at the specified location.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildSettlement(int playerID) {
		return players.get(playerID).canBuildSettlement();
	}
	
	/**
	 * Checks to see if a specified player can
	 * build a city at the specified location
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildCity(int playerID) {
		return players.get(playerID).canBuildCity();
	}
	
	/**
	 * Checks if a specified player can buy a
	 * development card at this time.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuyDevCard(int playerID) {
		return players.get(playerID).canBuyDevCard();
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buyRoad(int playerID) throws InsufficientCardNumberException {
		players.get(playerID).buyRoad();
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buySettlement(int playerID) throws InsufficientCardNumberException {
		players.get(playerID).buySettlement();
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buyCity(int playerID) throws InsufficientCardNumberException {
		players.get(playerID).buyCity();
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buyDevCard(int playerID) throws InsufficientCardNumberException {
		players.get(playerID).buyDevCard();
	}
	
	/**
	 * Exchange four of one player's resources for
	 * one of another.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeFour(int playerID, ResourceType tradeIn, ResourceType tradeOut) throws InsufficientCardNumberException {
		players.get(playerID).tradeFour(tradeIn, tradeOut);
	}
	
	/**
	 * Trade a player's resources using a harbor
	 * he/she has built on.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeTwoWithPort(int playerID, ResourceType tradeIn, ResourceType tradeOut) throws InsufficientCardNumberException {
		players.get(playerID).tradeTwoWithPort(tradeIn, tradeOut);
	}
	
	/**
	 * Trade a player's resources using a harbor
	 * he/she has built on.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeThreeWithPort(int playerID, ResourceType tradeIn, ResourceType tradeOut) throws InsufficientCardNumberException {
		players.get(playerID).tradeThreeWithPort(tradeIn, tradeOut);
	}
	
	/**
	 * Allows a player to move the robber in exchange for a Soldier Card
	 * @throws InsufficientCardNumberException 
	 */
	public void playDevCard(int playerID, DevCardType type) throws InsufficientCardNumberException {
		players.get(playerID).playDevCard(type);
	}
	
	/**
	 * End a player's turn and mark that it is the
	 * next player's turn.
	 */
	public void finishTurn(int playerID) {
		players.get(playerID).finishTurn();
	}
}
