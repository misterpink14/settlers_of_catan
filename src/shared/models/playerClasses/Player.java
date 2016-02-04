package shared.models.playerClasses;

import java.util.HashMap;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.cardClasses.DevCards;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.cardClasses.ResourceCards;

public class Player {
	
	private final int MAX_ROADS = 15;
	private final int MAX_SETTLEMENTS = 5;
	private final int MAX_CITIES = 4;
	
	/**The id of the client that is controlling this player*/
	private int id;
	
	/**String with the player's name*/
	private String name;
	
	/**The player's color*/
	private CatanColor color;
	
	/**Set to true if it is this player's turn*/
	private boolean isTurn = false;
	
	/**The number of victory points the player has earned. The goal is 10.*/
	private int victoryPoints = 0;
	
	/**This is set to true if the player has the longest road above 5 roads*/
	private boolean longestRoad = false;
	
	/**This is set to true if the player has the largest army above 3 knight cards played*/
	private boolean largestArmy = false;
	
	/**The number of cities this player may build*/
	private int cities = MAX_CITIES;
	
	/**The number of settlements this player may build*/
	private int settlements = MAX_SETTLEMENTS;
	
	/**The number of roads this player may build*/
	private int roads = MAX_ROADS;
	
	/**A container for this player's resource cards.*/
	private ResourceCards resourceCards = new ResourceCards(0,0,0,0,0);
	
	/**A container for this player's development cards*/
	private DevCards devCards = new DevCards(0,0,0,0,0);
	
	/**A container for this player's used dev cards*/
	private DevCards usedDevCards = new DevCards(0,0,0,0,0);
	
	/**The number of soldierCards this player has played*/
	private int army = 0;
	
	/**The number of Monument cards this player has played.*/
	private int monuments = 0;
	
	//we need a way to represent Harbor benefits

	public Player(int id, String name, CatanColor color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}
	
	public Player(HashMap<ResourceType, Integer> resources, HashMap<DevCardType, Integer> oldDevCards, HashMap<DevCardType, Integer> devCards,
			int roads, int cities, int settlements, int soldiers, int victoryPoints, int monuments, int id, String name, CatanColor color,
			boolean largestArmy, boolean longestRoad) {
		for(ResourceType type : resources.keySet()) {
			this.resourceCards.addCard(type, resources.get(type));
		}
		for(DevCardType type : oldDevCards.keySet()) {
			this.usedDevCards.addCards(type, oldDevCards.get(type));
		}
		for(DevCardType type : devCards.keySet()) {
			this.devCards.addCards(type, devCards.get(type));
		}
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.army = soldiers;
		this.victoryPoints = victoryPoints;
		this.monuments = monuments;
		this.id = id;
		this.name = name;
		this.color = color;
		this.largestArmy = largestArmy;
		this.longestRoad = longestRoad;
	}

	/**
	 * Serialized player constructor
	 */
	public Player(ResourceCards resourceCards, DevCards oldDevCards, DevCards newDevCards,
				  int numRoads, int numCities, int numSettlements, int numSoldiers,
				  int numVictoryPoints, int numMonuments, boolean playedDevCard,
				  boolean discarded, int playerIndex, String name, CatanColor color) {
		this.resourceCards = resourceCards;
		this.usedDevCards = oldDevCards;
		this.devCards = newDevCards;
		this.roads = MAX_ROADS - numRoads;
		this.cities = MAX_CITIES - numCities;
		this.settlements = MAX_SETTLEMENTS - settlements;
		this.victoryPoints = numVictoryPoints;
		this.id = playerIndex;
		this.name = name;
	}
	
	/**Checks if this player has the longest road*/
	public boolean isLongestRoad() {
		return longestRoad;
	}

	/**gives this player the longest road*/
	public void setLongestRoad(boolean longestRoad) {
		if(longestRoad == true && this.longestRoad == false) {
			this.victoryPoints += 2;
		}
		if(longestRoad == false && this.longestRoad == true) {
			this.victoryPoints -= 2;
		}
		this.longestRoad = longestRoad;
	}

	/**checks if this player has the largest army*/
	public boolean isLargestArmy() {
		return largestArmy;
	}

	/**Gives this player the largest army*/
	public void setLargestArmy(boolean largestArmy) {
		if(largestArmy == true && this.largestArmy == false) {
			this.victoryPoints += 2;
		}
		if(largestArmy == false && this.largestArmy == true) {
			this.victoryPoints -= 2;
		}
		this.largestArmy = largestArmy;
	}

	/**Gets this player's name*/
	public String getName() {
		return name;
	}

	/**Gets this players Color*/
	public CatanColor getColor() {
		return color;
	}

	/**Gets this players victory points*/
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
	/**Gets this players army */
	public int getArmy() {
		return army;
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
	 * Set isTurn to false
	 */
	public void finishTurn() {
		this.isTurn = false;
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
		victoryPoints++;
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
		victoryPoints++;
	}
	
	/**
	 * Deducts resources from this player
	 * in order to buy a development card.
	 * @throws InsufficientCardNumberException 
	 */
	public void buyDevCard(DevCardType card) throws InsufficientCardNumberException {
		resourceCards.removeCard(ResourceType.WHEAT, 1);
		resourceCards.removeCard(ResourceType.ORE, 1);
		resourceCards.removeCard(ResourceType.SHEEP, 1);
		this.devCards.addCard(card);
	}
	
	/**
	 * Subtract a specified development card from this player
	 * @throws InsufficientCardNumberException 
	 */
	public void playDevCard(DevCardType type) throws InsufficientCardNumberException {
		devCards.removeCard(type);
		usedDevCards.addCard(type);
		if(type == DevCardType.MONUMENT) {
			victoryPoints++;
		}
		if(type == DevCardType.SOLDIER) {
			army++;
		}
		if(type == DevCardType.ROAD_BUILD) {
			roads -= 2;
		}
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
	
	//**************************************************************************************************************************************
	//													Can Methods
	//**************************************************************************************************************************************
	
	public boolean canDiscardCards(ResourceType type, int num) {
		return this.resourceCards.canRemove(type, num);
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

}
