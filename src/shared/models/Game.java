package shared.models;
/**
 * 
 * @author Stephen Snyder
 *
 */
public class Game {
	/**The map contains all information having to do with the board.*/
	Map map = new Map();
	/**The bank contains all resource cards that do not belong to a player.*/
	Bank bank = new Bank();
	/**The cardDeck contains all the development cards not belonging to a player.*/
	CardDeck cardDeck = new CardDeck();
	/**The game players object holds four player objects that represent four clients that will connect
	 * to the server.
	 */
	GamePlayers players = new GamePlayers();
	
	
	/**
	 * The Game class is a master class that contains all information having to do with a specific game
	 * of settlers of catan.
	 */
	public Game() {}

	/**
	 * Trades a player's resources for a new road on the map. It must border a settlement or road owned by the player.
	 */
	public void buildRoad(){}
	
	/**
	 * Trades a player's resources for a new settlement on the map. The player must have a road leading to the spot wanted.
	 * The selected place to build must also be at least two building spots away from any other settlement.
	 */
	public void buildSettlement(){}
	
	/**
	 * Trades a player's resources for a new city on the map. The player must build it in place of an existing settlement.
	 */
	public void buildCity(){}
	
	/**
	 * Trades a player's resources for a development card
	 */
	public void buyDevelopmentCard(){}
	
	/**
	 * Allows a player to move the robber in exchange for a Soldier Card
	 */
	public void playSoldierCard(){}
	
	/**
	 * Allows a player to build two roads in exchange for a Road Builder Card
	 */
	public void playRoadBuilderCard(){}
	
	/**
	 * Allows a player to take all owned cards of a specified resource in exchange for a Monopoly Card
	 */
	public void playMonopolyCard(){}
	
	/**
	 * Allows a player to choose two resources to be added to it's hand in exchange for a Year Of Plenty Card
	 */
	public void playYearOfPlentyCard(){}
	
	/**
	 * Allows a player to offer an exchange of resources to one or more other players
	 */
	public void offerATrade(){}
}
