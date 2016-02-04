package shared.serializerJSON;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.RobberLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.Game;
import shared.models.cardClasses.Bank;
import shared.models.cardClasses.CardDeck;
import shared.models.chatClasses.GameChat;
import shared.models.chatClasses.Message;
import shared.models.logClasses.GameLog;
import shared.models.mapClasses.EdgeMap;
import shared.models.mapClasses.Hex;
import shared.models.mapClasses.HexMap;
import shared.models.mapClasses.InvalidTokenException;
import shared.models.mapClasses.InvalidTypeException;
import shared.models.mapClasses.Map;
import shared.models.mapClasses.Piece;
import shared.models.mapClasses.PortMap;
import shared.models.mapClasses.TerrainHex;
import shared.models.mapClasses.VertexMap;
import shared.models.mapClasses.WaterHex;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.Player;
import shared.models.playerClasses.TurnManager;

public class Deserializer {
	
	private Game deserialize(JsonObject json) {
		// the "des" prefix signifies that the object has been deserialized.
		
		// Pull out the information about "deck" from the JSON
		JsonObject jsonDeck = json.getAsJsonObject("deck");
		CardDeck desDeck = deserializeDeck(jsonDeck);
		
		// Now get the information labeled "map"
		JsonObject jsonMap = json.getAsJsonObject("map");
		Map desMap = deserializeMap(jsonMap);
		
		// Now "players"
		JsonArray jsonPlayers = json.getAsJsonArray("players");
		GamePlayers desPlayers = deserializePlayers(jsonPlayers);
		
		// "log"
		JsonObject jsonLog = json.getAsJsonObject("log");
		GameLog desLog = deserializeLog(jsonLog);
		
		// "chat"
		JsonObject jsonChat = json.getAsJsonObject("chat");
		GameChat desChat = deserializeChat(jsonChat);
		
		// "bank"
		JsonObject jsonBank = json.getAsJsonObject("bank");
		Bank desBank = deserializeBank(jsonBank);
		
		// "turnTracker"
		JsonObject jsonTurnTracker = json.getAsJsonObject("turnTracker");
		deserializeTurnTracker(jsonTurnTracker);
		
		// currentTurn
		int desCurrentTurn = -1;
		
		// hasPlayedDevCard
		boolean desHasPlayedDevCard = false;
		
		// winner
		int desWinner = json.getAsJsonObject("winner").getAsInt();

		return new Game(desMap, desBank, desDeck, desPlayers, desLog, desChat,
				        desCurrentTurn, desHasPlayedDevCard, desWinner);
	}
	
	private CardDeck deserializeDeck(JsonObject jsonDeck) {
		// Year of Plenty
		JsonObject jsonYearOfPlenty = jsonDeck.getAsJsonObject("yearOfPlenty");
		int yearOfPlentyCount = jsonYearOfPlenty.getAsInt();
		// Monopoly
		JsonObject jsonMonopoly = jsonDeck.getAsJsonObject("monopoly");
		int monopolyCount = jsonMonopoly.getAsInt();
		// Soldier
		JsonObject jsonSoldier = jsonDeck.getAsJsonObject("soldier");
		int soldierCount = jsonSoldier.getAsInt();
		// RoadBuilding
		JsonObject jsonRoadBuilding = jsonDeck.getAsJsonObject("roadBuilding");
		int roadBuildingCount = jsonRoadBuilding.getAsInt();
		// Monument
		JsonObject jsonMonument = jsonDeck.getAsJsonObject("monument");
		int monumentCount = jsonMonument.getAsInt();
		
		HashMap<DevCardType, Integer> cards = new HashMap<DevCardType, Integer>();
		cards.put(DevCardType.YEAR_OF_PLENTY, yearOfPlentyCount);
		cards.put(DevCardType.MONOPOLY, monopolyCount);
		cards.put(DevCardType.SOLDIER, soldierCount);
		cards.put(DevCardType.ROAD_BUILD, roadBuildingCount);
		cards.put(DevCardType.MONUMENT, monumentCount);

		return new CardDeck(cards);
	}
	
