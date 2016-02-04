package shared.models.playerClasses;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;

public class PlayerTest {
	
	Player player = new Player(0, "Bob", CatanColor.BLUE);

	@Test
	public void testPlayer() {
	}

	@Test
	public void testStartTurn() {
		player.startTurn();
		assertTrue(player.isTurn());
	}

	@Test
	public void testAddRemoveResourceFromHand() {
		player.addResourceToHand(ResourceType.BRICK, 5);

		assertTrue(player.getNumOfResource(ResourceType.BRICK) == 5);
		try {
			player.removeResourceFromHand(ResourceType.BRICK, 4);
			assertTrue(player.getNumOfResource(ResourceType.BRICK) == 1);
			player.removeResourceFromHand(ResourceType.BRICK, 1);
			assertTrue(player.getNumOfResource(ResourceType.BRICK) == 0);			
		} catch (InsufficientCardNumberException e) {
			fail();
		}
		boolean failed = false;
		try {
			player.removeResourceFromHand(ResourceType.BRICK, 1);
		} catch (InsufficientCardNumberException e) {
			failed = true;
		}
		assertTrue(failed);
	}

	@Test
	public void testCanRollDice() {
		player.startTurn();
		assertTrue(player.canRollDice());
		player.finishTurn();
		assertFalse(player.canRollDice());
	}

	@Test
	public void testCanBuildRoad() {
		player.startTurn();
		assertFalse(player.canBuildRoad());
		player.addResourceToHand(ResourceType.WOOD, 1);
		player.addResourceToHand(ResourceType.BRICK, 1);
		assertTrue(player.canBuildRoad());
	}

	@Test
	public void testCanBuildSettlement() {
		player.startTurn();
		assertFalse(player.canBuildSettlement());
		player.addResourceToHand(ResourceType.WOOD, 1);
		player.addResourceToHand(ResourceType.BRICK, 1);
		player.addResourceToHand(ResourceType.WHEAT, 1);
		player.addResourceToHand(ResourceType.SHEEP, 1);
		assertTrue(player.canBuildSettlement());
	}

	@Test
	public void testCanBuildCity() {
		player.startTurn();
		assertFalse(player.canBuildCity());
		player.addResourceToHand(ResourceType.WHEAT, 2);
		player.addResourceToHand(ResourceType.ORE, 3);
		assertTrue(player.canBuildCity());
	}

	@Test
	public void testCanBuyDevCard() {
		player.startTurn();
		assertFalse(player.canBuyDevCard());
		player.addResourceToHand(ResourceType.ORE, 1);
		player.addResourceToHand(ResourceType.WHEAT, 1);
		player.addResourceToHand(ResourceType.SHEEP, 1);
		assertTrue(player.canBuyDevCard());
	}

	@Test
	public void testBuyRoad() {
		//add resources to buy one road.
		player.addResourceToHand(ResourceType.WOOD, 1);
		player.addResourceToHand(ResourceType.BRICK, 2);
		try {
			//buy the road
			player.buyRoad();
			//check that the right resources and numbers were used in the method
			assertTrue(player.getNumOfResource(ResourceType.WOOD) == 0);
			assertTrue(player.getNumOfResource(ResourceType.BRICK) == 1);
		} catch (InsufficientCardNumberException e) {
			fail();
			e.printStackTrace();
		}
		//check that buy road fails if there are not enough resources.
		boolean failed = false;
		try {
			player.buyRoad();
		} catch (InsufficientCardNumberException e) {
			failed = true;
			e.printStackTrace();
		}
		assertTrue(failed);
	}

	@Test
	public void testBuySettlement() {
		player.addResourceToHand(ResourceType.BRICK, 1);
		player.addResourceToHand(ResourceType.WOOD, 2);
		player.addResourceToHand(ResourceType.SHEEP, 2);
		player.addResourceToHand(ResourceType.WHEAT, 2);
		try {
			player.buySettlement();
			assertTrue(player.getNumOfResource(ResourceType.BRICK) == 0);
			assertTrue(player.getNumOfResource(ResourceType.WOOD) == 1);
			assertTrue(player.getNumOfResource(ResourceType.SHEEP) == 1);
			assertTrue(player.getNumOfResource(ResourceType.WHEAT) == 1);
		} catch (InsufficientCardNumberException e) {
			fail();
			e.printStackTrace();
		}
		boolean failed = false;
		try {
			player.buySettlement();
		} catch (InsufficientCardNumberException e) {
			failed = true;
			e.printStackTrace();
		}
		assertTrue(failed);
	}

