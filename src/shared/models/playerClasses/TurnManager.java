package shared.models.playerClasses;

import java.util.HashMap;
import java.util.Random;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
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
	
	int clientIndex = -1;
	
	/**Keeps track of whether the player has played a dev card.*/
	private boolean hasPlayedDevCard;
	
	/**This is set to true if the player has the longest road above 5 roads*/
	private int longestRoad = -1;
	
	/**This is set to true if the player has the largest army above 3 knight cards played*/
	private int largestArmy = -1;
	
	
	
// CONSTRUCTORS
	public TurnManager() {}
	 
	
	/**This class keeps track of everything done during a players turn. It makes sure
	 * nothing illegal is done.*/
	public TurnManager(Map map, Bank bank, CardDeck cardDeck, GamePlayers players, GameLog log, GameChat chat, int longestRoad) {
		this.players = players;
		this.map = map;
		tradeManager = new TradeManager(players);
		this.cardDeck = cardDeck;
		this.bank = bank;
		hasPlayedDevCard = false;
		this.longestRoad = longestRoad;
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
	
	
	public TradeManager getTradeManager() {
		return tradeManager;
	}
	
	
	public GamePlayers getPlayers(){
		return this.players;
	}
	
	public int getLongestRoad() {
		return this.longestRoad;
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
	public void startGame(int currPlayer) {
		Random rand = new Random(System.currentTimeMillis());
		playerIndex = rand.nextInt(4) + 1;
		players.getPlayerByIndex(currPlayer).startTurn();
	}
	
	
	
	/**
	 * @return the id of the current player whose turn it is.
	 */
	public void nextTurn(int currPlayer) {
		playerIndex = this.players.finishTurn(currPlayer);
		hasPlayedDevCard = false;
	}
	
	
	public boolean isTurn(int currPlayer) {
		return players.getPlayerByIndex(currPlayer).isTurn();
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
	
	
	public void buildRoad(int currPlayer) throws InsufficientCardNumberException {
		players.getPlayerByIndex(currPlayer).buyRoad();
	}

	
	public void buildSettlement(int currPlayer) throws InsufficientCardNumberException {
		players.getPlayerByIndex(currPlayer).buySettlement();
	}
	
	
	public void buildCity(int currPlayer) throws InsufficientCardNumberException {
		players.getPlayerByIndex(currPlayer).buyCity();
	}
	
	
	public void buyDevCard(int currPlayer) throws InsufficientCardNumberException {
		DevCardType card = cardDeck.drawCard();
		players.getPlayerByIndex(currPlayer).buyDevCard(card);
	}
	
	public void playSoldierCard(int currPlayer, HexLocation hexLoc, int victimIndex) {
		try {
			this.players.getPlayerByIndex(currPlayer).removeDevCard(DevCardType.SOLDIER);
		} catch (InsufficientCardNumberException e) {System.out.println("Failed to remove soldier card from players hand.");}
		
		this.map.placeRobber(hexLoc);
		ResourceType robbedResource;
		try {
			robbedResource = this.players.getPlayerByIndex(victimIndex).removeRandomResource();
			this.players.getPlayerByIndex(currPlayer).addResourceToHand(robbedResource, 1);
		} catch (InsufficientCardNumberException e) {}
	}
	
	public void playYearOfPlentyCard(int currPlayer, ResourceType type1, ResourceType type2) {
		try {
			this.players.getPlayerByIndex(currPlayer).removeDevCard(DevCardType.YEAR_OF_PLENTY);
		} catch (InsufficientCardNumberException e) {System.out.println("Failed to remove year of plenty card from players hand.");}
		
		players.getPlayerByIndex(currPlayer).addResourceToHand(type1, 1);
		players.getPlayerByIndex(currPlayer).addResourceToHand(type2, 1);
	}
	
	public void playRoadBuildingCard(int currPlayer) {
		try {
			this.players.getPlayerByIndex(currPlayer).removeDevCard(DevCardType.ROAD_BUILD);
		} catch (InsufficientCardNumberException e) {System.out.println("Failed to remove roadbuilding card from players hand.");}
		
		//placeRoad
	}
	
	public void playMonumentCard(int currPlayer) {
		try {
			this.players.getPlayerByIndex(currPlayer).removeDevCard(DevCardType.MONUMENT);
		} catch (InsufficientCardNumberException e) {System.out.println("Failed to remove monument card from players hand.");}
		
		this.players.getPlayerByIndex(currPlayer).addVictoryPoint();
		this.players.getPlayerByIndex(currPlayer).addMonument();
	}
	
	public void playMonopolyCard(int currPlayer, ResourceType type) {
		try {
			this.players.getPlayerByIndex(currPlayer).removeDevCard(DevCardType.MONOPOLY);
		} catch (InsufficientCardNumberException e) {System.out.println("Failed to remove monopoly card from players hand.");}
		
		for (Player player : players.getPlayers()) {
			if (player.getIndex() != currPlayer) {
				int num = player.getNumOfResource(type);
				try {
					player.removeResourceFromHand(type, num);
				} catch (InsufficientCardNumberException e) {
					System.out.println("Somehow the monopoly card tried to take too many resources from a player.");
				}
				players.getPlayerByIndex(currPlayer).addResourceToHand(type, num);
			}
		}
	}
	
//***********************************************************************************************************************************
//														Can Functions
//***********************************************************************************************************************************
	public boolean CanDiscardCards(ResourceType type, int num, int currPlayer) {
		return this.isTurn(currPlayer) && players.getPlayerByIndex(currPlayer).canDiscardCards(type, num);
	}
	
	
	public boolean CanRollNumber(int currPlayer) {
		return this.isTurn(currPlayer) && players.getPlayerByIndex(currPlayer).canRollDice();
	}
	
	public boolean CanBuildRoad(EdgeLocation edgeLoc, int currPlayer, boolean isFree, boolean isSetup) {
		return this.isTurn(currPlayer) && 
				(players.getPlayerByIndex(currPlayer).canBuildRoad() || isFree) && 
				map.canPlaceRoad(edgeLoc, currPlayer, isSetup);
	}
	
	
	public boolean CanBuildSettlement(VertexLocation vertexLoc, int currPlayer, boolean isFree, boolean isSetup) {
		return this.isTurn(currPlayer) && 
				(players.getPlayerByIndex(currPlayer).canBuildSettlement() || isFree) && 
				map.canPlaceSettlement(vertexLoc, currPlayer, isSetup);
	}
	
	
	public boolean CanBuildCity(VertexLocation vertexLoc, int currPlayer) {
		return this.isTurn(currPlayer) && 
				players.getPlayerByIndex(currPlayer).canBuildCity() && 
				map.canPlaceCity(vertexLoc, currPlayer);
	}
	
	public boolean CanOfferTrade(int traderIndex, int tradeeIndex, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) {
		return this.isTurn(traderIndex) && tradeManager.canTrade(traderIndex, tradeeIndex, out, in);
	}
	
	
	public boolean CanMaritimeTrade(int currPlayer, ResourceType type) {
		return this.isTurn(currPlayer) && map.canMaritimeTrade(currPlayer, type);
	}
	
	
	public boolean CanFinishTurn() {
		return true;
	}
	
	
	public boolean CanBuyDevCard(int currPlayer) {
		return this.isTurn(currPlayer) && players.getPlayerByIndex(currPlayer).canBuyDevCard();
	}
	
	
	public boolean CanPlayDevCard(DevCardType card, int currPlayer) {
		int numberOfCard = players.getPlayerByIndex(currPlayer).getNumOfDevCard(card);
		if(this.isTurn(currPlayer) && numberOfCard > 0 && hasPlayedDevCard == false) {
			return true;
		}
		return false;
	}
	
	
	public boolean CanPlaceRobber(HexLocation hexLoc, int currPlayer) {
		return this.isTurn(currPlayer) && this.map.canPlaceRobber(hexLoc);
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


	public int getMaritimeTradeRatio(int playerIndex, ResourceType type) {
		return map.getMaritimeTradeRatio(playerIndex, type);
	}
}
