package shared.models;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.DevCardType;
import shared.definitions.GameState;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.RobberLocation;
import shared.locations.VertexLocation;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.chatClasses.GameChat;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Hex;
import shared.models.mapClasses.Map;
import shared.models.mapClasses.Piece;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.TurnManager;

/**
 * 
 * @author Stephen Snyder
 *
 */
public class Game extends Observable
{	
	/**The current state of this game*/
	GameState gameState;
	
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
	
	/**The turn tracker manages trades between players*/
	TurnManager turnManager;
	
	/**The index of the winner of the game*/
	int winner = -1;
	
	/**Each game has a version ID so the server knows which JSON to return.*/
	int versionID;
	
	
	
// CONSTRUCTORS
	public Game() 
	{
		this.gameState = GameState.LOGIN;
		this.map = new Map();
		this.bank = new Bank();
		this.cardDeck = new CardDeck();
		this.players = new GamePlayers();
		this.log = new GameLog();
		this.chat = new GameChat();
		this.turnManager = new TurnManager(map, bank, cardDeck, players, log, chat);
	}
	
	public Game(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat, 
			int currentTurn, boolean hasPlayedDevCard, int winner) 
	{
		this.map = map;
		this.bank = bank;
		this.cardDeck = cardDeck;
		this.players = players;
		this.log = log;
		this.chat = chat;
		this.turnManager = new TurnManager(map, bank, cardDeck, players, log, chat);
		this.turnManager.setCurrentTurn(currentTurn);
		this.turnManager.setHasPlayedDevCard(hasPlayedDevCard);
	}
	
	
	
// GETTERS
	public GameState getGameState()
	{
		return this.gameState;
	}
	
	
	public GamePlayers getPlayers() {
		return players;
	}
	
	
	public TurnManager getTurnManager() {
		return turnManager;
	}
	
	public GameChat getGameChat() {
		return chat;
	}
	
	public GameLog getGameLog() {
		return log;
	}
	
	/**
	 * Returns the version ID so the poller and proxy can request the correct model JSON.
	 */
	public int getVersionID() {
		return versionID;
	}
	
	
//SETTERS
	public void setGameState(GameState state) {
		this.gameState = state;
	}
	
	public void setGameChat(GameChat chat) {
		this.chat = chat;
	}
	
	public void setGameLog(GameLog log) {
		this.log = log;
	}


// OBSERVER
	@Override
	public void addObserver(Observer o)
	{
		super.addObserver(o);
	}
	
