package shared.serializerJSON;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import client.data.GameInfo;
import shared.communication.proxy.OfferTrade;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.Game;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.cardClasses.DevCards;
import shared.models.chatClasses.GameChat;
import shared.models.chatClasses.Message;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.Map;
import shared.models.mapClasses.Piece;
import shared.models.mapClasses.WaterHex;
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
		JsonArray jsonHexes = new JsonArray();
		JsonArray jsonRoads = new JsonArray();
		JsonArray jsonCities = new JsonArray();
		JsonArray jsonSettlements = new JsonArray();
		JsonObject jsonRadius = new JsonObject();
		JsonArray jsonPorts = new JsonArray();
		JsonObject jsonRobber = new JsonObject();
		
		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {
	
				HexLocation hexLoc = new HexLocation(x, y);
				HexType hexType;
				if (map.getHex(hexLoc) != null) {
					hexType = map.getHex(hexLoc).getHexType();
					if (hexType.equals(HexType.WATER)) {
						WaterHex hex = (WaterHex) map.getHex(hexLoc);
						PortType portType = hex.getPortType();
						
						JsonObject jsonPort = new JsonObject();
						
						switch(portType) {
						case BRICK:
							jsonPort.add("ratio", new JsonPrimitive(2));
							jsonPort.add("resource", new JsonPrimitive("brick"));
							break;
						case ORE:
							jsonPort.add("ratio", new JsonPrimitive(2));
							jsonPort.add("resource", new JsonPrimitive("ore"));
							break;
						case SHEEP:
							jsonPort.add("ratio", new JsonPrimitive(2));
							jsonPort.add("resource", new JsonPrimitive("sheep"));
							break;
						case THREE:
							jsonPort.add("ratio", new JsonPrimitive(3));
							break;
						case WHEAT:
							jsonPort.add("ratio", new JsonPrimitive(2));
							jsonPort.add("resource", new JsonPrimitive("wheat"));
							break;
						case WOOD:
							jsonPort.add("ratio", new JsonPrimitive(2));
							jsonPort.add("resource", new JsonPrimitive("wood"));
							break;
						}
						if (x > 0) {
							if (y <= 0) {
								jsonPort.add("direction", new JsonPrimitive("SW"));
							}
							else {
								jsonPort.add("direction", new JsonPrimitive("NW"));
							}
						}
						else {
							if (y <= 0) {
								jsonPort.add("direction", new JsonPrimitive("S"));
							}
							else {
								jsonPort.add("direction", new JsonPrimitive("N"));
							}
						}
						JsonObject jsonPortLocation = new JsonObject();
						jsonPortLocation.add("x", new JsonPrimitive(x));
						jsonPortLocation.add("y", new JsonPrimitive(y));
						jsonPort.add("location", jsonPortLocation);
						jsonPorts.add(jsonPort);
					}
				}
				
				ArrayList<EdgeLocation> edges = new ArrayList<EdgeLocation>();
				
				EdgeLocation nwEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
				edges.add(nwEdge);
				EdgeLocation nEdge = new EdgeLocation(hexLoc, EdgeDirection.North);
				edges.add(nEdge);
				EdgeLocation neEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
				edges.add(neEdge);
				EdgeLocation swEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
				edges.add(swEdge);
				EdgeLocation sEdge = new EdgeLocation(hexLoc, EdgeDirection.South);
				edges.add(sEdge);
				EdgeLocation seEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
				edges.add(seEdge);
				
				for (EdgeLocation edgeLoc : edges) {
					Piece edgePiece = map.getEdge(edgeLoc);
					if (edgePiece != null) {
						JsonObject jsonRoad = new JsonObject();
						int owner = edgePiece.getOwner();
						jsonRoad.add("owner", new JsonPrimitive(owner));
						JsonObject jsonRoadLocation = new JsonObject();
						switch(edgeLoc.getDir()) {
						case North:
							jsonRoadLocation.add("direction", new JsonPrimitive("N"));
							break;
						case NorthEast:
							jsonRoadLocation.add("direction", new JsonPrimitive("NE"));
							break;
						case NorthWest:
							jsonRoadLocation.add("direction", new JsonPrimitive("NW"));
							break;
						case South:
							jsonRoadLocation.add("direction", new JsonPrimitive("S"));
							break;
						case SouthEast:
							jsonRoadLocation.add("direction", new JsonPrimitive("SE"));
							break;
						case SouthWest:
							jsonRoadLocation.add("direction", new JsonPrimitive("SW"));
							break;
						}
						jsonRoadLocation.add("x", new JsonPrimitive(edgeLoc.getHexLoc().getX()));
						jsonRoadLocation.add("y", new JsonPrimitive(edgeLoc.getHexLoc().getY()));
						jsonRoad.add("location", jsonRoadLocation);
						jsonRoads.add(jsonRoad);
					}
				}
				
				ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
					
				VertexLocation nwVertex = new VertexLocation(hexLoc,  VertexDirection.NorthWest);
				vertices.add(nwVertex);
				VertexLocation neVertex = new VertexLocation(hexLoc,  VertexDirection.NorthEast);
				vertices.add(neVertex);
				VertexLocation eVertex = new VertexLocation(hexLoc,  VertexDirection.East);
				vertices.add(eVertex);
				VertexLocation seVertex = new VertexLocation(hexLoc,  VertexDirection.SouthEast);
				vertices.add(seVertex);
				VertexLocation swVertex = new VertexLocation(hexLoc,  VertexDirection.SouthWest);
				vertices.add(swVertex);
				VertexLocation wVertex = new VertexLocation(hexLoc,  VertexDirection.West);
				vertices.add(wVertex);
				
				for (VertexLocation vertex: vertices) {
					Piece vertexPiece = map.getVertex(vertex);
					if (vertexPiece != null) {
						int owner = vertexPiece.getOwner();
						JsonObject jsonCity = new JsonObject();
						jsonCity.add("owner", new JsonPrimitive(owner));
						JsonObject jsonCityLocation = new JsonObject();
						switch(vertex.getDir()) {
						case East:
							jsonCityLocation.add("direction", new JsonPrimitive("N"));
							break;
						case NorthEast:
							jsonCityLocation.add("direction", new JsonPrimitive("NE"));
							break;
						case NorthWest:
							jsonCityLocation.add("direction", new JsonPrimitive("NW"));
							break;
						case West:
							jsonCityLocation.add("direction", new JsonPrimitive("S"));
							break;
						case SouthEast:
							jsonCityLocation.add("direction", new JsonPrimitive("SE"));
							break;
						case SouthWest:
							jsonCityLocation.add("direction", new JsonPrimitive("SW"));
							break;
						}
						jsonCityLocation.add("x", new JsonPrimitive(vertex.getHexLoc().getX()));
						jsonCityLocation.add("y", new JsonPrimitive(vertex.getHexLoc().getY()));
						jsonCity.add("location", jsonCityLocation);
						if (vertexPiece.getType().toString().equals("CITY")) {
							jsonCities.add(jsonCity);
						}
						else {
							jsonSettlements.add(jsonCity);
						}
					}
				}
				
				if (map.getHex(hexLoc) != null) {
					if (map.getHex(hexLoc).getToken() != -1) {
						JsonObject jsonHex = new JsonObject();
						switch(map.getHex(hexLoc).getHexType()) {
						case BRICK:
							jsonHex.add("resource", new JsonPrimitive("brick"));
							break;
						case DESERT:
							break;
						case ORE:
							jsonHex.add("resource", new JsonPrimitive("ore"));
							break;
						case SHEEP:
							jsonHex.add("resource", new JsonPrimitive("sheep"));
							break;
						case WATER:
							break;
						case WHEAT:
							jsonHex.add("resource", new JsonPrimitive("wheat"));
							break;
						case WOOD:
							jsonHex.add("resource", new JsonPrimitive("wood"));
							break;
						}
						JsonObject jsonHexLocation = new JsonObject();
						jsonHexLocation.add("x", new JsonPrimitive(hexLoc.getX()));
						jsonHexLocation.add("y", new JsonPrimitive(hexLoc.getY()));
						jsonHex.add("location", jsonHexLocation);
						jsonHex.add("number", new JsonPrimitive(map.getHex(hexLoc).getToken()));
						jsonHexes.add(jsonHex);
					}
				}
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					HexLocation hexLoc = new HexLocation(-x, y);
					HexType hexType;
					if (map.getHex(hexLoc) != null) {
						hexType = map.getHex(hexLoc).getHexType();
						if (hexType.equals(HexType.WATER)) {
							WaterHex hex = (WaterHex) map.getHex(hexLoc);
							PortType portType = hex.getPortType();
							
							JsonObject jsonPort = new JsonObject();
							
							switch(portType) {
							case BRICK:
								jsonPort.add("ratio", new JsonPrimitive(2));
								jsonPort.add("resource", new JsonPrimitive("brick"));
								break;
							case ORE:
								jsonPort.add("ratio", new JsonPrimitive(2));
								jsonPort.add("resource", new JsonPrimitive("ore"));
								break;
							case SHEEP:
								jsonPort.add("ratio", new JsonPrimitive(2));
								jsonPort.add("resource", new JsonPrimitive("sheep"));
								break;
							case THREE:
								jsonPort.add("ratio", new JsonPrimitive(3));
								break;
							case WHEAT:
								jsonPort.add("ratio", new JsonPrimitive(2));
								jsonPort.add("resource", new JsonPrimitive("wheat"));
								break;
							case WOOD:
								jsonPort.add("ratio", new JsonPrimitive(2));
								jsonPort.add("resource", new JsonPrimitive("wood"));
								break;
							}
							if (-x < 0) {
								if (y <= 0) {
									jsonPort.add("direction", new JsonPrimitive("SE"));
								}
								else {
									jsonPort.add("direction", new JsonPrimitive("NE"));
								}
							}
							JsonObject jsonPortLocation = new JsonObject();
							jsonPortLocation.add("x", new JsonPrimitive(x));
							jsonPortLocation.add("y", new JsonPrimitive(y));
							jsonPort.add("location", jsonPortLocation);
							jsonPorts.add(jsonPort);
						}
					}
					
					ArrayList<EdgeLocation> edges = new ArrayList<EdgeLocation>();
					
					EdgeLocation nwEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
					edges.add(nwEdge);
					EdgeLocation nEdge = new EdgeLocation(hexLoc, EdgeDirection.North);
					edges.add(nEdge);
					EdgeLocation neEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
					edges.add(neEdge);
					EdgeLocation swEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
					edges.add(swEdge);
					EdgeLocation sEdge = new EdgeLocation(hexLoc, EdgeDirection.South);
					edges.add(sEdge);
					EdgeLocation seEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
					edges.add(seEdge);
					
					for (EdgeLocation edgeLoc : edges) {
						Piece edgePiece = map.getEdge(edgeLoc);
						if (edgePiece != null) {
							JsonObject jsonRoad = new JsonObject();
							int owner = edgePiece.getOwner();
							jsonRoad.add("owner", new JsonPrimitive(owner));
							JsonObject jsonRoadLocation = new JsonObject();
							switch(edgeLoc.getDir()) {
							case North:
								jsonRoadLocation.add("direction", new JsonPrimitive("N"));
								break;
							case NorthEast:
								jsonRoadLocation.add("direction", new JsonPrimitive("NE"));
								break;
							case NorthWest:
								jsonRoadLocation.add("direction", new JsonPrimitive("NW"));
								break;
							case South:
								jsonRoadLocation.add("direction", new JsonPrimitive("S"));
								break;
							case SouthEast:
								jsonRoadLocation.add("direction", new JsonPrimitive("SE"));
								break;
							case SouthWest:
								jsonRoadLocation.add("direction", new JsonPrimitive("SW"));
								break;
							}
							jsonRoadLocation.add("x", new JsonPrimitive(edgeLoc.getHexLoc().getX()));
							jsonRoadLocation.add("y", new JsonPrimitive(edgeLoc.getHexLoc().getY()));
							jsonRoad.add("location", jsonRoadLocation);
							jsonRoads.add(jsonRoad);
						}
					}
					
					ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
						
					VertexLocation nwVertex = new VertexLocation(hexLoc,  VertexDirection.NorthWest);
					vertices.add(nwVertex);
					VertexLocation neVertex = new VertexLocation(hexLoc,  VertexDirection.NorthEast);
					vertices.add(neVertex);
					VertexLocation eVertex = new VertexLocation(hexLoc,  VertexDirection.East);
					vertices.add(eVertex);
					VertexLocation seVertex = new VertexLocation(hexLoc,  VertexDirection.SouthEast);
					vertices.add(seVertex);
					VertexLocation swVertex = new VertexLocation(hexLoc,  VertexDirection.SouthWest);
					vertices.add(swVertex);
					VertexLocation wVertex = new VertexLocation(hexLoc,  VertexDirection.West);
					vertices.add(wVertex);
					
					for (VertexLocation vertex: vertices) {
						Piece vertexPiece = map.getVertex(vertex);
						if (vertexPiece != null) {
							int owner = vertexPiece.getOwner();
							JsonObject jsonCity = new JsonObject();
							jsonCity.add("owner", new JsonPrimitive(owner));
							JsonObject jsonCityLocation = new JsonObject();
							switch(vertex.getDir()) {
							case East:
								jsonCityLocation.add("direction", new JsonPrimitive("N"));
								break;
							case NorthEast:
								jsonCityLocation.add("direction", new JsonPrimitive("NE"));
								break;
							case NorthWest:
								jsonCityLocation.add("direction", new JsonPrimitive("NW"));
								break;
							case West:
								jsonCityLocation.add("direction", new JsonPrimitive("S"));
								break;
							case SouthEast:
								jsonCityLocation.add("direction", new JsonPrimitive("SE"));
								break;
							case SouthWest:
								jsonCityLocation.add("direction", new JsonPrimitive("SW"));
								break;
							}
							jsonCityLocation.add("x", new JsonPrimitive(vertex.getHexLoc().getX()));
							jsonCityLocation.add("y", new JsonPrimitive(vertex.getHexLoc().getY()));
							jsonCity.add("location", jsonCityLocation);
							
							if (vertexPiece.getType().toString().equals("CITY")) {
								jsonCities.add(jsonCity);
							}
							else {
								jsonSettlements.add(jsonCity);
							}
							
						}
					}
					
					if (map.getHex(hexLoc) != null) {
						if (map.getHex(hexLoc).getToken() != -1) {
							JsonObject jsonHex = new JsonObject();
							switch(map.getHex(hexLoc).getHexType()) {
							case BRICK:
								jsonHex.add("resource", new JsonPrimitive("brick"));
								break;
							case DESERT:
								break;
							case ORE:
								jsonHex.add("resource", new JsonPrimitive("ore"));
								break;
							case SHEEP:
								jsonHex.add("resource", new JsonPrimitive("sheep"));
								break;
							case WATER:
								break;
							case WHEAT:
								jsonHex.add("resource", new JsonPrimitive("wheat"));
								break;
							case WOOD:
								jsonHex.add("resource", new JsonPrimitive("wood"));
								break;
							}
							JsonObject jsonHexLocation = new JsonObject();
							jsonHexLocation.add("x", new JsonPrimitive(hexLoc.getX()));
							jsonHexLocation.add("y", new JsonPrimitive(hexLoc.getY()));
							jsonHex.add("location", jsonHexLocation);
							jsonHex.add("number", new JsonPrimitive(map.getHex(hexLoc).getToken()));
							jsonHexes.add(jsonHex);
						}
					}
				}
			}
		}
	
		jsonRobber.add("x", new JsonPrimitive(map.getRobberLocation().getHexLoc().getX()));
		jsonRobber.add("y", new JsonPrimitive(map.getRobberLocation().getHexLoc().getY()));
		
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
		JsonObject jsonBank = new JsonObject();
		jsonBank.add("brick", new JsonPrimitive(bank.getResources().getCards(ResourceType.BRICK)));
		jsonBank.add("ore", new JsonPrimitive(bank.getResources().getCards(ResourceType.ORE)));
		jsonBank.add("sheep", new JsonPrimitive(bank.getResources().getCards(ResourceType.SHEEP)));
		jsonBank.add("wheat", new JsonPrimitive(bank.getResources().getCards(ResourceType.WHEAT)));
		jsonBank.add("wood", new JsonPrimitive(bank.getResources().getCards(ResourceType.WOOD)));
		return jsonBank;
	}
	
	/**
	 * Serialize a OfferTrade into a JsonObject.
	 * @param offerTrade The OfferTrade to be serialized.
	 * @return The serialized OfferTrade (in the form of a JsonObject)
	 */
	public JsonObject serializeOfferTrade(OfferTrade offerTrade) {
		JsonObject jsonOfferTrade = new JsonObject();
		jsonOfferTrade.add("sender", new JsonPrimitive(offerTrade.playerIndex));
		jsonOfferTrade.add("receiver", new JsonPrimitive(offerTrade.receiverIndex));
		JsonObject jsonOffer = new JsonObject();
		jsonOffer.add("brick", new JsonPrimitive(offerTrade.brick));
		jsonOffer.add("ore", new JsonPrimitive(offerTrade.ore));
		jsonOffer.add("sheep", new JsonPrimitive(offerTrade.sheep));
		jsonOffer.add("wheat", new JsonPrimitive(offerTrade.wheat));
		jsonOffer.add("wood", new JsonPrimitive(offerTrade.wood));
		jsonOfferTrade.add("offer", new Gson().toJsonTree(jsonOffer));
		return jsonOfferTrade;
	}
	
	/**
	 * Serialize a TurnManager into a JsonObject.
	 * @param manager The TurnManager to be serialized.
	 * @return The serialized TurnManager (in the form of a JsonObject)
	 */
	public JsonObject serializeTurnTracker(TurnManager manager) {
		JsonObject jsonTurnTracker = new JsonObject();
		
		return new JsonObject();
	}
	
	/**
	 * Serialize a GameInfo array into a JsonObject.
	 * @param gamesList The GameInfo array to be serialized.
	 * @return The serialized GameInfo (in the form of a JsonObject)
	 */
	public JsonObject serializeGamesList(GameInfo[] gamesList) {
		JsonObject jsonGamesList = new JsonObject();
		for (GameInfo gameInfo : gamesList) {
			JsonObject jsonGame = new JsonObject();
			//jsonGame.add("name", new gameInfo.getTitle());
		}
		return jsonGamesList;
	}
	
}
