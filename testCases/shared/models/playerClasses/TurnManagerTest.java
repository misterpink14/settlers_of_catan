package shared.models.playerClasses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.models.Game;

public class TurnManagerTest {
	
	Game game = new Game();

	
	@Test
	public void testCanBuyDevCard() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
		assertTrue(game.getTurnManager().isTurn(0));
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WHEAT, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.SHEEP, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.ORE, 5);
		assertTrue(game.getTurnManager().CanBuyDevCard(0)); // Check if the current player can buy a development card
		
		player = new Player(234, "sal", CatanColor.RED, 1);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		assertFalse(game.getTurnManager().CanBuyDevCard(1)); // Other player's shouldn't be able to buy a dev card
	}
	
	
	@Test
	public void testCanBuyRoad() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 5);
		assertTrue(game.getTurnManager().getPlayers().getPlayerByIndex(0).canBuildRoad());
	}
	

	@Test
	public void testCanBuySettlement() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 5);
		assertFalse(game.getTurnManager().getPlayers().getPlayerByIndex(0).canBuildSettlement());
		
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WHEAT, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.SHEEP, 5);
		assertTrue(game.getTurnManager().getPlayers().getPlayerByIndex(0).canBuildSettlement());
	}
	
	
	@Test
	public void testCanBuyCity() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 5);
		assertFalse(game.getTurnManager().getPlayers().getPlayerByIndex(0).canBuildCity());

		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.ORE, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WHEAT, 5);
		assertTrue(game.getTurnManager().getPlayers().getPlayerByIndex(0).canBuildCity());
	}
	
	
	@Test
	public void testCanRollNumber() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
		assertTrue(game.getTurnManager().getPlayers().getPlayerByIndex(0).canRollDice());
		game.rollDice(0);
	}
	
	
	@Test
	public void testIsTurn() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
		assertTrue(game.getTurnManager().isTurn(0));
	}
	
	
	@Test
	public void testCanFinishTurn() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
	}
	
	
	@Test
	public void testCanSendChat() {
		Player player = new Player(123, "bob", CatanColor.BLUE, 0);
		try {
			game.getPlayers().addPlayer(player);
		} catch (Exception e) {
			fail("could not add player.");
		}
		game.getTurnManager().setCurrentTurn(0);
		assertTrue(game.getTurnManager().CanSendChat());
	}
}
