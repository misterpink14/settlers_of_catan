package shared.models.cardClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.definitions.DevCardType;

public class DevCardsTest {
	
	DevCards devCards = new DevCards(14, 2, 2, 2, 5);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ConstructorTest() {
		assertTrue(devCards.getSoldierCards() == 14);
		assertTrue(devCards.getMonopolyCards() == 2);
		assertTrue(devCards.getRoadBuilderCards() == 2);
		assertTrue(devCards.getYearOfPlentyCards() == 2);
		assertTrue(devCards.getMonumentCards() == 5);
	}
	
	@Test
	public void addCardTests() {
		devCards.addCard(DevCardType.SOLDIER);
		assertTrue(devCards.getSoldierCards() == 15);
	}
	
	@Test
	public void removeCardTests() {
		devCards.removeCard(DevCardType.SOLDIER);
		assertTrue(devCards.getSoldierCards() == 13);
	}

}
