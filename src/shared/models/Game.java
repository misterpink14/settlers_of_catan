package shared.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.clientFacade.ClientFacade;
import shared.communication.proxy.OfferTrade;
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
import shared.models.mapClasses.InvalidTypeException;
import shared.models.mapClasses.Map;
import shared.models.mapClasses.Piece;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.Player;
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
	
	OfferTrade offerTrade = null;
	
	/**The turn tracker manages trades between players*/
	TurnManager turnManager;
	
	/**The index of the winner of the game*/
	int winner = -1;
	
	/**Each game has a version ID so the server knows which JSON to return.*/
	int versionID;
	
	int currPlayer = -1;  // Index of player (client)
	
	
	
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
		this.turnManager = new TurnManager(map, bank, cardDeck, players, log, chat, -1);
	}
	
	public Game(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat, 
			int currentTurn, boolean hasPlayedDevCard, int winner, int longestRoad) 
	{
		this.map = map;
		this.bank = bank;
		this.cardDeck = cardDeck;
		this.players = players;
		this.log = log;
		this.chat = chat;
		this.turnManager = new TurnManager(map, bank, cardDeck, players, log, chat, longestRoad);
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
	
	public int getWinner() {
		return this.winner;
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
	
	
	public int getLongestRoad() {
		return this.turnManager.getLongestRoad();
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
	/*
	 * method to move the robber.
	 */
	public ArrayList<Piece> placeRobber (HexLocation hexLoc)
	{
		return this.map.placeRobber(hexLoc);
	}
	
	
	/**
	 * Update the game from a collection of parameters deserialized from a json object.
	 * @param map
	 * @param bank
	 * @param cardDeck
	 * @param players
	 * @param log
	 * @param chat
	 * @param offerTrade
	 * @param currentTurn
	 * @param currentState
	 * @param hasPlayedDevCard
	 * @param winner
	 * @param longestRoad
	 */
	public void update(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat, 
			OfferTrade offerTrade, int currentTurn, String currentState, boolean hasPlayedDevCard, int winner, int longestRoad) {
		this.map = map;
		this.bank = bank;
		this.cardDeck = cardDeck;
		this.players = players;
		this.currPlayer = ClientFacade.getInstance().getUserData().getPlayerIndex();
		this.log = log;
		this.chat = chat;
		this.offerTrade = offerTrade;
		this.turnManager = new TurnManager(map, bank, cardDeck, players, log, chat, longestRoad);
		this.turnManager.setCurrentTurn(currentTurn);
		this.turnManager.setHasPlayedDevCard(hasPlayedDevCard);
		updateState(currentState);
		this.setChanged();
		this.notifyObservers();
	}
	
	

	/**
	 * Use for testing the Server Poller. Removes the need for the ClientFacade
	 * 
	 * @param map
	 * @param bank
	 * @param cardDeck
	 * @param players
	 * @param log
	 * @param chat
	 * @param currentTurn
	 * @param currentState
	 * @param hasPlayedDevCard
	 * @param winner
	 */
	public void updateForTest(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat, 
			OfferTrade offerTrade, int currentTurn, String currentState, boolean hasPlayedDevCard, int winner, int longestRoad) {
		this.map = map;
		this.bank = bank;
		this.cardDeck = cardDeck;
		this.players = players;
		this.log = log;
		this.chat = chat;
		this.offerTrade = offerTrade;
		this.turnManager = new TurnManager(map, bank, cardDeck, players, log, chat, longestRoad);
		this.turnManager.setCurrentTurn(currentTurn);
		this.turnManager.setHasPlayedDevCard(hasPlayedDevCard);
		this.setChanged();
		this.notifyObservers();
	}
	
	
	// This is not done!!! -- Implement all the other possible states
	/**
	 * Intelligently calculates the current state (based on the new model)
	 * 
	 * @param currentState
	 */
	public void updateState(String currentState) {
		
		switch (currentState) 
		{ 	// TODO: REMAINING -> OUTDATED, TRADEOFFER, TRADEACCEPT // TODO: not sure how these work // TODO: Also I need a way to get the currPlayer (the client's player index)
			case "Rolling":
				this.gameState = this.isTurn(this.currPlayer) ? GameState.ROLLING : GameState.NOTMYTURN;
				break;
			case "Robbing":
				this.gameState = this.isTurn(this.currPlayer) ? GameState.ROBBER : GameState.NOTMYTURN;
				break;
			case "Playing":
				if (this.winner != -1)
				{
					this.gameState = GameState.ENDOFGAME;
				}
				else
				{
					if (this.isTurn(currPlayer))
					{
						this.gameState = GameState.MYTURN;
					}
					else
					{
						this.gameState = GameState.NOTMYTURN;
					}
				}
				break;
			case "Discarding":
				this.gameState = GameState.DISCARD;
				break;
			case "FirstRound":
				this.gameState = this.isTurn(currPlayer) ? GameState.SETUP1 : GameState.NOTMYTURN;
				break;
			case "SecondRound":
				this.gameState = this.isTurn(currPlayer) ? GameState.SETUP2 : GameState.NOTMYTURN;
				break;
			default:
				break;
		}
	}
	
	/**
	 * returns if the specified player index contains the player whose turn it is.
	 * @param playerIndex
	 * @return
	 */
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
	 * @throws InvalidTypeException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildRoad(int currPlayer, EdgeLocation loc) throws InsufficientCardNumberException, InvalidTypeException {
		turnManager.buildRoad(currPlayer, loc);
	}
	
	
	/**
	 * Trades a player's resources for a new settlement on the map. The player must have a road leading to the spot wanted.
	 * The selected place to build must also be at least two building spots away from any other settlement.
	 * @throws InsufficientCardNumberException 
	 * @throws InvalidTypeException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildSettlement(int currPlayer, VertexLocation loc) throws InsufficientCardNumberException, InvalidTypeException {
		turnManager.buildSettlement(currPlayer, loc);
	}
	
	
	/**
	 * Trades a player's resources for a new city on the map. The player must build it in place of an existing settlement.
	 * @throws InsufficientCardNumberException 
	 * @throws InvalidTypeException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buildCity(int currPlayer, VertexLocation loc) throws InsufficientCardNumberException, InvalidTypeException {
		turnManager.buildCity(currPlayer, loc);
	}
	
	
	/**
	 * Trades a player's resources for a development card
	 * @throws InsufficientCardNumberException 
	 * @exception invalidPlayerID if the player id does not match an existing player.
	 */
	public void buyDevelopmentCard(int currPlayer) throws InsufficientCardNumberException {
		turnManager.buyDevCard(currPlayer);
	}
	
	
	//play dev card methods
	public void playSoldierCard(int currPlayer, HexLocation hexLoc, int victimIndex) {
		turnManager.playSoldierCard(currPlayer, hexLoc, victimIndex);
	}
	
	public void playYearOfPlentyCard(int currPlayer, ResourceType type1, ResourceType type2) {
		turnManager.playYearOfPlentyCard(currPlayer, type1, type2);
	}
	
	public void playRoadBuildingCard(int currPlayer, EdgeLocation loc) throws InvalidTypeException {
		turnManager.playRoadBuildingCard(currPlayer, loc);
	}
	
	public void playMonumentCard(int currPlayer) {
		turnManager.playMonumentCard(currPlayer);
	}
	
	public void playMonopolyCard(int currPlayer, ResourceType type) {
		turnManager.playMonopolyCard(currPlayer, type);
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
	
	public int getMaritimeTradeRatio(int playerIndex, ResourceType type) {
		return turnManager.getMaritimeTradeRatio(playerIndex, type);
	}
	
	public Bank getBank() {
		return bank;
	}
	
	/**
	 * returns a trade offer that has been made and is currently open.
	 * @return
	 */
	public OfferTrade getOfferTrade() {
		return offerTrade;
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
	
	
	public boolean CanBuildRoad(EdgeLocation edgeLoc, int currPlayer, boolean isFree, boolean isSetup) {
		return turnManager.CanBuildRoad(edgeLoc, currPlayer, isFree, isSetup);
	}
	
	public boolean CanBuildSettlement(VertexLocation vertLoc, int currPlayer, boolean isFree, boolean isSetup) {
		return turnManager.CanBuildSettlement(vertLoc, currPlayer, isFree, isSetup);
	}
	
	
	public boolean CanBuildCity(VertexLocation vertLoc, int currPlayer) {
		return turnManager.CanBuildCity(vertLoc, currPlayer);
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
	
	
	public boolean CanPlaceRobber(HexLocation hexLoc, int currPlayer) {
		return turnManager.CanPlaceRobber(hexLoc, currPlayer);
	}
	
	
	public boolean CanSendChat() {
		return turnManager.CanSendChat();
	}
	
	
	public boolean CanAcceptTrade(int traderIndex, int tradeeIndex, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) {
		return turnManager.CanAcceptTrade(traderIndex, tradeeIndex, out, in);
	}

	public Boolean settlementTouchesPlayerRoad(VertexLocation loc, int ownerID) {
		return this.map.settlementTouchesPlayerRoad(loc, ownerID);
	}
}
