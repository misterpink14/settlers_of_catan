package client.clientFacade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.serverProxy.FakeProxy;
import shared.communication.proxy.Credentials;
import shared.models.Game;

public class ClientFacadeTest {
	
	ClientFacade facade;
	
	@Before
	public void setUp() {
		facade = new ClientFacade(new Game(), new FakeProxy()); 
	}

	@Test
	public void testCreatePlayer() {
		assertTrue(!facade.createPlayer().equals("False"));
	}

	@Test
	public void testLogin() {
		assertTrue(!facade.login(new Credentials()).equals("False"));
	}

	@Test
	public void testRollDice() {
		assertTrue(!facade.rollDice().equals("False"));
	}

	@Test
	public void testCanBuildRoad() {
		assertTrue(facade.canBuildRoad(0, 0, "NE", 1));
	}

	@Test
	public void testBuildRoad() {
		assertTrue(!facade.buildRoad(0, 0, "NE", 1).equals("False"));
	}

	@Test
	public void testCanBuildCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanBuildSettlement() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildSettlement() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanBuyDevCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuyDevCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testOfferTrade() {
		fail("Not yet implemented");
	}

	@Test
	public void testTradeFour() {
		fail("Not yet implemented");
	}

	@Test
	public void testTradeHarbor() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayDevCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinishTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendChat() {
		fail("Not yet implemented");
	}

}
