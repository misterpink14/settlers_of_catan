package shared.serializerJSON;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import shared.definitions.HexType;
import shared.locations.HexLocation;
import shared.models.Game;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.chatClasses.GameChat;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Hex;
import shared.models.mapClasses.HexMap;
import shared.models.mapClasses.InvalidTokenException;
import shared.models.mapClasses.InvalidTypeException;
import shared.models.mapClasses.Map;
import shared.models.mapClasses.TerrainHex;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.TurnManager;

public class Deserializer {
	
	private Game deserialize(JsonObject json) {
		// Pull out the information about "deck" from the JSON
		JsonObject jsonDeck = json.getAsJsonObject("deck");
		deserializeDeck(jsonDeck);
		
		// Now get the information labeled "map"
		JsonObject jsonMap = json.getAsJsonObject("map");
		deserializeMap(jsonMap);
		
		// Now "players"
		JsonObject jsonPlayers = json.getAsJsonObject("players");
		deserializePlayers(jsonPlayers);
		
		// "log"
		JsonObject jsonLog = json.getAsJsonObject("log");
		deserializeLog(jsonLog);
		
		// "chat"
		JsonObject jsonChat = json.getAsJsonObject("chat");
		deserializeChat(jsonChat);
		
		// "bank"
		JsonObject jsonBank = json.getAsJsonObject("bank");
		deserializeBank(jsonBank);
		
		// "turnTracker"
		JsonObject jsonTurnTracker = json.getAsJsonObject("turnTracker");
		deserializeTurnTracker(jsonTurnTracker);
		
		// temporary error fix... haha
		return new Game();
	}
	
	private CardDeck deserializeDeck(JsonObject jsonDeck) {
		// The way the deck is currently structured in our code, the
		// deserialization can't happen with the JSON they give us.
		
		// Year of Plenty
		JsonObject jsonYearOfPlenty = jsonDeck.getAsJsonObject("yearOfPlenty");
		jsonYearOfPlenty.getAsInt();
		// Monopoly
		JsonObject jsonMonopoly = jsonDeck.getAsJsonObject("monopoly");
		jsonMonopoly.getAsInt();
		// Soldier
		JsonObject jsonSoldier = jsonDeck.getAsJsonObject("soldier");
		jsonSoldier.getAsInt();
		// RoadBuilding
		JsonObject jsonRoadBuilding = jsonDeck.getAsJsonObject("roadBuilding");
		jsonRoadBuilding.getAsInt();
		// Monument
		JsonObject jsonMonument = jsonDeck.getAsJsonObject("monument");
		jsonMonument.getAsInt();
		
		// temporary error fix... haha
		return new CardDeck();
	}
	
	private Map deserializeMap(JsonObject jsonMap) {
		try {
			
			// Build the hexes for the map
			HexMap hexes = new HexMap();
			JsonArray jsonHexes = jsonMap.getAsJsonArray("hexes");
			for (JsonElement hex : jsonHexes) {
				
				// when initialized, newHex is just garbage. It will
				// be replaced after reading the JSON.
				
				TerrainHex newHex = new TerrainHex(HexType.WOOD, -1);
				if (!hex.getAsJsonObject().getAsJsonObject("resource").isJsonNull()) {
					int tokenNumber = hex.getAsJsonObject().getAsJsonObject("number").getAsInt();
					switch(hex.getAsJsonObject().getAsJsonObject("resource").getAsString()) {
					case "wood":
						newHex = new TerrainHex(HexType.WOOD, tokenNumber);
						break;
					case "brick":
						newHex = new TerrainHex(HexType.BRICK, tokenNumber);
						break;
					case "sheep":
						newHex = new TerrainHex(HexType.SHEEP, tokenNumber);
						break;
					case "wheat":
						newHex = new TerrainHex(HexType.WHEAT, tokenNumber);
						break;
					case "ore":
						newHex = new TerrainHex(HexType.ORE, tokenNumber);
						break;
					default:
						newHex = new TerrainHex(HexType.DESERT, -1);
						break;
					}
				} else { // desert
					// The terrain hex doesn't support creating a desert hex yet
				}
				JsonObject hexJsonLoc = hex.getAsJsonObject().getAsJsonObject("location");
				int locX = hexJsonLoc.getAsJsonObject("x").getAsInt();
				int locY = hexJsonLoc.getAsJsonObject("y").getAsInt();
				HexLocation hexLoc = new HexLocation(locX, locY);
				
				hexes.setHex(hexLoc, newHex);
			}
			
			// Build out the roads from the JSON
			
			// Build out the cities from the JSON
			
			// Build out the settlements from the JSON
			
			// Place dat robber
			
		} catch (InvalidTokenException e) {
		
		} catch(InvalidTypeException e) {
			
		}
		// temporary error fix... haha
		return new Map();
	}
	
	private GamePlayers deserializePlayers(JsonObject jsonPlayers) {
		GamePlayers players = new GamePlayers();
		
		// temporary error fix... haha
		return players;
	}
	
	private GameLog deserializeLog(JsonObject jsonLog) {
		
		// temporary error fix... haha
		return new GameLog();
	}
	
	private GameChat deserializeChat(JsonObject jsonChat) {
		// temporary error fix... haha
		return new GameChat();
	}
	
	private Bank deserializeBank(JsonObject jsonBank) {
		// temporary error fix... haha
		return new Bank();
	}
	
	private TurnManager deserializeTurnTracker(JsonObject jsonTurnTracker) {
		// temporary error fix... haha
		return new TurnManager();
	}
}
