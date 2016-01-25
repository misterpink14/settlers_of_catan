package shared.models.cardClasses;

import shared.definitions.DevCardType;

public class CardDeck {
	
	/**The container that holds the development cards in the deck*/
	DevCards devCards = new DevCards(14,2,2,2,5);

	public CardDeck(String json) {}

	/** Randomly select a card from the deck to give to a player
	 * 
	 * @return the type of the card drawn
	 */
	public DevCardType drawCard() {
		return null;
	}
}
