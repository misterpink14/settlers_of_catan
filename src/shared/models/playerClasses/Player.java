package shared.models.playerClasses;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.cardClasses.DevCards;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.cardClasses.ResourceCards;

public class Player {
	
	/**The id of the client that is controlling this player*/
	private int id;
	
	/**Set to true if it is this player's turn*/
	private boolean isTurn = false;
	
	/**The number of victory points the player has earned. The goal is 10.*/
	private int victoryPoints = 0;
	
	/**This is set to true if the player has the longest road above 5 roads*/
	private boolean longestRoad = false;
	
	/**This is set to true if the player has the largest army above 3 knight cards played*/
	private boolean largestArmy = false;
	
	/**The number of cities this player may build*/
	private int cities = 4;
	
	/**The number of settlements this player may build*/
	private int settlements = 5;
	
	/**The number of roads this player may build*/
	private int roads = 15;
	
	/**A container for this player's resource cards.*/
	private ResourceCards resourceCards = new ResourceCards(0,0,0,0,0);
	
	/**A container for this player's development cards*/
	private DevCards devCards = new DevCards(0,0,0,0,0);
	
	//we need a way to represent Harbor benefits

	public Player(int id) {
		this.id = id;
	}
	
	/** Check if it is this player's turn
	 * @return Returns true if it's the player's turn,
	 * otherwise, returns false;
	 */
	public boolean isTurn() {
		return this.isTurn;
	}
	
	/**Set isTurn to true*/
	public void startTurn() {
		this.isTurn = true;
	}
	
	/**
	 * Adds resources to this player's resourceCards object
	 * @param type the type of resource to add.
	 * @param num the number to add.
	 */
	public void addResourceToHand(ResourceType type, int num) {
		resourceCards.addCard(type, num);
	}
	
	/**
	 * Removes resources from this player's resourceCards object
	 * @param type the type of resource to remove
	 * @param num the number of resources to remove
	 * @throws InsufficientCardNumberException 
	 */
	public void removeResourceFromHand(ResourceType type, int num) throws InsufficientCardNumberException {
		resourceCards.removeCard(type, num);
	}
	
	/**
	 * Returns the number of a type of resource in the player's hand.
	 * @param type The type of resource to get
	 * @return the number of the type of resources in the player's hand
	 */
	public int getNumOfResource(ResourceType type) {
		int number = 0;
		switch(type){
		case BRICK:
			number = resourceCards.getBrickCards();
			break;
		case ORE:
			number = resourceCards.getOreCards();
			break;
		case SHEEP:
			number = resourceCards.getSheepCards();
			break;
		case WHEAT:
			number = resourceCards.getWheatCards();
			break;
		case WOOD:
			number = resourceCards.getWoodCards();
			break;
		}
		return number;
	}
	
	/**
	 * Checks if this player can roll the dice
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice() {
		return this.isTurn;
	}
	
	/**
	 * Checks if this player has the resources to
	 * build a road.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildRoad() {
		if(this.isTurn && resourceCards.getBrickCards() >= 1
				&& resourceCards.getWoodCards() >= 1 && roads > 0) {
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
	public boolean canBuildSettlement() {
		if(this.isTurn && resourceCards.getBrickCards() >= 1
				&& resourceCards.getSheepCards() >= 1
				&& resourceCards.getWoodCards() >= 1
				&& resourceCards.getWheatCards() >= 1 && settlements > 0) {
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
	public boolean canBuildCity() {
		if(this.isTurn && resourceCards.getOreCards() >= 3
				&& resourceCards.getWheatCards() >= 2 && cities > 0) {
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
	public boolean canBuyDevCard() {
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
	 * @throws InsufficientCardNumberException 
	 */
	public void buyRoad() throws InsufficientCardNumberException {
		resourceCards.removeCard(ResourceType.WOOD, 1);
		resourceCards.removeCard(ResourceType.BRICK, 1);
		roads--;
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buySettlement() throws InsufficientCardNumberException {
		resourceCards.removeCard(ResourceType.WHEAT, 1);
		resourceCards.removeCard(ResourceType.WOOD, 1);
		resourceCards.removeCard(ResourceType.SHEEP, 1);
		resourceCards.removeCard(ResourceType.BRICK, 1);
		settlements--;
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buyCity() throws InsufficientCardNumberException {
		resourceCards.removeCard(ResourceType.ORE, 3);
		resourceCards.removeCard(ResourceType.WHEAT, 2);
		cities--;
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buyDevCard() throws InsufficientCardNumberException {
		resourceCards.removeCard(ResourceType.WHEAT, 1);
		resourceCards.removeCard(ResourceType.ORE, 1);
		resourceCards.removeCard(ResourceType.SHEEP, 1);
	}
	
	/**
	 * Exchange four of this player's resources for
	 * one of another.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeFour(ResourceType tradeIn, ResourceType tradeOut) throws InsufficientCardNumberException {
		resourceCards.removeCard(tradeIn, 4);
		resourceCards.addCard(tradeOut, 1);
	}
	
	/**
	 * Trade this player's resources using a harbor
	 * he/she has built on.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeTwoWithPort(ResourceType portType, ResourceType returnType) throws InsufficientCardNumberException {
		resourceCards.removeCard(portType, 2);
		resourceCards.addCard(returnType, 1);		
	}
	
	public void tradeThreeWithPort(ResourceType portType, ResourceType returnType) throws InsufficientCardNumberException {
		resourceCards.removeCard(portType, 3);
		resourceCards.addCard(returnType, 1);		
	}
	
	/**
	 * Subtract a specified development card from this player
	 * @throws InsufficientCardNumberException 
	 */
	public void playDevCard(DevCardType type) throws InsufficientCardNumberException {
		devCards.removeCard(type);
	}
	
	/**
	 * Subtract a specified development card from this player
	 */
	public void drawDevCard(DevCardType type) {
		devCards.addCard(type);
	}
	
	/**
	 * Returns the number of a type of development Card in the player's hand. Mostly for testing purposes
	 * @param type The type of card to get
	 * @return the number of the type of card in the player's hand
	 */
	public int getNumOfDevCard(DevCardType type) {
		int number = 0;
		switch(type){
		case SOLDIER:
			number = devCards.getSoldierCards();
			break;
		case MONUMENT:
			number = devCards.getMonumentCards();
			break;
		case MONOPOLY:
			number = devCards.getMonopolyCards();
			break;
		case ROAD_BUILD:
			number = devCards.getRoadBuilderCards();
			break;
		case YEAR_OF_PLENTY:
			number = devCards.getYearOfPlentyCards();
			break;	
		}
		return number;
	}
	
	/**
	 * Set isTurn to false
	 */
	public void finishTurn() {
		this.isTurn = false;
	}

}
