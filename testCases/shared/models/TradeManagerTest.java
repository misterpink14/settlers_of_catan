package shared.models;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.playerClasses.GamePlayers;

public class TradeManagerTest {
	GamePlayers players = new GamePlayers();
	TradeManager tm = new TradeManager(players);

	@Test
	public void testTrade() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
		} catch (Exception e) {
			fail("could not add player");
		}
		players.startTurn(1);
		players.addResourceToHand(1, ResourceType.BRICK, 2);
		players.addResourceToHand(1, ResourceType.WOOD, 2);
		players.addResourceToHand(2, ResourceType.ORE, 2);
		players.addResourceToHand(2, ResourceType.WHEAT, 2);
		
		Map<ResourceType, Integer> out = new HashMap<ResourceType, Integer>();
		Map<ResourceType, Integer> in = new HashMap<ResourceType, Integer>();
		
		out.put(ResourceType.BRICK, 1);
		out.put(ResourceType.WOOD, 2);
		in.put(ResourceType.ORE, 1);
		in.put(ResourceType.WHEAT, 1);		
		
		assertTrue(tm.canTrade(1, 2, out, in));
		try {
			tm.ExecuteTrade(1, 2, out, in);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to trade");
		}
		
		//verify that the players have the right number of resources
		assertTrue(players.getNumOfResource(1, ResourceType.BRICK) == 1);
		assertTrue(players.getNumOfResource(1, ResourceType.WOOD) == 0);
		assertTrue(players.getNumOfResource(1, ResourceType.ORE) == 1);
		assertTrue(players.getNumOfResource(1, ResourceType.WHEAT) == 1);
		assertTrue(players.getNumOfResource(2, ResourceType.BRICK) == 1);
		assertTrue(players.getNumOfResource(2, ResourceType.WOOD) == 2);
		assertTrue(players.getNumOfResource(2, ResourceType.ORE) == 1);
		assertTrue(players.getNumOfResource(2, ResourceType.WHEAT) == 1);
		
		assertFalse(tm.canTrade(1, 2, out, in));
	}

}
