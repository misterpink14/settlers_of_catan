package shared.serializerJSON;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import client.data.GameInfo;
import shared.communication.proxy.OfferTrade;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.Game;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.cardClasses.DevCards;
import shared.models.chatClasses.GameChat;
import shared.models.chatClasses.Message;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Map;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.TurnManager;

/** Serializer
 * 
 * @author Bo Pace
 *
 */
public class Serializer {
	
private static Serializer instance = null;
	
	public static Serializer getInstance() {
		if (instance == null) {
			instance = new Serializer();
		}
		return instance;
	}
	
	public void serialize() { 
		
	}
	
	/**
	 * Serialize a game model into a JsonObject.
	 * @param game The game model to be serialized.
	 * @return The serialized model (in the form of a JsonObject)
	 */
	public JsonObject serialize(Game game) {
		return new JsonObject();
	}
	
	/**
	 * Serialize a CardDeck into a JsonObject.
	 * @param deck The CardDeck to be serialized.
	 * @return The serialized CardDeck (in the form of a JsonObject)
	 */
	public JsonObject serializeDeck(CardDeck deck) {
		JsonObject jsonDeck = new JsonObject();
		DevCards cards = deck.getDevCards();
		// Year of Plenty
		jsonDeck.add("yearOfPlenty", new JsonPrimitive(cards.getYearOfPlentyCards()));
		// Monopoly
		jsonDeck.add("monopoly", new JsonPrimitive(cards.getMonopolyCards()));
		// Soldier
		jsonDeck.add("soldier", new JsonPrimitive(cards.getSoldierCards()));
		// RoadBuilding
		jsonDeck.add("roadBuilding", new JsonPrimitive(cards.getRoadBuilderCards()));
		// Monument
		jsonDeck.add("monument", new JsonPrimitive(cards.getMonumentCards()));
		
		return jsonDeck;
	}
	
	/**
	 * Serialize a Map into a JsonObject.
	 * @param map The Map to be serialized.
	 * @return The serialized Map (in the form of a JsonObject)
	 */
	public JsonObject serializeMap(Map map) {
		JsonObject jsonMap = new JsonObject();
		
		// hexes
		// JsonArray jsonHexes = new JsonArray();
		// JsonObject hex1 = new JsonObject();
		
		
		return jsonMap;
	}
	
