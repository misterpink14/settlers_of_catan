package shared.models.playerClasses;

import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
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
		return this.isTurn;
	}
	
	/**Set isTurn to true*/
	public void startTurn() {
		this.isTurn = true;
	}
	
	/**
	 * Checks if this player can roll the dice
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice(int playerID) {
		return this.isTurn;
	}
	
	/**
	 * Checks if this player has the resources to
	 * build a road.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildRoad(int playerID) {
		if(this.isTurn && resourceCards.getBrickCards() >= 1
				&& resourceCards.getWoodCards() >= 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if this player has the resources to
	 * build a settlement
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildSettlement(int playerID) {
		if(this.isTurn && resourceCards.getBrickCards() >= 1
				&& resourceCards.getSheepCards() >= 1
				&& resourceCards.getWoodCards() >= 1
				&& resourceCards.getWheatCards() >= 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if this player has the resources to
	 * build a city.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildCity(int playerID) {
		if(this.isTurn && resourceCards.getOreCards() >= 3
				&& resourceCards.getWheatCards() >= 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if this player has the resources to
	 * buy a development card
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuyDevCard(int playerID) {
		if(this.isTurn && resourceCards.getOreCards() >= 1
				&& resourceCards.getSheepCards() >= 1
				&& resourceCards.getWheatCards() >= 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buyRoad(int playerID) {
		resourceCards.removeCard(ResourceType.WOOD, 1);
		resourceCards.removeCard(ResourceType.BRICK, 1);
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buySettlement(int playerID) {
		resourceCards.removeCard(ResourceType.WHEAT, 1);
		resourceCards.removeCard(ResourceType.WOOD, 1);
		resourceCards.removeCard(ResourceType.SHEEP, 1);
		resourceCards.removeCard(ResourceType.BRICK, 1);
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buyCity(int playerID) {
		resourceCards.removeCard(ResourceType.ORE, 3);
		resourceCards.removeCard(ResourceType.WHEAT, 2);
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 */
	public void buyDevCard(int playerID) {
		resourceCards.removeCard(ResourceType.WHEAT, 1);
		resourceCards.removeCard(ResourceType.ORE, 1);
		resourceCards.removeCard(ResourceType.SHEEP, 1);
	}
	
	/**
	 * Exchange four of this player's resources for
	 * one of another.
	 */
	public void tradeFour(int playerID, ResourceType tradeIn, ResourceType tradeOut) {
		resourceCards.removeCard(tradeIn, 4);
		resourceCards.addCard(tradeOut, 1);
	}
	
	/**
	 * Trade this player's resources using a harbor
	 * he/she has built on.
	 */
	public void tradeTwoWithPort(int playerID, ResourceType portType, ResourceType returnType) {
		resourceCards.removeCard(portType, 2);
		resourceCards.addCard(returnType, 1);		
	}
	
	public void tradeThreeWithPort(int playerID, ResourceType portType, ResourceType returnType) {
		resourceCards.removeCard(portType, 3);
		resourceCards.addCard(returnType, 1);		
	}
	
	/**
	 * Subtract a specified development card from this player
	 */
	public void playDevCard(int playerID, DevCardType type) {
		devCards.removeCard(type);
	}
	
	/**
	 * Subtract a specified development card from this player
	 */
	public void drawDevCard(int playerID, DevCardType type) {
		devCards.addCard(type);
	}
	
	/**
	 * Set isTurn to false
	 */
	public void finishTurn(int playerID) {
		this.isTurn = false;
	}

}