	@Override
	public void deleteObserver(Observer o) {
		super.deleteObserver(o);
	}

	

// Public METHODS
	public void update(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat, 
			int currentTurn, String currentState, boolean hasPlayedDevCard, int winner) {
		this.map = map;
		this.bank = bank;
		this.cardDeck = cardDeck;
		this.players = players;
		this.log = log;
		this.chat = chat;
		this.turnManager = new TurnManager(map, bank, cardDeck, players, log, chat);
		this.turnManager.setCurrentTurn(currentTurn);
		this.turnManager.setHasPlayedDevCard(hasPlayedDevCard);
		updateState(currentState);
		this.setChanged();
		this.notifyObservers();
	}
	
	
	// This is not done!!! -- Implement all the other possible states
	/**
	 * Intelligently calculates the current state (based on the new model)
	 * 
	 * @param currentState
	 */
	public void updateState(String currentState) { // (String currentState, int currPlayer) {
		
		switch (currentState) 
		{ 	// TODO: REMAINING -> OUTDATED, TRADEOFFER, TRADEACCEPT // TODO: not sure how these work // TODO: Also I need a way to get the currPlayer (the client's player index)
			case "Rolling":
			{
				this.gameState = GameState.OUTDATED;
			}
			case "Robbing":
			{
				this.gameState = GameState.ROBBER; // this.isTurn(currPlayer) ? GameState.ROBBER : GameState.NOTMYTURN
			}
			case "Playing":
			{
				this.gameState = GameState.MYTURN; // this.winner != -1 ? GameState.ENDOFGAME : this.isTurn(currPlayer) ? GameState.MYTURN : GameState.NOTMYTURN
			}
			case "Discarding":
			{
				this.gameState = GameState.DISCARD;
			}
			case "FirstRound":
			{
				this.gameState = GameState.SETUP1; // this.isTurn(currPlayer) ? GameState.SETUP1 : GameState.NOTMYTURN
			}
			case "SecondRound":
			{
				this.gameState = GameState.SETUP2; // this.isTurn(currPlayer) ? GameState.SETUP2 : GameState.NOTMYTURN
			}
		}
	}
	
	
	public boolean isTurn(int playerIndex) {
		return turnManager.isTurn(playerIndex);
	}
	
	
	/**
	 * If the specified player can roll the dice, do so
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 * @return The number rolled or 0 if the player does not have permission to roll the dice.
	 */
	public int rollDice(int playerID) {
		return turnManager.rollDice();
	}

	
	/**
	 * Trades a player's resources for a new road on the map. It must connect with another of the player's
	 * roads, settlements, or cities.
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildRoad(int currPlayer) throws InsufficientCardNumberException {
		turnManager.buildRoad(currPlayer);
	}
	
	
	/**
	 * Trades a player's resources for a new settlement on the map. The player must have a road leading to the spot wanted.
	 * The selected place to build must also be at least two building spots away from any other settlement.
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildSettlement(int currPlayer) throws InsufficientCardNumberException {
		turnManager.buildSettlement(currPlayer);
	}
	
	
	/**
	 * Trades a player's resources for a new city on the map. The player must build it in place of an existing settlement.
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildCity(int currPlayer) throws InsufficientCardNumberException {
		turnManager.buildCity(currPlayer);
	}
	
	
	/**
	 * Trades a player's resources for a development card
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buyDevelopmentCard(int currPlayer) throws InsufficientCardNumberException {
		turnManager.buyDevCard(currPlayer);
	}
	
	
	/**
	 * Allows a player to move the robber in exchange for a Soldier Card
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void playDevCard(DevCardType type, int currPlayer) throws InsufficientCardNumberException {
		turnManager.playDevCard(type, currPlayer);
	}
	
	
	/**
	 * Allows a player to trade int resources with the bank. If the player has built on a port, benefits may apply.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeResourcesWithBank(int playerID, int numberToTrade, ResourceType tradeIn, ResourceType tradeOut) throws InsufficientCardNumberException {
		switch(numberToTrade){
		case 4:
			turnManager.getTradeManager().tradeFour(playerID, tradeIn, tradeOut);
			break;
		case 3:
			turnManager.getTradeManager().tradeThreeWithPort(playerID, tradeIn, tradeOut);
			break;
		case 2:
			turnManager.getTradeManager().tradeTwoWithPort(playerID, tradeIn, tradeOut);
			break;
		}
	}
	
	
	/**
	 * Allows a player to offer an exchange of resources to one or more other players
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void offerATrade(int playerID, List<Integer> playersToOfferTo, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) throws InsufficientCardNumberException{
		
	}
	
	
	public Hex getHex(HexLocation loc) {
		return this.map.getHex(loc);
	}
	
	
	public Piece getEdge(EdgeLocation loc) {
		return this.map.getEdge(loc);
	}
	
	
	public Piece getVertex(VertexLocation loc) {
		return this.map.getVertex(loc);
	}
	
	
	public RobberLocation getRobberLocation() {
		return this.map.getRobberLocation();
	}
	

//***********************************************************************************************************************************
//														Can Functions
//***********************************************************************************************************************************
	

	public boolean CanDiscardCards(ResourceType type, int num, int currPlayer) {
		return turnManager.CanDiscardCards(type, num, currPlayer);
	}
	
	
	public boolean CanRollNumber(int currPlayer) {
		return turnManager.CanRollNumber(currPlayer);
	}
	
	
	public boolean CanBuildRoad(int x, int y, String direction, int currPlayer) {
		return turnManager.CanBuildRoad(x, y, direction, currPlayer);
	}
	
	
	public boolean CanBuildSettlement(int x, int y, String direction, int currPlayer) {
		return turnManager.CanBuildSettlement(x, y, direction, currPlayer);
	}
	
	
	public boolean CanBuildCity(int x, int y, String direction, int currPlayer) {
		return turnManager.CanBuildCity(x, y, direction, currPlayer);
	}
	
	
	public boolean CanOfferTrade(int traderIndex, int tradeeIndex, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) {
		return turnManager.CanOfferTrade(traderIndex, tradeeIndex, out, in);
	}
	
	
	public boolean CanMaritimeTrade(int currPlayer, ResourceType type) {
		return turnManager.CanMaritimeTrade(currPlayer, type);
	}
	
	
	public boolean CanFinishTurn() {
		return turnManager.CanFinishTurn();
	}
	
	
	public boolean CanBuyDevCard(int currPlayer) {
		return turnManager.CanBuyDevCard(currPlayer);
	}
	
	
	public boolean CanPlayDevCard(DevCardType card, int currPlayer) {
		return turnManager.CanPlayDevCard(card, currPlayer);
	}
	
	
	public boolean CanPlaceRobber(int x, int y, int currPlayer) {
		return turnManager.CanPlaceRobber(x, y, currPlayer);
	}
	
	
	public boolean CanSendChat() {
		return turnManager.CanSendChat();
	}
	
	
	public boolean CanAcceptTrade(int traderIndex, int tradeeIndex, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) {
		return turnManager.CanAcceptTrade(traderIndex, tradeeIndex, out, in);
	}
	
	
}
