package shared.models;


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
	
	/**
	 * The Game class is a master class that contains all information having to do with a specific game.
	 *  Parse and build the game using the json String
	 * 
	 * @param json
	 */
	public Game(String json)
	{
		bank = new Bank(json);
		chat = new GameChat(json);
		log = new GameLog(json);
		map = new Map(json);
		players = new GamePlayers(json);
		tradeManager = new TradeManager(json);
		turnTracker = new TurnTracker(json);
		
		
		cardDeck = new CardDeck(json);
		
	}
	
	/**
	 * Takes in a json summary of a game and changes itself to match the specified game
	 */
	public void importGame() {}
	
	/**
	 * If the specified player can roll the dice, do so
	 */
	public int rollDice(int playerID) {
		if (players.canRollDice(playerID)) {
			return dice.rollDice();
		}
		else{
			return 0;
		}
	}

	/**
	 * Trades a player's resources for a new road on the map. It must connect with another of the player's
	 * roads, settlements, or cities.
	 */
	public void buildRoad(int playerID) {
		if(players.canBuildRoad(playerID)) {
			
		}
	}
	
	/**
	 * Trades a player's resources for a new settlement on the map. The player must have a road leading to the spot wanted.
	 * The selected place to build must also be at least two building spots away from any other settlement.
	 */
	public void buildSettlement(int playerID) {
		if(players.canBuildSettlement(playerID)) {
			players.canBuildSettlement(playerID);
		}
	}
	
	/**
	 * Trades a player's resources for a new city on the map. The player must build it in place of an existing settlement.
	 */
	public void buildCity(int playerID) {
		if(players.canBuildCity(playerID)) {
			players.canBuildCity(playerID);
		}
	}
	
	/**
	 * Trades a player's resources for a development card
	 */
	public void buyDevelopmentCard(int playerID) {
		if(players.canBuyDevCard(playerID)) {
			players.buyDevCard(playerID);
		}
	}
	
	/**
	 * Allows a player to move the robber in exchange for a Soldier Card
	 */
	public void playSoldierCard(int playerID) {
		players.playSoldierCard(playerID);
	}
	
	/**
	 * Allows a player to build two roads in exchange for a Road Builder Card
	 */
	public void playRoadBuilderCard(int playerID) {
		players.playRoadBuilderCard(playerID);
	}
	
	/**
	 * Allows a player to take all owned cards of a specified resource in exchange for a Monopoly Card
	 */
	public void playMonopolyCard(int playerID) {
		players.playMonopolyCard(playerID);
	}
	
	/**
	 * Allows a player to choose two resources to be added to it's hand in exchange for a Year Of Plenty Card
	 */
	public void playYearOfPlentyCard(int playerID) {
		players.playYearOfPlentyCard(playerID);
	}
	
	/**
	 * Allows a player to offer an exchange of resources to one or more other players
	 */
	public void offerATrade(int playerID){}
	
	/**
	 * Check the TurnTracker to see if it is the user's turn at the given index
	 * 
	 * @param playerIndex
	 */
	public void isTurn(int playerIndex) {}
}