	private Map deserializeMap(JsonObject jsonMap) {
		HexMap hexes = new HexMap();
		VertexMap vertices = new VertexMap();
		EdgeMap edges = new EdgeMap();
		PortMap ports = new PortMap();
		RobberLocation robberLocation = null;
		try {
			
			// Build the hexes for the map
			// HexMap hexes
			JsonArray jsonHexes = jsonMap.getAsJsonArray("hexes");
			for (JsonElement hex : jsonHexes) {
				
				// when initialized, newHex is just garbage. It will
				// be replaced after reading the JSON.
				
				TerrainHex newHex;
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
					newHex = new TerrainHex(HexType.DESERT, -1);
				}
				JsonObject hexJsonLoc = hex.getAsJsonObject().getAsJsonObject("location");
				int locX = hexJsonLoc.getAsJsonObject("x").getAsInt();
				int locY = hexJsonLoc.getAsJsonObject("y").getAsInt();
				HexLocation hexLoc = new HexLocation(locX, locY);
				
				hexes.setHex(hexLoc, newHex);
			}
			
			// Build out the roads from the JSON
			JsonArray jsonRoads = jsonMap.getAsJsonArray("roads");
			for (JsonElement road : jsonRoads) {
				int ownerIndex = road.getAsJsonObject().getAsJsonObject("owner").getAsInt();
				Piece newRoad = new Piece(PieceType.ROAD, ownerIndex);
				JsonObject jsonRoadLoc = road.getAsJsonObject().getAsJsonObject("location");
				int locX = jsonRoadLoc.getAsJsonObject("x").getAsInt();
				int locY = jsonRoadLoc.getAsJsonObject("y").getAsInt();
				HexLocation hexLoc = new HexLocation(locX, locY);
				EdgeLocation newEdgeLoc = null;
				switch(jsonRoadLoc.getAsJsonObject("direction").getAsString()) {
				case "NW":
					newEdgeLoc = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
					break;
				case "N":
					newEdgeLoc = new EdgeLocation(hexLoc, EdgeDirection.North);
					break;
				case "NE":
					newEdgeLoc = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
					break;
				case "SE":
					newEdgeLoc = new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
					break;
				case "S":
					newEdgeLoc = new EdgeLocation(hexLoc, EdgeDirection.South);
					break;
				case "SW":
					newEdgeLoc = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
					break;
				}
				edges.setEdge(newEdgeLoc, newRoad);
			}

			// Build out the cities from the JSON
			JsonArray jsonCities = jsonMap.getAsJsonArray("cities");
			for (JsonElement city : jsonCities) {
				int ownerIndex = city.getAsJsonObject().getAsJsonObject("owner").getAsInt();
				Piece newCity = new Piece(PieceType.CITY, ownerIndex);
				
				JsonObject jsonCityLoc = city.getAsJsonObject().getAsJsonObject("location");
				int locX = jsonCityLoc.getAsJsonObject("x").getAsInt();
				int locY = jsonCityLoc.getAsJsonObject("y").getAsInt();
				HexLocation hexLoc = new HexLocation(locX, locY);
				
				VertexLocation newVertexLoc = null;
				switch(jsonCityLoc.getAsJsonObject("direction").getAsString()) {
				case "W":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.West);
					break;
				case "NW":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.NorthWest);
					break;
				case "NE":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.NorthEast);
					break;
				case "E":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.East);
					break;
				case "SE":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthEast);
					break;
				case "SW":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
					break;
				}
				vertices.setVertex(newVertexLoc, newCity);
			}
			
			// Build out the settlements from the JSON
			JsonArray jsonSettlements = jsonMap.getAsJsonArray("settlements");
			for (JsonElement settlement : jsonSettlements) {
				int ownerIndex = settlement.getAsJsonObject().getAsJsonObject("owner").getAsInt();
				Piece newSettlement = new Piece(PieceType.SETTLEMENT, ownerIndex);
				
				JsonObject jsonSettlementLoc = settlement.getAsJsonObject().getAsJsonObject("location");
				int locX = jsonSettlementLoc.getAsJsonObject("x").getAsInt();
				int locY = jsonSettlementLoc.getAsJsonObject("y").getAsInt();
				HexLocation hexLoc = new HexLocation(locX, locY);
				
				VertexLocation newVertexLoc = null;
				switch(jsonSettlementLoc.getAsJsonObject("direction").getAsString()) {
				case "W":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.West);
					break;
				case "NW":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.NorthWest);
					break;
				case "NE":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.NorthEast);
					break;
				case "E":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.East);
					break;
				case "SE":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthEast);
					break;
				case "SW":
					newVertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
					break;
				}
				vertices.setVertex(newVertexLoc, newSettlement);
			}
			
			// Build out the ports from the JSON
			JsonArray jsonPorts = jsonMap.getAsJsonArray("ports");
			for (JsonElement port : jsonPorts) {
				int ratio = port.getAsJsonObject().getAsJsonObject("ratio").getAsInt();
				WaterHex newWaterHex = null;
				if (ratio == 3) {
					newWaterHex = new WaterHex(PortType.THREE);
				} else {
					switch(port.getAsJsonObject().getAsJsonObject("resource").getAsString()) {
					case "wood":
						newWaterHex = new WaterHex(PortType.WOOD);
						break;
					case "brick":
						newWaterHex = new WaterHex(PortType.BRICK);
						break;
					case "sheep":
						newWaterHex = new WaterHex(PortType.SHEEP);
						break;
					case "wheat":
						newWaterHex = new WaterHex(PortType.WHEAT);
						break;
					case "ore":
						newWaterHex = new WaterHex(PortType.ORE);
						break;
					}
				}
				JsonObject jsonPortLoc = port.getAsJsonObject().getAsJsonObject("location");
				int locX = jsonPortLoc.getAsJsonObject("x").getAsInt();
				int locY = jsonPortLoc.getAsJsonObject("y").getAsInt();
				HexLocation waterHexLoc = new HexLocation(locX, locY);
				
				hexes.setHex(waterHexLoc, newWaterHex);
			}
			
			// Place dat robber
			JsonObject jsonRobberLoc = jsonMap.getAsJsonObject("robber");
			int robberLocX = jsonRobberLoc.getAsJsonObject("x").getAsInt();
			int robberLocY = jsonRobberLoc.getAsJsonObject("y").getAsInt();
			HexLocation robberHex = new HexLocation(robberLocX, robberLocY);
			robberLocation = new RobberLocation(robberHex);
			
		} catch (InvalidTokenException e) {
		
		} catch(InvalidTypeException e) {
			
		}
		
		return new Map(hexes, vertices, edges, ports, robberLocation);
	}
	
	private GamePlayers deserializePlayers(JsonArray jsonPlayers) {
		GamePlayers players = new GamePlayers();
		
		for (JsonElement player : jsonPlayers) {
			// Resources
			HashMap<ResourceType, Integer> playerResources = new HashMap<ResourceType, Integer>();
			JsonObject playerObj = player.getAsJsonObject();
			JsonObject resources = playerObj.getAsJsonObject("resources");
			int brickCount = resources.getAsJsonObject("brick").getAsInt();
			playerResources.put(ResourceType.BRICK, brickCount);
			int woodCount = resources.getAsJsonObject("wood").getAsInt();
			playerResources.put(ResourceType.WOOD, brickCount);
			int sheepCount = resources.getAsJsonObject("sheep").getAsInt();
			playerResources.put(ResourceType.SHEEP, sheepCount);
			int wheatCount = resources.getAsJsonObject("wheat").getAsInt();
			playerResources.put(ResourceType.WHEAT, wheatCount);
			int oreCount = resources.getAsJsonObject("ore").getAsInt();
			playerResources.put(ResourceType.ORE, oreCount);
			
			// Old Dev Cards
			HashMap<DevCardType, Integer> playerOldDevCards = new HashMap<DevCardType, Integer>();
			JsonObject oldDevCards = playerObj.getAsJsonObject("oldDevCards");
			int oldYearOfPlentyCount = oldDevCards.getAsJsonObject("yearOfPlenty").getAsInt();
			playerOldDevCards.put(DevCardType.YEAR_OF_PLENTY, oldYearOfPlentyCount);
			int oldMonopolyCount = resources.getAsJsonObject("monopoly").getAsInt();
			playerOldDevCards.put(DevCardType.MONOPOLY, oldMonopolyCount);
			int oldSoldierCount = resources.getAsJsonObject("soldier").getAsInt();
			playerOldDevCards.put(DevCardType.SOLDIER, oldSoldierCount);
			int oldRoadBuildingCount = resources.getAsJsonObject("roadBuilding").getAsInt();
			playerOldDevCards.put(DevCardType.ROAD_BUILD, oldRoadBuildingCount);
			int oldMonumentCount = resources.getAsJsonObject("monument").getAsInt();
			playerOldDevCards.put(DevCardType.MONUMENT, oldMonumentCount);
			
			// New Dev Cards
			HashMap<DevCardType, Integer> playerNewDevCards = new HashMap<DevCardType, Integer>();
			JsonObject newDevCards = playerObj.getAsJsonObject("newDevCards");
			int newYearOfPlentyCount = newDevCards.getAsJsonObject("yearOfPlenty").getAsInt();
			playerNewDevCards.put(DevCardType.YEAR_OF_PLENTY, newYearOfPlentyCount);
			int newMonopolyCount = resources.getAsJsonObject("monopoly").getAsInt();
			playerNewDevCards.put(DevCardType.MONOPOLY, newMonopolyCount);
			int newSoldierCount = resources.getAsJsonObject("soldier").getAsInt();
			playerNewDevCards.put(DevCardType.SOLDIER, newSoldierCount);
			int newRoadBuildingCount = resources.getAsJsonObject("roadBuilding").getAsInt();
			playerNewDevCards.put(DevCardType.ROAD_BUILD, newRoadBuildingCount);
			int newMonumentCount = resources.getAsJsonObject("monument").getAsInt();
			playerNewDevCards.put(DevCardType.MONUMENT, newMonumentCount);
			
			int playerRoads = playerObj.getAsJsonObject("roads").getAsInt();
			int playerCities = playerObj.getAsJsonObject("cities").getAsInt();
			int playerSettlements = playerObj.getAsJsonObject("settlements").getAsInt();
			int playerSoldiers = playerObj.getAsJsonObject("soldiers").getAsInt();
			int playerVictoryPoints = playerObj.getAsJsonObject("victoryPoints").getAsInt();
			int playerMonuments = playerObj.getAsJsonObject("monuments").getAsInt();
			int playerID = playerObj.getAsJsonObject("playerID").getAsInt();
			int playerIndex = playerObj.getAsJsonObject("playerIndex").getAsInt();
			String playerName = playerObj.getAsJsonObject("name").getAsString();
			CatanColor playerColor = null;
			
			switch(playerObj.getAsJsonObject("color").getAsString()) {
			case "red":
				playerColor = CatanColor.RED;
				break;
			case "orange":
				playerColor = CatanColor.ORANGE;
				break;
			case "yellow":
				playerColor = CatanColor.YELLOW;
				break;
			case "blue":
				playerColor = CatanColor.BLUE;
				break;
			case "green":
				playerColor = CatanColor.GREEN;
				break;
			case "purple":
				playerColor = CatanColor.PURPLE;
				break;
			case "puce":
				playerColor = CatanColor.PUCE;
				break;
			case "white":
				playerColor = CatanColor.WHITE;
				break;
			case "brown":
				playerColor = CatanColor.BROWN;
				break;
			}
			
			try {
				players.addPlayer(new Player(playerResources, playerOldDevCards, playerNewDevCards,
						playerRoads, playerCities, playerSettlements, playerSoldiers, playerVictoryPoints,
						playerMonuments, playerID, playerName, playerColor, false, false));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return players;
	}
	
	private GameLog deserializeLog(JsonObject jsonLog) {
		ArrayList<Message> messages = new ArrayList<Message>();
		JsonArray jsonLines = jsonLog.getAsJsonArray("lines");
		for (JsonElement line : jsonLines) {
			String source = line.getAsJsonObject().getAsJsonObject("source").getAsString();
			String message = line.getAsJsonObject().getAsJsonObject("message").getAsString();
			messages.add(new Message(source, message));
		}
		return new GameLog(messages);
	}
	
	private GameChat deserializeChat(JsonObject jsonChat) {
		ArrayList<Message> messages = new ArrayList<Message>();
		JsonArray jsonLines = jsonChat.getAsJsonArray("lines");
		for (JsonElement line : jsonLines) {
			String source = line.getAsJsonObject().getAsJsonObject("source").getAsString();
			String message = line.getAsJsonObject().getAsJsonObject("message").getAsString();
			messages.add(new Message(source, message));
		}
		return new GameChat(messages);
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