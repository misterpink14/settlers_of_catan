package shared.models;

import shared.definitions.DevCardType;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.chatClasses.GameChat;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Map;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.TurnTracker;

/**
 * 
 * @author Stephen Snyder
 *
 */
public class Game 
{
	/**This allows two random numbers between 1 and 6 to be generated at any time.*/
	Dice dice;
	/**The map contains all information having to do with the board.*/
	Map map;
	/**The bank contains all resource cards that do not belong to a player.*/
	Bank bank;
	/**The cardDeck contains all the development cards not belonging to a player.*/
	CardDeck cardDeck;
	/**The game players object holds four player objects that represent four clients that will connect
	 * to the server.
	 */
	GamePlayers players;
	/**Stores the log for the game*/
	GameLog log;
	/**The game chat object stores and retrieves the history of the chat log between players.*/
	GameChat chat;
	/**The trade manager handles all trades between players. Both storing and executing*/
	TradeManager tradeManager;
	/**The turn tracker manages trades between players*/
	TurnTracker turnTracker;
	/**Each game has a version ID so the server knows which JSON to return.*/
	int versionID;
	
	public Game() {}
	
	/**
	 * Takes in a json summary of a game and changes itself to match the specified game
	 */
	public void importGame() {}
	
	/**
	 * If the specified player can roll the dice, do so
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 * @return The number rolled or 0 if the player does not have permission to roll the dice.
	 */
	public int rollDice(int playerID) {
		if (players.canRollDice(playerID)) {
			return Dice.rollDice();
		}
		else{
			return 0;
		}
	}

	/**
	 * Trades a player's resources for a new road on the map. It must connect with another of the player's
	 * roads, settlements, or cities.
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildRoad(int playerID) {
		if(players.canBuildRoad(playerID)) {
			
		}
	}
	
	/**
	 * Trades a player's resources for a new settlement on the map. The player must have a road leading to the spot wanted.
	 * The selected place to build must also be at least two building spots away from any other settlement.
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildSettlement(int playerID) {
		if(players.canBuildSettlement(playerID)) {
			players.canBuildSettlement(playerID);
		}
	}
	
	/**
	 * Trades a player's resources for a new city on the map. The player must build it in place of an existing settlement.
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildCity(int playerID) {
		if(players.canBuildCity(playerID)) {
			players.canBuildCity(playerID);
		}
	}
	
	/**
	 * Trades a player's resources for a development card
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buyDevelopmentCard(int playerID) throws InsufficientCardNumberException {
		if(players.canBuyDevCard(playerID)) {
			players.buyDevCard(playerID);
		}
	}
	
	/**
	 * Allows a player to move the robber in exchange for a Soldier Card
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void playSoldierCard(int playerID, DevCardType type) throws InsufficientCardNumberException {
		players.playDevCard(playerID, type);
	}
	
	/**
	 * Allows a player to offer an exchange of resources to one or more other players
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void offerATrade(int playerID){}
	
	/**
	 * Check the TurnTracker to see if it is the user's turn at the given index
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public boolean isTurn(int playerIndex) {
		return turnTracker.isTheirTurn(playerIndex);
	}
	
	/**
	 * Returns the version ID so the poller and proxy can request the correct model JSON.
	 */
	public int versionID() {
		return versionID;
	}
}
