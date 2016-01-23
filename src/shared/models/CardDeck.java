package shared.models;

import shared.definitions.DevCardType;

public class CardDeck {
	
	/**The number of soldier cards left in the deck*/
	int soldierCards = 14;
	/**The number of year of plenty cards left in the deck*/
	int yearOfPlentyCards = 2;
	/**The number of monopoly cards left in the deck*/
	int monopolyCards = 2;
	/**The number of road builder cards left in the deck*/
	int roadBuilderCards = 2;
	/**The number of monument cards left in the deck*/
	int monumentCards = 5;

	public CardDeck(String json) {}

	/** Randomly select a card from the deck to give to a player
	 * 
	 * @return the type of the card drawn
	 */
	public DevCardType drawCard() {
		return null;
	}
}
