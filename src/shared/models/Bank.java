package shared.models;

public class Bank {
	
	/**The number of wood cards currently available in the game*/
	int woodCards = 19;
	/**The number of brick cards currently available in the game*/
	int brickCards = 19;
	/**The number of sheep cards currently available in the game*/
	int sheepCards = 19;
	/**The number of wheat cards currently available in the game*/
	int wheatCards = 19;
	/**The number of ore cards currently available in the game*/
	int oreCards = 19;

	/**
	 * The Bank keeps track of how many resource cards are without owners.
	 */
	public Bank() {}

}
