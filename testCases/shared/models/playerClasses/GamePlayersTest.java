package shared.models.playerClasses;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamePlayersTest {

	GamePlayers players = new GamePlayers();
	
	@Test
	public void testGamePlayers() {}

	@Test
	public void testAddPlayers() {
		//adds four players, checking the counts along the way. We need to know that there are only four players.
		try {
			players.addPlayer(1);
			assertTrue(players.getNumberOfPlayers() == 1);
			
			players.addPlayer(2);
			assertTrue(players.getNumberOfPlayers() == 2);
			
			players.addPlayer(3);
			assertTrue(players.getNumberOfPlayers() == 3);
			
			players.addPlayer(4);
			assertTrue(players.getNumberOfPlayers() == 4);
		} catch (Exception e) {
			fail("Failed adding a legal number of players");
		}
		
		boolean failed = false;
		try {
			players.addPlayer(5);
		} catch (Exception e) {
			failed = true;
			e.printStackTrace();
		}
		assertTrue(failed);		
	}

	@Test
	public void testFinishTurn() {
		try {
			players.addPlayer(1);
			assertTrue(players.getNumberOfPlayers() == 1);
			
			players.addPlayer(2);
			assertTrue(players.getNumberOfPlayers() == 2);
			
			players.addPlayer(3);
			assertTrue(players.getNumberOfPlayers() == 3);
			
			players.addPlayer(4);
			assertTrue(players.getNumberOfPlayers() == 4);
		} catch (Exception e) {
			fail("Failed adding a legal number of players");
		}
		players.getPlayerByIndex(1).startTurn();
		players.finishTurn(1);
		assertFalse(players.getPlayerByIndex(1).isTurn());
	}

}
