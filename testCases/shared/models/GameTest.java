package shared.models;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;

public class GameTest {
	Game game = new Game("");

	@Test
	public void testImportGame() {
		
	}

	@Test
	public void testRollDice() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		game.rollDice(1);
	}

	@Test
	public void testBuildRoad() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		game.players.addResourceToHand(1, ResourceType.BRICK, 1);
		game.players.addResourceToHand(1, ResourceType.WOOD, 1);
		try {
			game.buildRoad(1);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to build a road");
		}
	}

	@Test
	public void testBuildSettlement() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		game.players.addResourceToHand(1, ResourceType.BRICK, 1);
		game.players.addResourceToHand(1, ResourceType.WOOD, 1);
		game.players.addResourceToHand(1, ResourceType.SHEEP, 1);
		game.players.addResourceToHand(1, ResourceType.WHEAT, 1);
		try {
			game.buildSettlement(1);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to build a Settlement");
		}
	}

	@Test
	public void testBuildCity() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		game.players.addResourceToHand(1, ResourceType.ORE, 3);
		game.players.addResourceToHand(1, ResourceType.WHEAT, 2);
		try {
			game.buildCity(1);
		} catch (InsufficientCardNumberException e1) {
			fail("Failed to build a city");
		}
	}

	@Test
	public void testBuyDevelopmentCard() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		game.players.addResourceToHand(1, ResourceType.ORE, 1);
		game.players.addResourceToHand(1, ResourceType.SHEEP, 1);
		game.players.addResourceToHand(1, ResourceType.WHEAT, 1);
		try {
			game.buyDevelopmentCard(1);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to buy a development card");
		}
	}

	@Test
	public void testPlayDevCard() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		game.players.drawDevCard(1, DevCardType.MONOPOLY);
		try {
			game.playDevCard(1, DevCardType.MONOPOLY);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to play a dev card");
		}
	}

	@Test
	public void testTradeResourcesWithBank() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		game.players.addResourceToHand(1, ResourceType.BRICK, 4);
		try {
			game.tradeResourcesWithBank(1, 4, ResourceType.BRICK, ResourceType.ORE);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to trade four resources");
		}
		game.players.addResourceToHand(1, ResourceType.BRICK, 3);
		try {
			game.tradeResourcesWithBank(1, 3, ResourceType.BRICK, ResourceType.ORE);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to trade three resources");
		}
		game.players.addResourceToHand(1, ResourceType.BRICK, 2);
		try {
			game.tradeResourcesWithBank(1, 2, ResourceType.BRICK, ResourceType.ORE);
		} catch (InsufficientCardNumberException e) {
			fail("Failed to trade two resources");
		}
		assertTrue(game.players.getPlayer(1).getNumOfResource(ResourceType.BRICK) == 0);
	}

	@Test
	public void testOfferATrade() {
		
	}

	@Test
	public void testIsTurn() {
		try {
			game.players.addPlayer(1);
		} catch (Exception e) {
			fail("Could not add player");
		}
		game.players.startTurn(1);
		assertTrue(game.isTurn(1));
		game.players.finishTurn(1);
		assertFalse(game.isTurn(1));
	}

	@Test
	public void testVersionID() {
	}

}
