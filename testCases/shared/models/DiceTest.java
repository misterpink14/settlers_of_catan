package shared.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		int dieRoll = Dice.rollDice();
		assertTrue(dieRoll < 7 && dieRoll > 0);
	}
}
