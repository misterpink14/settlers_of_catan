package shared.serializerJSON;

import org.junit.Test;
import static org.junit.Assert.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import shared.models.Game;
import shared.models.cardClasses.CardDeck;
import shared.models.cardClasses.DevCards;
import shared.models.chatClasses.GameChat;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Map;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.Player;

public class DeserializerTest {
	
	Deserializer deserializer = new Deserializer();
	
	@Test
	public void deckTest() {
		String deckString = "{   \"deck\": {     \"yearOfPlenty\": 2,     \"monopoly\": 2,     \"soldier\": 14,     \"roadBuilding\": 2,     \"monument\": 5   }}";
		
		JsonParser parser = new JsonParser();
		JsonObject deckJson = parser.parse(deckString).getAsJsonObject();

		CardDeck desDeck = deserializer.deserializeDeck(deckJson);
		DevCards devCards = desDeck.getDevCards();
		assertTrue(devCards.getMonopolyCards() == 2);
		assertTrue(devCards.getYearOfPlentyCards() == 2);
		assertTrue(devCards.getRoadBuilderCards() == 2);
		assertTrue(devCards.getMonumentCards() == 5);
		assertTrue(devCards.getSoldierCards() == 14);
	}
	
	public void mapTest() {
		String mapString = "{ \"map\": {     \"hexes\": [       {         \"location\": {           \"x\": 0,           \"y\": -2         }       },       {         \"resource\": \"brick\",         \"location\": {           \"x\": 1,           \"y\": -2         },         \"number\": 4       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": 2,           \"y\": -2         },         \"number\": 11       },       {         \"resource\": \"brick\",         \"location\": {           \"x\": -1,           \"y\": -1         },         \"number\": 8       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": 0,           \"y\": -1         },         \"number\": 3       },       {         \"resource\": \"ore\",         \"location\": {           \"x\": 1,           \"y\": -1         },         \"number\": 9       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": 2,           \"y\": -1         },         \"number\": 12       },       {         \"resource\": \"ore\",         \"location\": {           \"x\": -2,           \"y\": 0         },         \"number\": 5       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": -1,           \"y\": 0         },         \"number\": 10       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": 0,           \"y\": 0         },         \"number\": 11       },       {         \"resource\": \"brick\",         \"location\": {           \"x\": 1,           \"y\": 0         },         \"number\": 5       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": 2,           \"y\": 0         },         \"number\": 6       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": -2,           \"y\": 1         },         \"number\": 2       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": -1,           \"y\": 1         },         \"number\": 9       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": 0,           \"y\": 1         },         \"number\": 4       },       {         \"resource\": \"sheep\",         \"location\": {           \"x\": 1,           \"y\": 1         },         \"number\": 10       },       {         \"resource\": \"wood\",         \"location\": {           \"x\": -2,           \"y\": 2         },         \"number\": 6       },       {         \"resource\": \"ore\",         \"location\": {           \"x\": -1,           \"y\": 2         },         \"number\": 3       },       {         \"resource\": \"wheat\",         \"location\": {           \"x\": 0,           \"y\": 2         },         \"number\": 8       }     ],     \"roads\": [       {         \"owner\": 1,         \"location\": {           \"direction\": \"S\",           \"x\": -1,           \"y\": -1         }       },       {         \"owner\": 3,         \"location\": {           \"direction\": \"SW\",           \"x\": -1,           \"y\": 1         }       },       {         \"owner\": 3,         \"location\": {           \"direction\": \"SW\",           \"x\": 2,           \"y\": -2         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"S\",           \"x\": 1,           \"y\": -1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"S\",           \"x\": 0,           \"y\": 1         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"S\",           \"x\": 0,           \"y\": 0         }       },       {         \"owner\": 1,         \"location\": {           \"direction\": \"SW\",           \"x\": -2,           \"y\": 1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"SW\",           \"x\": 2,           \"y\": 0         }       }     ],     \"cities\": [],     \"settlements\": [       {         \"owner\": 3,         \"location\": {           \"direction\": \"SW\",           \"x\": -1,           \"y\": 1         }       },       {         \"owner\": 3,         \"location\": {           \"direction\": \"SE\",           \"x\": 1,           \"y\": -2         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"SW\",           \"x\": 0,           \"y\": 0         }       },       {         \"owner\": 2,         \"location\": {           \"direction\": \"SW\",           \"x\": 1,           \"y\": -1         }       },       {         \"owner\": 1,         \"location\": {           \"direction\": \"SW\",           \"x\": -2,           \"y\": 1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"SE\",           \"x\": 0,           \"y\": 1         }       },       {         \"owner\": 1,         \"location\": {           \"direction\": \"SW\",           \"x\": -1,           \"y\": -1         }       },       {         \"owner\": 0,         \"location\": {           \"direction\": \"SW\",           \"x\": 2,           \"y\": 0         }       }     ],     \"radius\": 3,     \"ports\": [       {         \"ratio\": 2,         \"resource\": \"ore\",         \"direction\": \"S\",         \"location\": {           \"x\": 1,           \"y\": -3         }       },       {         \"ratio\": 3,         \"direction\": \"SW\",         \"location\": {           \"x\": 3,           \"y\": -3         }       },       {         \"ratio\": 3,         \"direction\": \"NW\",         \"location\": {           \"x\": 2,           \"y\": 1         }       },       {         \"ratio\": 2,         \"resource\": \"brick\",         \"direction\": \"NE\",         \"location\": {           \"x\": -2,           \"y\": 3         }       },       {         \"ratio\": 2,         \"resource\": \"wheat\",         \"direction\": \"S\",         \"location\": {           \"x\": -1,           \"y\": -2         }       },       {         \"ratio\": 2,         \"resource\": \"wood\",         \"direction\": \"NE\",         \"location\": {           \"x\": -3,           \"y\": 2         }       },       {         \"ratio\": 3,         \"direction\": \"SE\",         \"location\": {           \"x\": -3,           \"y\": 0         }       },       {         \"ratio\": 2,         \"resource\": \"sheep\",         \"direction\": \"NW\",         \"location\": {           \"x\": 3,           \"y\": -1         }       },       {         \"ratio\": 3,         \"direction\": \"N\",         \"location\": {           \"x\": 0,           \"y\": 3         }       }     ],     \"robber\": {       \"x\": 0,       \"y\": -2     }   }}";
		
		JsonParser parser = new JsonParser();
		JsonObject mapJson = parser.parse(mapString).getAsJsonObject();
		
		Map map = deserializer.deserializeMap(mapJson);
		// The robber is on (0, -2), so should be false
		assertFalse(map.canPlaceRobber(0, -2));
		// The robber is on (0, -2), so should be true
		assertTrue(map.canPlaceRobber(-2, -2));
	}
	
