package shared.models.playerClasses;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.models.Game;
import shared.models.cardClasses.InsufficientCardNumberException;

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
		assertFalse(game.getTurnManager().CanBuyDevCard());
		game.getTurnManager().setState(TurnManager.TurnState.NORMAL);
		assertTrue(game.getTurnManager().CanBuyDevCard());
		try {
			game.getTurnManager().buyDevCard();
		} catch (InsufficientCardNumberException e) {
			fail("Could not buy dev Card");
		}
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
		game.getTurnManager().setState(TurnManager.TurnState.NORMAL);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 5);
		//assertTrue(game.getTurnManager().CanBuildRoad(x, y, direction, ownerId);
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
		game.getTurnManager().setState(TurnManager.TurnState.NORMAL);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WHEAT, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.SHEEP, 5);
		//assertTrue(game.getTurnManager().CanBuildSettlement(x, y, direction, ownerId);
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
		game.getTurnManager().setState(TurnManager.TurnState.NORMAL);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 5);
		//assertTrue(game.getTurnManager().CanBuildCity(x, y, direction, ownerId);
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
		assertTrue(game.getTurnManager().CanRollNumber());
		game.rollDice(0);
	}
	
	@Test
	public void testCanPlaceRobber() {
		
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
		assertFalse(game.getTurnManager().CanFinishTurn());
		game.getTurnManager().setState(TurnManager.TurnState.NORMAL);
		assertTrue(game.getTurnManager().CanFinishTurn());
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
		game.getTurnManager().setState(TurnManager.TurnState.NORMAL);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.WOOD, 5);
		game.getPlayers().getPlayerByIndex(0).addResourceToHand(ResourceType.BRICK, 5);
		assertTrue(game.getTurnManager().CanSendChat());
	}

}
