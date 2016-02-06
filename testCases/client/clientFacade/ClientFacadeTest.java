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
		facade.game.getTurnManager().startGame();
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
		assertTrue(facade.canBuildCity(0, 0, "NE", 1));
	}

	@Test
	public void testBuildCity() {
		assertTrue(!facade.buildCity(0, 0, "NE", 1).equals("False"));
	}

	@Test
	public void testCanBuildSettlement() {
		assertTrue(facade.canBuildSettlement(0, 0, "NE", 1));
	}

	@Test
	public void testBuildSettlement() {
		assertTrue(!facade.buildSettlement(0, 0, "NE", 1).equals("False"));
	}

	@Test
	public void testCanBuyDevCard() {
		assertTrue(facade.canBuyDevCard());
	}

	@Test
	public void testBuyDevCard() {
		assertTrue(!facade.buyDevCard().equals("False"));
	}

	@Test
	public void testOfferTrade() {
		assertTrue(!facade.offerTrade().equals("False"));
	}

	@Test
	public void testTradeHarbor() {
		assertTrue(!facade.tradeHarbor().equals("False"));
	}

	@Test
	public void testFinishTurn() {
		assertTrue(facade.finishTurn().equals("False"));
	}

	@Test
	public void testSendChat() {
		assertTrue(facade.sendChat().equals("False"));
	}

}
