package shared.models.playerClasses;

import shared.models.cardClasses.DevCards;
import shared.models.cardClasses.ResourceCards;

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
	
	/**A container for this player's resource cards.*/
	ResourceCards resourceCards = new ResourceCards(0,0,0,0,0);
	
	/**A container for this player's development cards*/
	DevCards devCards = new DevCards(0,0,0,0,0);
	
	//we need a way to represent Harbor benefits

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
