package shared.serializerJSON;

import com.google.gson.JsonObject;

import shared.models.Game;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.chatClasses.GameChat;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Map;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.TurnManager;

public class Serializer {
	
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
		
		// temporarly error fix... haha
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
		
		// temporarly error fix... haha
		return new CardDeck();
	}
	
	private Map deserializeMap(JsonObject jsonMap) {
		
		
		
		// temporarly error fix... haha
		return new Map();
		
	}
	
	private GamePlayers deserializePlayers(JsonObject jsonPlayers) {
		
		
		// temporarly error fix... haha
		return new GamePlayers();
	}
	
	private GameLog deserializeLog(JsonObject jsonLog) {
		
		// temporarly error fix... haha
		return new GameLog();
	}
	
	private GameChat deserializeChat(JsonObject jsonChat) {
		// temporarly error fix... haha
		return new GameChat();
	}
	
	private Bank deserializeBank(JsonObject jsonBank) {
		// temporarly error fix... haha
		return new Bank();
	}
	
	private TurnManager deserializeTurnTracker(JsonObject jsonTurnTracker) {
		// temporarly error fix... haha
		return new TurnManager();
	}
}
