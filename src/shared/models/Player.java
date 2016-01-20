package shared.models;

public class Player {
	
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
	

	public Player() {}

}
