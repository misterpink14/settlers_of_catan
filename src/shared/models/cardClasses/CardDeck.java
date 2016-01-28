package shared.models.cardClasses;

import shared.definitions.DevCardType;

public class CardDeck {
	
	/**This holds the number of each type of card left in the deck */
	DevCards devCards = new DevCards(14,2,2,2,5);
	/**This holds representations of cards that can be shuffled randomly, then drawn one at a time.*/
	DevCardType[] deck = new DevCardType[25];
	
	private int cardsInDeck = 25;

	/**The container that holds the development cards in the deck*/
	public CardDeck() {
		//adds the right number of cards to the deck representation.
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

	/** Gives the next card in the deck. The devCard object will subtract one from the type drawn.
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
     * Shuffles the deck. This will only happen in the constructor. There is no other time the deck needs to be shuffled.
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
