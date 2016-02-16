package client.clientFacade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import client.serverProxy.FakeProxy;
import shared.communication.proxy.Credentials;
import shared.models.Game;
import shared.serializerJSON.Deserializer;

public class ClientFacadeTest {
	
	ClientFacade facade;
	
	@Before
	public void setUp() {
		String gameModelString = "{   \"deck\": {     \"yearOfPlenty\": 2,     \"monopoly\": 2,     \"soldier\": 14,     \"roadBuilding\": 2,     \"monument\": 5   },   \"map\": {     \"hexes\": [       {         \"location\": {           \"x\": 0,           \"y\": -2         }       },       {         \"resource\": \"brick\",         \"location\": {           \"x\": 1,           \"y\": -2         },         \"number\": 4       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": 2,           \"y\": -2         },         \"number\": 11       },       {         \"resource\": \"brick\",         \"location\": {           \"x\": -1,           \"y\": -1         },         \"number\": 8       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": 0,           \"y\": -1         },         \"number\": 3       },       {         \"resource\": \"ore\",         \"location\": {           \"x\": 1,           \"y\": -1         },         \"number\": 9       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": 2,           \"y\": -1         },         \"number\": 12       },       {         \"resource\": \"ore\",         \"location\": {           \"x\": -2,           \"y\": 0         },         \"number\": 5       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": -1,           \"y\": 0         },         \"number\": 10       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": 0,           \"y\": 0         },         \"number\": 11       },       {         \"resource\": \"brick\",         \"location\": {           \"x\": 1,           \"y\": 0         },         \"number\": 5       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": 2,           \"y\": 0         },         \"number\": 6       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": -2,           \"y\": 1         },         \"number\": 2       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": -1,           \"y\": 1         },         \"number\": 9       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": 0,           \"y\": 1         },         \"number\": 4       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": 1,           \"y\": 1         },         \"number\": 10       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": -2,           \"y\": 2         },         \"number\": 6       },       {         \"resource\": \"ore\",         \"location\": {           \"x\": -1,           \"y\": 2         },         \"number\": 3       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": 0,           \"y\": 2         },         \"number\": 8       }     ],     \"roads\": [       {         \"owner\": 1,         \"location\": {           \"direction\": \"S\",           \"x\": -1,           \"y\": -1         }       },       {         \"owner\": 3,         \"location\": {           \"direction\": \"SW\",           \"x\": -1,           \"y\": 1         }       },       {         \"owner\": 3,         \"location\": {           \"direction\": \"SW\",           \"x\": 2,           \"y\": -2         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"S\",           \"x\": 1,           \"y\": -1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"S\",           \"x\": 0,           \"y\": 1         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"S\",           \"x\": 0,           \"y\": 0         }       },       {         \"owner\": 1,         \"location\": {           \"direction\": \"SW\",           \"x\": -2,           \"y\": 1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"SW\",           \"x\": 2,           \"y\": 0         }       }     ],     \"cities\": [],     \"settlements\": [       {         \"owner\": 3,         \"location\": {           \"direction\": \"SW\",           \"x\": -1,           \"y\": 1         }       },       {         \"owner\": 3,         \"location\": {           \"direction\": \"SE\",           \"x\": 1,           \"y\": -2         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"SW\",           \"x\": 0,           \"y\": 0         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"SW\",           \"x\": 1,           \"y\": -1         }       },       {         \"owner\": 1,         \"location\": {           \"direction\": \"SW\",           \"x\": -2,           \"y\": 1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"SE\",           \"x\": 0,           \"y\": 1         }       },       {         \"owner\": 1,         \"location\": {           \"direction\": \"SW\",           \"x\": -1,           \"y\": -1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"SW\",           \"x\": 2,           \"y\": 0         }       }     ],     \"radius\": 3,     \"ports\": [       {         \"ratio\": 2,         \"resource\": \"ore\",         \"direction\": \"S\",         \"location\": {           \"x\": 1,           \"y\": -3         }       },       {         \"ratio\": 3,         \"direction\": \"SW\",         \"location\": {           \"x\": 3,           \"y\": -3         }       },       {         \"ratio\": 3,         \"direction\": \"NW\",         \"location\": {           \"x\": 2,           \"y\": 1         }       },       {         \"ratio\": 2,         \"resource\": \"brick\",         \"direction\": \"NE\",         \"location\": {           \"x\": -2,           \"y\": 3         }       },       {         \"ratio\": 2,         \"resource\": \"wheat\",         \"direction\": \"S\",         \"location\": {           \"x\": -1,           \"y\": -2         }       },       {         \"ratio\": 2,         \"resource\": \"wood\",         \"direction\": \"NE\",         \"location\": {           \"x\": -3,           \"y\": 2         }       },       {         \"ratio\": 3,         \"direction\": \"SE\",         \"location\": {           \"x\": -3,           \"y\": 0         }       },       {         \"ratio\": 2,         \"resource\": \"sheep\",         \"direction\": \"NW\",         \"location\": {           \"x\": 3,           \"y\": -1         }       },       {         \"ratio\": 3,         \"direction\": \"N\",         \"location\": {           \"x\": 0,           \"y\": 3         }       }     ],     \"robber\": {       \"x\": 0,       \"y\": -2     }   },   \"players\": [     {       \"resources\": {         \"brick\": 0,         \"wood\": 1,         \"sheep\": 1,         \"wheat\": 1,         \"ore\": 0       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 0,       \"playerIndex\": 0,       \"name\": \"Sam\",       \"color\": \"red\"     },     {       \"resources\": {         \"brick\": 1,         \"wood\": 0,         \"sheep\": 1,         \"wheat\": 0,         \"ore\": 1       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 1,       \"playerIndex\": 1,       \"name\": \"Brooke\",       \"color\": \"blue\"     },     {       \"resources\": {         \"brick\": 0,         \"wood\": 1,         \"sheep\": 1,         \"wheat\": 1,         \"ore\": 0       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 10,       \"playerIndex\": 2,       \"name\": \"Pete\",       \"color\": \"red\"     },     {       \"resources\": {         \"brick\": 0,         \"wood\": 1,         \"sheep\": 1,         \"wheat\": 0,         \"ore\": 1       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 11,       \"playerIndex\": 3,       \"name\": \"Mark\",       \"color\": \"green\"     }   ],   \"log\": {     \"lines\": [       {         \"source\": \"Sam\",         \"message\": \"Sam built a road\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a settlement\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam's turn just ended\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a road\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a settlement\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke's turn just ended\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a road\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a settlement\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete's turn just ended\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a road\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a settlement\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark's turn just ended\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a road\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a settlement\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark's turn just ended\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a road\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a settlement\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete's turn just ended\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a road\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a settlement\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke's turn just ended\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a road\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a settlement\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam's turn just ended\"       }     ]   },   \"chat\": {     \"lines\": []   },   \"bank\": {     \"brick\": 23,     \"wood\": 21,     \"sheep\": 20,     \"wheat\": 22,     \"ore\": 22   },   \"turnTracker\": {     \"status\": \"Rolling\",     \"currentTurn\": 0,     \"longestRoad\": -1,     \"largestArmy\": -1   },   \"winner\": -1,   \"version\": 0}";
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(gameModelString).getAsJsonObject();
		Deserializer deserializer = new Deserializer();
		Game game = new Game();
		deserializer.deserialize(game, json);
		facade = new ClientFacade(game, new FakeProxy()); 
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
		assertTrue(!facade.canBuildRoad(0, 0, "NE", 1));
	}

	@Test
	public void testBuildRoad() {
		assertTrue(facade.buildRoad(0, 0, "NE", 1).equals("False"));
	}

	@Test
	public void testCanBuildCity() {
		assertTrue(!facade.canBuildCity(0, 0, "NE", 1));
	}

	@Test
	public void testBuildCity() {
		assertTrue(facade.buildCity(0, 0, "NE", 1).equals("False"));
	}

	@Test
	public void testCanBuildSettlement() {
		assertTrue(!facade.canBuildSettlement(0, 0, "NE", 1));
	}

	@Test
	public void testBuildSettlement() {
		assertTrue(facade.buildSettlement(0, 0, "NE", 1).equals("False"));
	}

	@Test
	public void testCanBuyDevCard() {
		assertTrue(!facade.canBuyDevCard());
	}

	@Test
	public void testBuyDevCard() {
		assertTrue(facade.buyDevCard().equals("False"));
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
		assertTrue(facade.sendChat().equals("True"));
	}

}
