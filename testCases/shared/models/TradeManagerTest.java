package shared.models;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.TradeManager;

public class TradeManagerTest {
	GamePlayers players = new GamePlayers();
	TradeManager tm = new TradeManager(players);

	@Test
	public void testTrade() {
		try {
			players.addPlayer(1, "bob", CatanColor.BLUE);
			players.addPlayer(2, "bob", CatanColor.BLUE);
		} catch (Exception e) {
			fail("could not add player");
		}
		players.getPlayerByIndex(0).startTurn();
		players.getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 2);
		players.getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 2);
		players.getPlayerByIndex(1).addResourceToHand(ResourceType.ORE, 2);
		players.getPlayerByIndex(1).addResourceToHand(ResourceType.WHEAT, 2);
		
		HashMap<ResourceType, Integer> out = new HashMap<ResourceType, Integer>();
		HashMap<ResourceType, Integer> in = new HashMap<ResourceType, Integer>();
		
		out.put(ResourceType.BRICK, 1);
		out.put(ResourceType.WOOD, 2);
		in.put(ResourceType.ORE, 1);
		in.put(ResourceType.WHEAT, 1);		
		
		assertTrue(tm.canTrade(0, 1, out, in));
		try {
			tm.ExecuteTrade(0, 1, out, in);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to trade");
		}
		
		//verify that the players have the right number of resources
		assertTrue(players.getPlayerByIndex(0).getNumOfResource(ResourceType.BRICK) == 1);
		assertTrue(players.getPlayerByIndex(0).getNumOfResource(ResourceType.WOOD) == 0);
		assertTrue(players.getPlayerByIndex(0).getNumOfResource(ResourceType.ORE) == 1);
		assertTrue(players.getPlayerByIndex(0).getNumOfResource(ResourceType.WHEAT) == 1);
		assertTrue(players.getPlayerByIndex(1).getNumOfResource(ResourceType.BRICK) == 1);
		assertTrue(players.getPlayerByIndex(1).getNumOfResource(ResourceType.WOOD) == 2);
		assertTrue(players.getPlayerByIndex(1).getNumOfResource(ResourceType.ORE) == 1);
		assertTrue(players.getPlayerByIndex(1).getNumOfResource(ResourceType.WHEAT) == 1);
		
		assertFalse(tm.canTrade(0, 1, out, in));
	}

}