	/**
	 * Serialize a GamePlayers object into a JsonObject.
	 * @param players The GamePlayers object to be serialized.
	 * @return The serialized GamePlayers (in the form of a JsonObject)
	 */
	public JsonArray serializePlayers(GamePlayers players) {
		JsonArray jsonPlayers = new JsonArray();
		for (int i = 0; i < players.getNumberOfPlayers(); i++) {
			JsonObject jsonPlayer = new JsonObject();
			
			// Resources
			JsonObject jsonResources = new JsonObject();
			jsonResources.add("brick", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfResource(ResourceType.BRICK)));
			jsonResources.add("ore", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfResource(ResourceType.ORE)));
			jsonResources.add("sheep", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfResource(ResourceType.SHEEP)));
			jsonResources.add("wheat", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfResource(ResourceType.WHEAT)));
			jsonResources.add("wood", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfResource(ResourceType.WOOD)));
			jsonPlayer.add("resources", new Gson().toJsonTree(jsonResources));
			
			// Old Dev Cards
			JsonObject jsonOldDevCards = new JsonObject();
			jsonOldDevCards.add("yearOfPlenty", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfDevCard(DevCardType.YEAR_OF_PLENTY)));
			jsonOldDevCards.add("monopoly", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfDevCard(DevCardType.MONOPOLY)));
			jsonOldDevCards.add("soldier", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfDevCard(DevCardType.SOLDIER)));
			jsonOldDevCards.add("roadBuilding", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfDevCard(DevCardType.ROAD_BUILD)));
			jsonOldDevCards.add("monument", new JsonPrimitive(players.getPlayerByIndex(i).getNumOfDevCard(DevCardType.MONUMENT)));
			jsonPlayer.add("oldDevCards", new Gson().toJsonTree(jsonOldDevCards));
			
			// New Dev Cards
			JsonObject jsonNewDevCards = new JsonObject();
			jsonNewDevCards.add("yearOfPlenty", new JsonPrimitive(players.getPlayerByIndex(i).getNewDevCards().getYearOfPlentyCards()));
			jsonNewDevCards.add("monopoly", new JsonPrimitive(players.getPlayerByIndex(i).getNewDevCards().getMonopolyCards()));
			jsonNewDevCards.add("soldier", new JsonPrimitive(players.getPlayerByIndex(i).getNewDevCards().getSoldierCards()));
			jsonNewDevCards.add("roadBuilding", new JsonPrimitive(players.getPlayerByIndex(i).getNewDevCards().getRoadBuilderCards()));
			jsonNewDevCards.add("monument", new JsonPrimitive(players.getPlayerByIndex(i).getNewDevCards().getMonumentCards()));
			jsonPlayer.add("newDevCards", new Gson().toJsonTree(jsonNewDevCards));
			
			// Rest of the player's properties
			jsonPlayer.add("roads", new JsonPrimitive(players.getPlayerByIndex(i).getRoads()));
			jsonPlayer.add("cities", new JsonPrimitive(players.getPlayerByIndex(i).getCities()));
			jsonPlayer.add("settlements", new JsonPrimitive(players.getPlayerByIndex(i).getSettlements()));
			jsonPlayer.add("soldiers", new JsonPrimitive(players.getPlayerByIndex(i).getArmy()));
			jsonPlayer.add("victoryPoints", new JsonPrimitive(players.getPlayerByIndex(i).getVictoryPoints()));
			jsonPlayer.add("monuments", new JsonPrimitive(players.getPlayerByIndex(i).getMonuments()));
			// Not sure how to do this part
			// jsonPlayer.add("playedDevCard", new JsonPrimitive(players.getPlayerByIndex(i)));
			// Or this part
			//jsonPlayer.add("discarded", new JsonPrimitive(players.getPlayerByIndex(i)));
			jsonPlayer.add("playerID", new JsonPrimitive(players.getPlayerByIndex(i).getID()));
			jsonPlayer.add("playerIndex", new JsonPrimitive(players.getPlayerByIndex(i).getIndex()));
			jsonPlayer.add("name", new JsonPrimitive(players.getPlayerByIndex(i).getName()));
			jsonPlayer.add("color", new JsonPrimitive(players.getPlayerByIndex(i).getColor().toString()));
			jsonPlayers.add(jsonPlayer);
			
		}
		
		return jsonPlayers;
	}
	
	/**
	 * Serialize a GameLog into a JsonObject.
	 * @param log The GameLog to be serialized.
	 * @return The serialized GameLog (in the form of a JsonObject)
	 */
	public JsonObject serializeLog(GameLog log) {
		JsonObject jsonLog = new JsonObject();
		JsonArray jsonLines = new JsonArray();
		for (Message message : log.getMessages()) {
			JsonObject jsonLine = new JsonObject();
			jsonLine.add("source", new JsonPrimitive(message.getSource()));
			jsonLine.add("message", new JsonPrimitive(message.getMessage()));
			jsonLines.add(jsonLine);
		}
		jsonLog.add("lines", new Gson().toJsonTree(jsonLines));
		return jsonLog;
	}
	
	/**
	 * Serialize a GameChat into a JsonObject.
	 * @param chat The GameChat to be serialized.
	 * @return The serialized GameChat (in the form of a JsonObject)
	 */
	public JsonObject serializeChat(GameChat chat) {
		JsonObject jsonChat = new JsonObject();
		JsonArray jsonLines = new JsonArray();
		for (Message message : chat.getMessages()) {
			JsonObject jsonLine = new JsonObject();
			jsonLine.add("source", new JsonPrimitive(message.getSource()));
			jsonLine.add("message", new JsonPrimitive(message.getMessage()));
			jsonLines.add(jsonLine);
		}
		jsonChat.add("lines", new Gson().toJsonTree(jsonLines));
		return jsonChat;
	}
	
	/**
	 * Serialize a Bank into a JsonObject.
	 * @param bank The Bank to be serialized.
	 * @return The serialized Bank (in the form of a JsonObject)
	 */
	public JsonObject serializeBank(Bank bank) {
		return new JsonObject();
	}
	
	/**
	 * Serialize a OfferTrade into a JsonObject.
	 * @param offerTrade The OfferTrade to be serialized.
	 * @return The serialized OfferTrade (in the form of a JsonObject)
	 */
	public JsonObject serializeOfferTrade(OfferTrade offerTrade) {
		return new JsonObject();
	}
	
	/**
	 * Serialize a TurnManager into a JsonObject.
	 * @param manager The TurnManager to be serialized.
	 * @return The serialized TurnManager (in the form of a JsonObject)
	 */
	public JsonObject serializeTurnTracker(TurnManager manager) {
		return new JsonObject();
	}
	
	/**
	 * Serialize a GameInfo array into a JsonObject.
	 * @param gamesList The GameInfo array to be serialized.
	 * @return The serialized GameInfo (in the form of a JsonObject)
	 */
	public JsonObject serializeGamesList(GameInfo[] gamesList) {
		return new JsonObject();
	}
	
}