	public void playersTest() {
		String playersString = "{   \"players\": [     {       \"resources\": {         \"brick\": 0,         \"wood\": 1,         \"sheep\": 1,         \"wheat\": 1,         \"ore\": 0       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 0,       \"playerIndex\": 0,       \"name\": \"Sam\",       \"color\": \"red\"     },     {       \"resources\": {         \"brick\": 1,         \"wood\": 0,         \"sheep\": 1,         \"wheat\": 0,         \"ore\": 1       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 1,       \"playerIndex\": 1,       \"name\": \"Brooke\",       \"color\": \"blue\"     },     {       \"resources\": {         \"brick\": 0,         \"wood\": 1,         \"sheep\": 1,         \"wheat\": 1,         \"ore\": 0       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 10,       \"playerIndex\": 2,       \"name\": \"Pete\",       \"color\": \"red\"     },     {       \"resources\": {         \"brick\": 0,         \"wood\": 1,         \"sheep\": 1,         \"wheat\": 0,         \"ore\": 1       },       \"oldDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"newDevCards\": {         \"yearOfPlenty\": 0,         \"monopoly\": 0,         \"soldier\": 0,         \"roadBuilding\": 0,         \"monument\": 0       },       \"roads\": 13,       \"cities\": 4,       \"settlements\": 3,       \"soldiers\": 0,       \"victoryPoints\": 2,       \"monuments\": 0,       \"playedDevCard\": false,       \"discarded\": false,       \"playerID\": 11,       \"playerIndex\": 3,       \"name\": \"Mark\",       \"color\": \"green\"     }   ]}";
		
		JsonParser parser = new JsonParser();
		JsonArray playerJson = parser.parse(playersString).getAsJsonArray();
		
		GamePlayers players = deserializer.deserializePlayers(playerJson);
		Player player = players.getPlayerByIndex(0);
		assertTrue(player.getName() == "Sam");
	}
	
	public void chatTest() {
		String chatString =   "{ \"chat\": {     \"lines\": [       {         \"source\": \"Sam\",         \"message\": \"Sam built a road\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a settlement\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam's turn just ended\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a road\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a settlement\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke's turn just ended\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a road\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a settlement\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete's turn just ended\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a road\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a settlement\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark's turn just ended\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a road\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a settlement\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark's turn just ended\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a road\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a settlement\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete's turn just ended\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a road\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a settlement\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke's turn just ended\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a road\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a settlement\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam's turn just ended\"       }     ]   } }";


		JsonParser parser = new JsonParser();
		JsonObject chatJson = parser.parse(chatString).getAsJsonObject();

		GameChat desChat = deserializer.deserializeChat(chatJson);

		// There are 24 messages to be deserialized from the JSON
		assertTrue(desChat.getMessages().size() == 24);
	}
	
	public void logTest() {
		String logString =   "{ \"log\": {     \"lines\": [       {         \"source\": \"Sam\",         \"message\": \"Sam built a road\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a settlement\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam's turn just ended\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a road\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a settlement\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke's turn just ended\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a road\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a settlement\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete's turn just ended\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a road\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a settlement\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark's turn just ended\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a road\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark built a settlement\"       },       {         \"source\": \"Mark\",         \"message\": \"Mark's turn just ended\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a road\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete built a settlement\"       },       {         \"source\": \"Pete\",         \"message\": \"Pete's turn just ended\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a road\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke built a settlement\"       },       {         \"source\": \"Brooke\",         \"message\": \"Brooke's turn just ended\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a road\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam built a settlement\"       },       {         \"source\": \"Sam\",         \"message\": \"Sam's turn just ended\"       }     ]   } }";


		JsonParser parser = new JsonParser();
		JsonObject logJson = parser.parse(logString).getAsJsonObject();

		GameLog desLog = deserializer.deserializeLog(logJson);

		// There are 24 messages to be deserialized from the JSON
		assertTrue(desLog.getMessages().size() == 24);
	}
}
