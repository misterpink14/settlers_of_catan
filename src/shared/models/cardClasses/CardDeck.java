package shared.models.cardClasses;

import shared.definitions.DevCardType;

public class CardDeck {
	
	/**The container that holds the development cards in the deck*/
	DevCards devCards = new DevCards(14,2,2,2,5);
	
	DevCardType[] deck = new DevCardType[25];
	
	private int cardsInDeck = 25;

	public CardDeck() {
		int i = 0;
		while(i < 14) {
			deck[i] = DevCardType.SOLDIER;
			i++;
		}
		while(i < 19) {
			deck[i] = DevCardType.MONUMENT;
			i++;
		}
		while(i < 21) {
			deck[i] = DevCardType.ROAD_BUILD;
			i++;
		}
		while(i < 23) {
			deck[i] = DevCardType.MONOPOLY;
			i++;
		}
		while(i < 25) {
			deck[i] = DevCardType.YEAR_OF_PLENTY;
			i++;
		}
		shuffle();
	}

	/** Gives the next card in the deck.
	 * 
	 * @return the type of the card drawn
	 * @throws InsufficientCardNumberException 
	 */
	public DevCardType drawCard() throws InsufficientCardNumberException {
		if(cardsInDeck == 0) {
			return null;
		}
		devCards.removeCard(deck[--cardsInDeck]);
		return deck[cardsInDeck];
	}
	
	 /**
     * Put all the used cards back into the deck (if any), and
     * shuffle the deck into a random order.
     */
    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            DevCardType temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
    }
}
