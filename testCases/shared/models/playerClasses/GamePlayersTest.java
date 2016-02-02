package shared.models.playerClasses;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;

public class GamePlayersTest {

	GamePlayers players = new GamePlayers();

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
	public void testIsTurn() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		players.startTurn(3);
		assertTrue(players.isTurn(3));
		assertFalse(players.isTurn(1));
		assertFalse(players.isTurn(2));
		assertFalse(players.isTurn(4));
		
		players.finishTurn(3);
		players.startTurn(4);
		assertTrue(players.isTurn(4));
		assertFalse(players.isTurn(1));
		assertFalse(players.isTurn(2));
		assertFalse(players.isTurn(3));
	}

	@Test
	public void testCanRollDice() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		players.startTurn(3);
		assertTrue(players.canRollDice(3));
		assertFalse(players.canRollDice(1));
	}

	@Test
	public void testBuildRoad() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		//starting the turn and having the proper number of resources are both needed for building roads.
		players.startTurn(1);
		players.getPlayer(1).addResourceToHand(ResourceType.WOOD, 1);
		assertFalse(players.canBuildRoad(1));
		players.getPlayer(1).addResourceToHand(ResourceType.BRICK, 1);
		assertTrue(players.canBuildRoad(1));
		
		//if it's not your turn, you cannot build a road.
		players.getPlayer(2).addResourceToHand(ResourceType.WOOD, 1);
		players.getPlayer(2).addResourceToHand(ResourceType.BRICK, 1);
		assertFalse(players.canBuildRoad(2));
		
		try {
			players.getPlayer(1).buyRoad();
		} catch (InsufficientCardNumberException e) {
			fail("Build Road Failed.");
		}
		try {
			players.getPlayer(1).buyRoad();
			fail("Did not Fail to build road");
		} catch (InsufficientCardNumberException e) {}
		
	}
	
	@Test
	public void testBuildSettlement() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		//starting the turn and having the proper number of resources are both needed for building roads.
		players.startTurn(1);
		players.getPlayer(1).addResourceToHand(ResourceType.WOOD, 1);
		assertFalse(players.canBuildSettlement(1));
		players.getPlayer(1).addResourceToHand(ResourceType.BRICK, 1);
		players.getPlayer(1).addResourceToHand(ResourceType.WHEAT, 1);
		players.getPlayer(1).addResourceToHand(ResourceType.SHEEP, 1);
		assertTrue(players.canBuildSettlement(1));
		
		//if it's not your turn, you cannot build a road.
		players.getPlayer(2).addResourceToHand(ResourceType.WOOD, 1);
		players.getPlayer(2).addResourceToHand(ResourceType.BRICK, 1);
		players.getPlayer(2).addResourceToHand(ResourceType.WHEAT, 1);
		players.getPlayer(2).addResourceToHand(ResourceType.SHEEP, 1);
		assertFalse(players.canBuildSettlement(2));
		
		try {
			players.getPlayer(1).buySettlement();
		} catch (InsufficientCardNumberException e) {
			fail("Build Settlement Failed.");
		}
		try {
			players.getPlayer(1).buySettlement();
			fail("Did not Fail to build settlement");
		} catch (InsufficientCardNumberException e) {}
	}

	@Test
	public void testBuildCity() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		//starting the turn and having the proper number of resources are both needed for building roads.
		players.startTurn(1);
		players.getPlayer(1).addResourceToHand(ResourceType.ORE, 3);
		assertFalse(players.canBuildCity(1));
		players.getPlayer(1).addResourceToHand(ResourceType.WHEAT, 2);
		assertTrue(players.canBuildCity(1));
		
		//if it's not your turn, you cannot build a road.
		players.getPlayer(2).addResourceToHand(ResourceType.ORE, 3);
		players.getPlayer(2).addResourceToHand(ResourceType.WHEAT, 2);
		assertFalse(players.canBuildCity(2));
		
		try {
			players.getPlayer(1).buyCity();
		} catch (InsufficientCardNumberException e) {
			fail("Build city Failed.");
		}
		try {
			players.getPlayer(1).buyCity();
			fail("Did not Fail to build city");
		} catch (InsufficientCardNumberException e) {}
	}

	@Test
	public void testBuyDevCard() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		//starting the turn and having the proper number of resources are both needed for building roads.
		players.startTurn(1);
		players.getPlayer(1).addResourceToHand(ResourceType.ORE, 1);
		assertFalse(players.canBuyDevCard(1));
		players.getPlayer(1).addResourceToHand(ResourceType.WHEAT, 1);
		players.getPlayer(1).addResourceToHand(ResourceType.SHEEP, 1);
		assertTrue(players.canBuyDevCard(1));
		
		//if it's not your turn, you cannot build a road.
		players.getPlayer(2).addResourceToHand(ResourceType.ORE, 1);
		players.getPlayer(2).addResourceToHand(ResourceType.WHEAT, 1);
		players.getPlayer(2).addResourceToHand(ResourceType.SHEEP, 1);
		assertFalse(players.canBuyDevCard(2));
		
		try {
			players.getPlayer(1).buyDevCard();
		} catch (InsufficientCardNumberException e) {
			fail("Buy Dev Card Failed.");
		}
		try {
			players.getPlayer(1).buyDevCard();
			fail("Did not Fail to buy Dev Card");
		} catch (InsufficientCardNumberException e) {}
	}

	@Test
	public void testTradeFour() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		
		players.getPlayer(1).addResourceToHand(ResourceType.SHEEP, 4);
		try {
			players.getPlayer(1).tradeFour(ResourceType.SHEEP, ResourceType.ORE);
		} catch (InsufficientCardNumberException e) {
			fail("Could not trade four resources");
		}
	}

	@Test
	public void testTradeTwoWithPort() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		
		players.getPlayer(1).addResourceToHand(ResourceType.SHEEP, 2);
		try {
			players.getPlayer(1).tradeTwoWithPort(ResourceType.SHEEP, ResourceType.ORE);
		} catch (InsufficientCardNumberException e) {
			fail("Could not trade two resources");
		}
	}

	@Test
	public void testTradeThreeWithPort() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		
		players.getPlayer(1).addResourceToHand(ResourceType.SHEEP, 3);
		try {
			players.getPlayer(1).tradeThreeWithPort(ResourceType.SHEEP, ResourceType.ORE);
		} catch (InsufficientCardNumberException e) {
			fail("Could not trade three resources");
		}
	}

	@Test
	public void testPlayDevCard() {
		try {
			players.addPlayer(1);
			players.addPlayer(2);
			players.addPlayer(3);
			players.addPlayer(4);
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
		
		players.getPlayer(1).drawDevCard(DevCardType.MONOPOLY);
		try {
			players.getPlayer(1).playDevCard(DevCardType.MONOPOLY);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to play a dev card");
		}
	}
}