	@Test
	public void testBuyCity() {
		player.addResourceToHand(ResourceType.ORE, 4);
		player.addResourceToHand(ResourceType.WHEAT, 3);
		try {
			player.buyCity();
			assertTrue(player.getNumOfResource(ResourceType.ORE) == 1);
			assertTrue(player.getNumOfResource(ResourceType.WHEAT) == 1);
		} catch (InsufficientCardNumberException e) {
			fail();
			e.printStackTrace();
		}
		boolean failed = false;
		try {
			player.buyCity();
		} catch (InsufficientCardNumberException e) {
			failed = true;
			e.printStackTrace();
		}
		assertTrue(failed);
	}

	@Test
	public void testBuyDevCard() {
		player.addResourceToHand(ResourceType.ORE, 1);
		player.addResourceToHand(ResourceType.SHEEP, 2);
		player.addResourceToHand(ResourceType.WHEAT, 2);
		try {
			player.buyDevCard(DevCardType.MONOPOLY);
			assertTrue(player.getNumOfResource(ResourceType.ORE) == 0);
			assertTrue(player.getNumOfResource(ResourceType.SHEEP) == 1);
			assertTrue(player.getNumOfResource(ResourceType.WHEAT) == 1);
			assertTrue(player.getNumOfDevCard(DevCardType.MONOPOLY) == 1);
		} catch (InsufficientCardNumberException e) {
			fail();
			e.printStackTrace();
		}
		boolean failed = false;
		try {
			player.buyDevCard(DevCardType.MONOPOLY);
		} catch (InsufficientCardNumberException e) {
			failed = true;
			e.printStackTrace();
		}
		assertTrue(failed);
	}

	@Test
	public void testDrawPlayDevCard() {
		//draw one of each type.
		player.drawDevCard(DevCardType.SOLDIER);
		player.drawDevCard(DevCardType.MONUMENT);
		player.drawDevCard(DevCardType.MONOPOLY);
		player.drawDevCard(DevCardType.ROAD_BUILD);
		player.drawDevCard(DevCardType.YEAR_OF_PLENTY);
		
		assertTrue(player.getNumOfDevCard(DevCardType.SOLDIER) == 1);
		assertTrue(player.getNumOfDevCard(DevCardType.MONUMENT) == 1);
		assertTrue(player.getNumOfDevCard(DevCardType.MONOPOLY) == 1);
		assertTrue(player.getNumOfDevCard(DevCardType.ROAD_BUILD) == 1);
		assertTrue(player.getNumOfDevCard(DevCardType.YEAR_OF_PLENTY) == 1);
		
		//play one of each type
		try {
			player.playDevCard(DevCardType.SOLDIER);
			player.playDevCard(DevCardType.MONUMENT);
			player.playDevCard(DevCardType.MONOPOLY);
			player.playDevCard(DevCardType.ROAD_BUILD);
			player.playDevCard(DevCardType.YEAR_OF_PLENTY);
		} catch (InsufficientCardNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(player.getNumOfDevCard(DevCardType.SOLDIER) == 0);
		assertTrue(player.getNumOfDevCard(DevCardType.MONUMENT) == 0);
		assertTrue(player.getNumOfDevCard(DevCardType.MONOPOLY) == 0);
		assertTrue(player.getNumOfDevCard(DevCardType.ROAD_BUILD) == 0);
		assertTrue(player.getNumOfDevCard(DevCardType.YEAR_OF_PLENTY) == 0);
	}

	@Test
	public void testFinishTurn() {
		player.startTurn();
		player.finishTurn();
		assertFalse(player.isTurn());
	}

}
