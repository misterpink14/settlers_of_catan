package shared.serializerJSON;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import client.data.GameInfo;
import shared.communication.proxy.OfferTrade;
import shared.models.Game;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.chatClasses.GameChat;
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
	
	public void deserialize() { 
		
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
		return new JsonObject();
	}
	
	/**
	 * Serialize a Map into a JsonObject.
	 * @param map The Map to be serialized.
	 * @return The serialized Map (in the form of a JsonObject)
	 */
	public JsonObject serializeMap(Map map) {
		return new JsonObject();
	}
	
	/**
	 * Serialize a GamePlayers object into a JsonObject.
	 * @param players The GamePlayers object to be serialized.
	 * @return The serialized GamePlayers (in the form of a JsonObject)
	 */
	public JsonArray serializePlayers(GamePlayers players) {
		return new JsonArray();
	}
	
	/**
	 * Serialize a GameLog into a JsonObject.
	 * @param log The GameLog to be serialized.
	 * @return The serialized GameLog (in the form of a JsonObject)
	 */
	public JsonObject serializeLog(GameLog log) {
		return new JsonObject();
	}
	
	/**
	 * Serialize a GameChat into a JsonObject.
	 * @param chat The GameChat to be serialized.
	 * @return The serialized GameChat (in the form of a JsonObject)
	 */
	public JsonObject serializeChat(GameChat chat) {
		return new JsonObject();
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
