package shared.models.playerClasses;

import java.util.HashMap;
import java.util.Random;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.Dice;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.chatClasses.GameChat;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Map;

public class TurnManager {
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
	
	/**The trade manager handles everything to do with trading in the game.*/
	TradeManager tradeManager;
	
	/**The index of the player whose turn it is.*/
	int playerIndex = -1;
	
	/**Keeps track of whether the player has played a dev card.*/
	private boolean hasPlayedDevCard;
	
	/**This is set to true if the player has the longest road above 5 roads*/
	private int longestRoad = -1;
	
	/**This is set to true if the player has the largest army above 3 knight cards played*/
	private int largestArmy = -1;
	
	
	
// CONSTRUCTORS
	// temporarly error fix... haha
	public TurnManager() {}
	 
	
	/**This class keeps track of everything done during a players turn. It makes sure
	 * nothing illegal is done.*/
	public TurnManager(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat) {
		this.players = players;
		this.map = map;
		tradeManager = new TradeManager(players);
		this.cardDeck = cardDeck;
		this.bank = bank;
		hasPlayedDevCard = false;
	}
	
	
	//serialization
	public TurnManager(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat, int longestRoad, int largestArmy) {
		this.players = players;
		this.map = map;
		this.tradeManager = new TradeManager(players);
		this.cardDeck = cardDeck;
		this.bank = bank;
		this.hasPlayedDevCard = false;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}
	
// GETTERS
	public int getPlayerIndex() {
		return this.playerIndex;
	}
	
	
// SETTERS
	public void setCurrentTurn(int index) {
		players.getPlayerByIndex(index).startTurn();
		playerIndex = index;
		this.hasPlayedDevCard = false;
	}
	
	
	public void setHasPlayedDevCard(boolean hpdc) {
		this.hasPlayedDevCard = hpdc;
	}
	
	
	
// METHODS
	public void startGame() {
		Random rand = new Random(System.currentTimeMillis());
		playerIndex = rand.nextInt(4) + 1;
		players.getPlayerByIndex(playerIndex).startTurn();
	}
	
	
	
	/**
	 * @return the id of the current player whose turn it is.
	 */
	public void nextTurn() {
		playerIndex = this.players.finishTurn(playerIndex);
		hasPlayedDevCard = false;
	}
	
	
	public boolean isTurn(int index) {
		return players.getPlayerByIndex(index).isTurn();
	}
	
	
	/**
	 * If the specified player can roll the dice, do so
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 * @return The number rolled or 0 if the player does not have permission to roll the dice.
	 */
	public int rollDice() {
		int roll = Dice.rollDice();
		return roll;
	}
	
	
	public void buildRoad() throws InsufficientCardNumberException {
		players.getPlayerByIndex(playerIndex).buyRoad();
	}

	
	public void buildSettlement() throws InsufficientCardNumberException {
		players.getPlayerByIndex(playerIndex).buySettlement();
	}
	
	
	public void buildCity() throws InsufficientCardNumberException {
		players.getPlayerByIndex(playerIndex).buyCity();
	}
	
	
	public void buyDevCard() throws InsufficientCardNumberException {
		DevCardType card = cardDeck.drawCard();
		players.getPlayerByIndex(playerIndex).buyDevCard(card);
	}
	
	
	public void playDevCard(DevCardType card) throws InsufficientCardNumberException {
		hasPlayedDevCard = true;
		players.getPlayerByIndex(playerIndex).playDevCard(card);
	}
	
	
	public TradeManager getTradeManager() {
		return tradeManager;
	}
	
	
	
//***********************************************************************************************************************************
//														Can Functions
//***********************************************************************************************************************************
	public boolean CanDiscardCards(ResourceType type, int num) {
		return players.getPlayerByIndex(playerIndex).canDiscardCards(type, num);
	}
	
	
	public boolean CanRollNumber() {
		return players.getPlayerByIndex(playerIndex).canRollDice();
	}
	
	
	public boolean CanBuildRoad(int x, int y, String direction, int ownerId) {
		return players.getPlayerByIndex(playerIndex).canBuildRoad() && map.canPlaceRoad(x, y, direction, ownerId);
	}
	
	
	public boolean CanBuildSettlement(int x, int y, String direction, int ownerId) {
		return players.getPlayerByIndex(playerIndex).canBuildSettlement() && map.canPlaceSettlement(x, y, direction, ownerId);
	}
	
	
	public boolean CanBuildCity(int x, int y, String direction, int ownerId) {
		return players.getPlayerByIndex(playerIndex).canBuildCity() && map.canPlaceCity(x, y, direction, ownerId);
	}
	
	public boolean CanOfferTrade(int traderIndex, int tradeeIndex, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) {
		return tradeManager.canTrade(traderIndex, tradeeIndex, out, in);
	}
	
	
	public boolean CanMaritimeTrade(int ownerId, ResourceType type) {
		return map.canMaritimeTrade(ownerId, type);
	}
	
	
	public boolean CanFinishTurn() {
		return true;
	}
	
	
	public boolean CanBuyDevCard() {
		return players.getPlayerByIndex(playerIndex).canBuyDevCard();
	}
	
	
	public boolean CanPlayDevCard(DevCardType card) {
		int numberOfCard = players.getPlayerByIndex(playerIndex).getNumOfDevCard(card);
		if(numberOfCard > 0 && hasPlayedDevCard == false) {
			return true;
		}
		return false;
	}
	
	
	public boolean CanPlaceRobber(int x, int y) {
		return this.map.canPlaceRobber(x, y);
	}
	
	
	public boolean CanSendChat() {
		return true;
	}
	
	
	public boolean CanAcceptTrade(int traderIndex, int tradeeIndex, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) {
		return tradeManager.canTrade(traderIndex, tradeeIndex, out, in);
	}
	
	
	public String serialize() {
		String json = "turnTracker: {status: Rolling, ";
		json += "currentTurn: " + this.playerIndex + ", ";
		json += "longestRoad: " + this.longestRoad + ", ";
		json += "largestArmy: " + this.largestArmy + ", ";
		json += "}";
		return json;
	}
}
