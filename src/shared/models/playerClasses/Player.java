package shared.models.playerClasses;

public class Player {
	
	/**The id of the client that is controlling this player*/
	int id;
	
	/**Set to true if it is this player's turn*/
	boolean isTurn = false;
	
	/**The number of victory points the player has earned. The goal is 10.*/
	int victoryPoints = 0;
	
	/**This is set to true if the player has the longest road above 5 roads*/
	boolean longestRoad = false;
	
	/**This is set to true if the player has the largest army above 3 knight cards played*/
	boolean largestArmy = false;
	
	/**The number of cities this player may build*/
	int cities = 4;
	
	/**The number of settlements this player may build*/
	int settlements = 5;
	
	/**The number of roads this player may build*/
	int roads = 15;
	
	/**The number of wood cards in the player's hand*/
	int woodCards = 0;
	/**The number of brick cards in the player's hand*/
	int brickCards = 0;
	/**The number of sheep cards in the player's hand*/
	int sheepCards = 0;
	/**The number of wheat cards in the player's hand*/
	int wheatCards = 0;
	/**The number of ore cards in the player's hand*/
	int oreCards = 0;
	
	/**The number of soldier cards in the players possession*/
	int soldierCards = 0;
	/**The number of year of plenty cards in the players possession*/
	int yearOfPlentyCards = 0;
	/**The number of monopoly cards in the players possession*/
	int monopolyCards = 0;
	/**The number of road builder cards in the players possession*/
	int roadBuilderCards = 0;
	/**The number of monument cards in the players possession*/
	int monumentCards = 0;
	
	//we need a way to represent harbor benefits.
	

	public Player(int id) {
		this.id = id;
	}
	
	/** Check if it is this player's turn
	 * @return Returns true if it's the player's turn,
	 * otherwise, returns false;
	 */
	public boolean isTurn(int playerID) {
		/* Check if it's the player's turn */
		return true;
	}
	
	/**
	 * Checks if this player can roll the dice
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice(int playerID) {
		return true;
	}
	
	/**
	 * Checks if this player has the resources to
	 * build a road.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildRoad(int playerID) {
		return true;
	}
	
	/**
	 * Checks if this player has the resources to
	 * build a settlement
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildSettlement(int playerID) {
		return true;
	}
	
	/**
	 * Checks if this player has the resources to
	 * build a city.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildCity(int playerID) {
		return true;
	}
	
	/**
	 * Checks if this player has the resources to
	 * buy a development card
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuyDevCard(int playerID) {
		return true;
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buyRoad(int playerID) {
		
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buySettlement(int playerID) {
		
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buyCity(int playerID) {
		
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buyDevCard(int playerID) {
		
	}
	
	/**
	 * Exchange four of this player's resources for
	 * one of another.
	 */
	public void tradeFour(int playerID) {
		
	}
	
	/**
	 * Trade this player's resources using a harbor
	 * he/she has built on.
	 */
	public void tradeHarbor(int playerID) {
		
	}
	
	/**
	 * Subtract a soldier card from this player
	 */
	public void playSoldierCard(int playerID) {}
	
	/**
	 * Subtract a road builder card from this player
	 */
	public void playRoadBuilderCard(int playerID) {}
	
	/**
	 * Subtract a monopoly card from this player
	 */
	public void playMonopolyCard(int playerID) {}
	
	/**
	 * Subtract a year of plenty card from this player
	 */
	public void playYearOfPlentyCard(int playerID) {}
	
	
	/**
	 * Set isTurn to false
	 */
	public void finishTurn(int playerID) {
		
	}

}
