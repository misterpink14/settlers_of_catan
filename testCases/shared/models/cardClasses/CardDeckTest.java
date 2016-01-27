package shared.models.cardClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.DevCardType;

public class CardDeckTest {
	
	CardDeck deck = new CardDeck();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void drawCardTest() {
		DevCardType card = deck.drawCard();
		switch (card) {
		case MONOPOLY:
			assertTrue(deck.devCards.monopolyCards == 1);
			break;
		case MONUMENT:
			assertTrue(deck.devCards.monumentCards == 4);
			break;
		case ROAD_BUILD:
			assertTrue(deck.devCards.roadBuilderCards == 1);
			break;
		case SOLDIER:
			assertTrue(deck.devCards.soldierCards == 13);
			break;
		case YEAR_OF_PLENTY:
			assertTrue(deck.devCards.yearOfPlentyCards == 1);
			break;
		}
	}

}
