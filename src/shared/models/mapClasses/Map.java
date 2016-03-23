package shared.models.mapClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.RobberLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;


/**
 * Responsible for keeping track of the "game board"
 * 
 * @author benthompson
 */
public class Map 
{
	/**
	 * The collection of Hex pieces. There are a total of 34 Hex pieces. 
	 * The x (horizontal) and y (diagonal: from top-left to bottom right) axis' increment from left to right.
	 *  The most center hex, in both x and y, is 0 with negative values to the left and positive values to 
	 *  the right
	 */
	private HexMap Hexes = new HexMap();
	private VertexMap Vertexes = new VertexMap();
	private EdgeMap Edges = new EdgeMap();
	private RobberLocation Robber;
	private PlayerMap PlayerPieces = new PlayerMap();
	
	
	
// CONSTRUCTORS
	public Map() {}
	
	
	/**
	 * Constructor generates a new map. Requires a String to be validated/parsed
	 * @throws InvalidTypeException 
	 * @throws InvalidTokenException 
	 */
	public Map(Boolean randomTiles, Boolean randomNumbers, Boolean randomPorts) throws InvalidTokenException, InvalidTypeException {

		Random rand = new Random();
		HexMap hexes = new HexMap();
		
		List<HexType> hexTypes =  new ArrayList<HexType>();

		hexTypes.add(HexType.DESERT);
		hexTypes.add(HexType.WOOD);
		hexTypes.add(HexType.WHEAT);
		hexTypes.add(HexType.WOOD);
		hexTypes.add(HexType.WHEAT);
		hexTypes.add(HexType.BRICK);
		hexTypes.add(HexType.ORE);
		hexTypes.add(HexType.BRICK);
		hexTypes.add(HexType.SHEEP);
		hexTypes.add(HexType.BRICK);
		hexTypes.add(HexType.SHEEP);
		hexTypes.add(HexType.SHEEP);
		hexTypes.add(HexType.ORE);
		hexTypes.add(HexType.WOOD);
		hexTypes.add(HexType.SHEEP);
		hexTypes.add(HexType.WHEAT);
		hexTypes.add(HexType.ORE);
		hexTypes.add(HexType.WHEAT);
		hexTypes.add(HexType.WOOD);
		
		
		List<Integer> tokens = new ArrayList<Integer>();

		tokens.add(3);
		tokens.add(11);
		tokens.add(4);
		tokens.add(8);
		tokens.add(4);
		tokens.add(9);
		tokens.add(5);
		tokens.add(10);
		tokens.add(8);
		tokens.add(10);
		tokens.add(9);
		tokens.add(3);
		tokens.add(11);
		tokens.add(12);
		tokens.add(6);
		tokens.add(5);
		tokens.add(2);
		tokens.add(6);
		
		
		int hexIndex = 0;
		int tokenIndex = 0;

		for (int x = 0; x <= 2; ++x) {
			
			int maxY = 2 - x;			
			for (int y = -2; y <= maxY; ++y) {
				
				if (randomTiles) {
					hexIndex = rand.nextInt(hexTypes.size());
				}
				if (randomNumbers) {
					tokenIndex = rand.nextInt(tokens.size());
				}
				HexType hexType = hexTypes.get(hexIndex);
				if (randomTiles) {
					hexTypes.remove(hexIndex);
				}
				hexIndex++;
				HexLocation hexLoc = new HexLocation(x, y);
				if (hexType.equals(HexType.DESERT)) {
					this.Robber = new RobberLocation(hexLoc);
					hexes.setHex(hexLoc, new Hex(hexType, -1));
				}
				else {
					hexes.setHex(hexLoc, new Hex(
							hexType, 
							tokens.get(tokenIndex)
						)
					);

					if (randomNumbers) {
						tokens.remove(tokenIndex);
					}
					tokenIndex++;
				}
			}
			
			if (x != 0) {
				int minY = x - 2;
				for (int y = minY; y <= 2; ++y) {

					if (randomTiles) {
						hexIndex = rand.nextInt(hexTypes.size());
					}
					if (randomNumbers) {
						tokenIndex = rand.nextInt(tokens.size());
					}
					HexType hexType = hexTypes.get(hexIndex);
					hexIndex++;
					HexLocation hexLoc = new HexLocation(-x, y);
					if (hexType.equals(HexType.DESERT)) {
						this.Robber = new RobberLocation(hexLoc);
						hexes.setHex(hexLoc, new Hex(hexType, -1));
					}
					else {
						hexes.setHex(hexLoc, new Hex(hexType, tokens.get(tokenIndex)));

						if (randomNumbers) {
							tokens.remove(tokenIndex);
						}
						tokenIndex++;
					}
				}
			}
		}
		
		List<PortType> portTypes = new ArrayList<PortType>();

		portTypes.add(PortType.THREE);
		portTypes.add(PortType.BRICK);
		portTypes.add(PortType.WOOD);
		portTypes.add(PortType.THREE);
		portTypes.add(PortType.WHEAT);
		portTypes.add(PortType.ORE);
		portTypes.add(PortType.THREE);
		portTypes.add(PortType.SHEEP);
		portTypes.add(PortType.THREE);
		
		int portIndex = 0;

		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}
		hexes.setHex(new HexLocation(0, 3), new WaterHex(portTypes.get(portIndex), EdgeDirection.North));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}
		
		hexes.setHex(new HexLocation(-2, 3), new WaterHex(portTypes.get(portIndex), EdgeDirection.NorthEast));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}
		
		hexes.setHex(new HexLocation(-3, 2), new WaterHex(portTypes.get(portIndex), EdgeDirection.NorthEast));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}
		
		hexes.setHex(new HexLocation(-3, 0), new WaterHex(portTypes.get(portIndex), EdgeDirection.SouthEast));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}
		
		hexes.setHex(new HexLocation(-1, -2), new WaterHex(portTypes.get(portIndex), EdgeDirection.SouthEast));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}
		
		hexes.setHex(new HexLocation(1, -3), new WaterHex(portTypes.get(portIndex), EdgeDirection.SouthWest));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}
		
		hexes.setHex(new HexLocation(3, -3), new WaterHex(portTypes.get(portIndex), EdgeDirection.SouthWest));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}

		hexes.setHex(new HexLocation(3, -1), new WaterHex(portTypes.get(portIndex), EdgeDirection.NorthWest));
		portIndex++;
		
		if (randomNumbers) {
			portIndex = rand.nextInt(hexTypes.size());
		}

		hexes.setHex(new HexLocation(2, 1), new WaterHex(portTypes.get(portIndex), EdgeDirection.NorthWest));
	}
	
	
	/**
	 * Constructor generates a new map. Requires a String to be validated/parsed
	 */
	public Map(HexMap h, VertexMap v, EdgeMap e, RobberLocation r, PlayerMap playerPieces) {
		
		this.Hexes = h;
		this.Vertexes = v;
		this.Edges = e;
		this.Robber = r;
		this.PlayerPieces = (playerPieces == null) ? new PlayerMap() : playerPieces;
	}
	
	
// GETTERS
	public HexMap getHexMap() {
		return Hexes;
	}
	
	public VertexMap getVertexMap() {
		return Vertexes;
	}
	
	public EdgeMap getEdgeMap() {
		return Edges;
	}
	
	public RobberLocation getRobberLocation() {
		return Robber;
	}
	
	public PlayerMap getPlayerMap() {
		return PlayerPieces;
	}
	
	
// SETTERS
	public void setHexMap(HexMap hexes) {
		this.Hexes = hexes;
	}

	
	public void setEdgeMap(EdgeMap edges) {
		this.Edges = edges;
	}
	
	
	public void setVertexMap(VertexMap vertexes) {
		this.Vertexes = vertexes;
	}
	
	
	public void setRobberLocation(RobberLocation robber) {
		this.Robber = robber;
	}
	
	
	public void setPlayerMap(PlayerMap playerPieces) {
		this.PlayerPieces = playerPieces;
	}
	
	
	public void setVertex(VertexLocation loc, Piece p) throws InvalidTypeException {
		this.Vertexes.setVertex(loc, p);
	}
	
	

// Public METHODS
	public ArrayList<Piece> placeRobber(HexLocation hexLoc) {
		
		ArrayList<Piece> playerIndexes = new ArrayList<Piece> ();
		VertexLocation vertexLoc = new VertexLocation(hexLoc, VertexDirection.East).getNormalizedLocation();
		Piece piece;
		try {
			piece = this.Vertexes.getPiece(vertexLoc);
			if (piece.getOwner() != -1) {
				playerIndexes.add(piece);
			}
		} catch (Exception e) {
			
		}
		
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.NorthEast).getNormalizedLocation();
		try {
			piece = this.Vertexes.getPiece(vertexLoc);
			if (piece.getOwner() != -1) {
				playerIndexes.add(piece);
			}
		} catch (Exception e) {
			
		}
		
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.NorthWest).getNormalizedLocation();
		try {
			piece = this.Vertexes.getPiece(vertexLoc);
			if (piece.getOwner() != -1) {
				playerIndexes.add(piece);
			}
		} catch (Exception e) {
			
		}
		
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.West).getNormalizedLocation();
		try {
			piece = this.Vertexes.getPiece(vertexLoc);
			if (piece.getOwner() != -1) {
				playerIndexes.add(piece);
			}
		} catch (Exception e) {
			
		}
		
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest).getNormalizedLocation();
		try {
			piece = this.Vertexes.getPiece(vertexLoc);
			if (piece.getOwner() != -1) {
				playerIndexes.add(piece);
			}
		} catch (Exception e) {
			
		}
		
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthEast).getNormalizedLocation();
		try {
			piece = this.Vertexes.getPiece(vertexLoc);
			if (piece.getOwner() != -1) {
				playerIndexes.add(piece);
			}
		} catch (Exception e) {
			
		}
		
		return playerIndexes;
	}
	
	/**
	 * Use to add a road to the player map
	 * 
	 * @param loc
	 * @param playerIndex
	 */
	public void addRoadToPlayerMap(EdgeLocation loc, int playerIndex) {
		this.PlayerPieces.addRoad(loc, playerIndex);
	}
	
	/**
	 * Use to add a road to the Edge map
	 * 
	 * @param loc
	 * @param playerIndex
	 * @throws InvalidTypeException 
	 */
	public void addRoadToEdgeMap(EdgeLocation loc, Piece newRoad) throws InvalidTypeException {
		this.Edges.setEdge(loc, newRoad);
	}
	
	/**
	 * Use to add a settlement to the player map
	 * 
	 * @param loc
	 * @param playerIndex
	 */
	public void addSettlementToPlayerMap(VertexLocation loc, int playerIndex) {
		this.PlayerPieces.addSettlement(loc, playerIndex);
	}
	
	/**
	 * Use to add a settlement to the vertex map
	 * 
	 * @param loc
	 * @param playerIndex
	 * @throws InvalidTypeException 
	 */
	public void addSettlementToVertexMap(VertexLocation loc, Piece newSettlement) throws InvalidTypeException {
		this.Vertexes.setVertex(loc, newSettlement);
	}
	
	/**
	 * Use to add a city to the player map
	 * 
	 * @param loc
	 * @param playerIndex
	 */
	public void addCityToPlayerMap(VertexLocation loc, int playerIndex) {
		this.PlayerPieces.addCity(loc, playerIndex);
	}
	
	/**
	 * Use to add a city to the vertex map
	 * 
	 * @param loc
	 * @param playerIndex
	 * @throws InvalidTypeException 
	 */
	public void addCityToVertexMap(VertexLocation loc, Piece newCity) throws InvalidTypeException {
		this.Vertexes.setVertex(loc, newCity);
	}

	
	/**
	 * Moves a robber to a given HexLocation
	 *  Constraints: must not already be there, cannot be a sea piece
	 * 
	 * @param location
	 */
	public boolean canPlaceRobber(HexLocation loc) {
		if (!this.Robber.canPlaceRobber(loc)){
			return false;
		}
		return this.Hexes.canPlaceRobber(loc);
	}
	
	
	/**
	 * Check if player can place a road at a given EdgeLocation
	 *  Constraints: Must be adjacent to player's existing roads, cannot be in between two WaterHexs
	 * 
	 * @param location
	 */
	public boolean canPlaceRoad(EdgeLocation edgeLoc, int ownerIndex, boolean isSetup) {
		try {
			edgeLoc = edgeLoc.getNormalizedLocation();
			return this.isValidEdge(edgeLoc) && this.Edges.canBuildRoad(edgeLoc, ownerIndex, isSetup);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	
	/**
	 * Check if player can place a settlement at a given VertexLocation
	 * 	Constrains: Must be adjacent to a player's existing roads. Must not be on outside Vertexes; in other
	 *  words, must touch a TerrainHex
	 * 
	 * @param location
	 */
	public boolean canPlaceSettlement(VertexLocation vertexLoc, int ownerIndex, boolean isSetup) {
		
		try {
			vertexLoc = vertexLoc.getNormalizedLocation();
			return this._canPlaceSettlement(vertexLoc) && this.Vertexes.canPlaceSettlement(vertexLoc, ownerIndex, isSetup);
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch (InvalidTypeException e) {
			return false;
		}
	}
	
	
	/**
	 * Check if player can place a city at a given VertexLocation
	 *  Constrains: Must be adjacent to a player's existing roads and must replace a settlement.
	 * 
	 * @param location
	 */
	public boolean canPlaceCity(VertexLocation vertexLoc, int ownerIndex) {
		
		try {
			vertexLoc = vertexLoc.getNormalizedLocation();
			return this.Vertexes.canPlaceCity(vertexLoc, ownerIndex);
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * Checks if a user can maritime trade.
	 * 
	 * @param ownerIndex
	 * @return
	 */
	public boolean canMaritimeTrade(int ownerIndex, ResourceType type) {
		
		PortType portType = this.convertResourceTypeToPortType(type);
		for(VertexLocation loc: this.PlayerPieces.getVertexPieceList(ownerIndex)) {
			if (isOnPort(loc, portType)) {
				return true;
			}
		}
		return false;
	}
	
	public Hex getHex(HexLocation loc) {
		try {
			return this.Hexes.getHex(loc);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Piece getEdge(EdgeLocation loc) {
		try {
			return this.Edges.getEdge(loc);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Piece getVertex(VertexLocation loc) {
		try {
			return this.Vertexes.getPiece(loc);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
// Private METHOD
	/**
	 * Checks if a given Vertex Location is on a port
	 * 
	 * @param loc
	 * @param portType
	 * @return
	 */
	private boolean isOnPort(VertexLocation loc, PortType portType)  {
		
		HexLocation hexLoc = loc.getHexLoc();
		try {
			switch (loc.getDir())
			{
				case NorthWest:
				{
					// Check N and NW Edges
					Hex h = this.Hexes.getHex(hexLoc);
					if (h.getHexType() == HexType.WATER) {
						
						WaterHex wh = (WaterHex) h;
						if (wh.getPortType() != null && 
							(wh.getDir() == EdgeDirection.North || wh.getDir() == EdgeDirection.NorthWest)) {
							if (wh.getPortType() == portType) {
								return true;
							}
						}
					}
					
	
					// Check (x-1,y) SE and NE
					HexLocation hexLoc_ = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
					
					h = this.Hexes.getHex(hexLoc_);
					if (h.getHexType() == HexType.WATER) {
						WaterHex wh = (WaterHex) h;
						if (wh.getPortType() != null && 
							(wh.getDir() == EdgeDirection.SouthEast || wh.getDir() == EdgeDirection.NorthEast)) {
							if (wh.getPortType() == portType) {
								return true;
							}
						}
					}
					
					
					
					// Check (x, y-1) S and SW
					hexLoc_ = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
					
					h = this.Hexes.getHex(hexLoc_);
					if (h.getHexType() == HexType.WATER) {
						WaterHex wh = (WaterHex) h;
						if (wh.getPortType() != null && 
							(wh.getDir() == EdgeDirection.South || wh.getDir() == EdgeDirection.SouthWest)) {
							if (wh.getPortType() == portType) {
								return true;
							}
						}
					}
					break;
				}
				case NorthEast:
				{
					// Check N and NE Edges
					Hex h = this.Hexes.getHex(hexLoc);
					if (h.getHexType() == HexType.WATER) {
						WaterHex wh = (WaterHex) h;
						if (wh.getPortType() != null && 
							(wh.getDir() == EdgeDirection.North || wh.getDir() == EdgeDirection.NorthEast)) {
							if (wh.getPortType() == portType) {
								return true;
							}
						}
					}
					
					
					
					// Check (x-1, y) S and SE
					HexLocation hexLoc_ = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
					
					h = this.Hexes.getHex(hexLoc_);
					if (h.getHexType() == HexType.WATER) {
						WaterHex wh = (WaterHex) h;
						if (wh.getPortType() != null && 
							(wh.getDir() == EdgeDirection.South || wh.getDir() == EdgeDirection.SouthEast)) {
							if (wh.getPortType() == portType) {
								return true;
							}
						}
					}
					
	
					// Check (x+1,y-1) NW and SW
					hexLoc_ = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
					
					h = this.Hexes.getHex(hexLoc_);
					if (h.getHexType() == HexType.WATER) {
						WaterHex wh = (WaterHex) h;
						if (wh.getPortType() != null && 
							(wh.getDir() == EdgeDirection.SouthWest || wh.getDir() == EdgeDirection.NorthWest)) {
							if (wh.getPortType() == portType) {
								return true;
							}
						}
					}
					break;
				}
			}
		} catch (Exception e) {
		}
		
		return false;
	}
	
	
	
	/**
	 * Converts a ResourceType to its associated Port Type. null == PortType.THREE
	 * 
	 * @param t
	 * @return
	 */
	private PortType convertResourceTypeToPortType (ResourceType t) {
		try{
			switch(t)
			{
				case WOOD:
				{
					return PortType.WOOD;
				}
				case BRICK:
				{
					return PortType.BRICK;
				}
				case SHEEP: 
				{
					return PortType.SHEEP;
				}
				case WHEAT:
				{
					return PortType.WHEAT;
				}
				case ORE:
				{
					return PortType.ORE;
				}
				default:
				{
					return PortType.THREE;
				}
			
			}
		} catch(NullPointerException e) {
			return PortType.THREE;
		}
	}
	
	
	/**
	 * Helper function for canPlaceSettlement(). Verifies that all surrounding hexes of a vertex aren't Water
	 * 
	 * @return
	 * @throws InvalidTypeException 
	 */
	private boolean _canPlaceSettlement(VertexLocation vertexLoc) throws InvalidTypeException, IndexOutOfBoundsException {
		
		HexLocation hexLoc = vertexLoc.getHexLoc();
		
		switch (vertexLoc.getDir())
		{
			case NorthEast:
			{
				HexLocation bottomLeft = vertexLoc.getHexLoc();
				HexLocation right = vertexLoc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
				HexLocation topLeft = vertexLoc.getHexLoc().getNeighborLoc(EdgeDirection.North);
				
				Boolean allWater = true;
				
				try {
					if (this.Hexes.getHexType(bottomLeft) != HexType.WATER) {
						allWater = false;
					}
				} catch (IndexOutOfBoundsException e) { }
				
				try {
					if (this.Hexes.getHexType(right) != HexType.WATER) {
						allWater = false;
					}
				} catch (IndexOutOfBoundsException e) { }
				
				try {
					if (this.Hexes.getHexType(topLeft) != HexType.WATER) {
						allWater = false;
					}
				} catch (IndexOutOfBoundsException e) { }
				
				//If all the hexes surrounding this settlement are water, return false
				return !allWater;
			}
			case NorthWest:
			{
				HexLocation bottomRight = vertexLoc.getHexLoc();
				HexLocation left = vertexLoc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
				HexLocation topRight = vertexLoc.getHexLoc().getNeighborLoc(EdgeDirection.North);
				
				Boolean allWater = true;
				
				try {
					if (this.Hexes.getHexType(bottomRight) != HexType.WATER) {
						allWater = false;
					}
				} catch (IndexOutOfBoundsException e) { }
				
				try {
					if (this.Hexes.getHexType(left) != HexType.WATER) {
						allWater = false;
					}
				} catch (IndexOutOfBoundsException e) { }
				
				try {
					if (this.Hexes.getHexType(topRight) != HexType.WATER) {
						allWater = false;
					}
				}
				catch (IndexOutOfBoundsException e) { }
				
				//If all the hexes surrounding this settlement are water, return false
				return !allWater;
			}
			default:
			{
				throw new InvalidTypeException();
			}
		}
	}
	
	
	/**
	 * Checks if a Hexs Edge and it's neighbor are both of type WATER. If not, the edge is
	 * 	valid
	 * 
	 * @param edgeLoc
	 * @return
	 */
	private boolean isValidEdge(EdgeLocation edgeLoc) 
	{
		try {
			HexLocation hexLoc = edgeLoc.getHexLoc();
			if (this.Hexes.getHexType(hexLoc) != HexType.WATER) {
				return true;
			}
			else {
				hexLoc = hexLoc.getNeighborLoc(edgeLoc.getDir());
				if (this.Hexes.getHexType(hexLoc) != HexType.WATER) {
					return true;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}
	
	
	/**
	 * Converts a string representation of an EdgeDirection to its emunerated value
	 * 
	 * @param edgeDir
	 * @return
	 * @throws InvalidTypeException
	 */
	private EdgeDirection stringToEdgeDirection(String edgeDir) throws InvalidTypeException {
		
		switch (edgeDir)
		{
			case "NW":
				return EdgeDirection.NorthWest;
			case "N":
				return EdgeDirection.North;
			case "NE":
				return EdgeDirection.NorthEast;
			case "S":
				return EdgeDirection.South;
			case "SE":
				return EdgeDirection.SouthEast;
			case "SW":
				return EdgeDirection.SouthWest;
		}
		throw new InvalidTypeException();
	}
	

	/**
	 * Converts a string representation of a VertexDirection to its enumerated value
	 * 
	 * @param edgeDir
	 * @return
	 * @throws InvalidTypeException
	 */
	private VertexDirection stringToVertexDirection(String edgeDir) throws InvalidTypeException {
		
		switch (edgeDir) 
		{
			case "NW":
				return VertexDirection.NorthWest;
			case "E":
				return VertexDirection.East;
			case "NE":
				return VertexDirection.NorthEast;
			case "W":
				return VertexDirection.West;
			case "SE":
				return VertexDirection.SouthEast;
			case "SW":
				return VertexDirection.SouthWest;
		}
		throw new InvalidTypeException();
	}


	public int getMaritimeTradeRatio(int playerIndex, ResourceType type) {
		
		PortType portType = this.convertResourceTypeToPortType(type);
		int minRatio = 4;
		for(VertexLocation loc: this.PlayerPieces.getVertexPieceList(playerIndex)) {
			
			if (isOnPort(loc, portType)) {
				if (portType == PortType.THREE) {
					minRatio = Math.min(minRatio, 3);
				} else {
					minRatio = 2;
				}
			}
		}
		return minRatio;
	}


	public Boolean settlementTouchesPlayerRoad(VertexLocation loc, int ownerID) {
		return this.Edges.settlementTouchesPlayerRoad(loc, ownerID);
	}
	
	
	
	
	/*
	 * SERVER METHODS
	 */
	
	
	/**
	 * Creates a new map for a new game using parameters specified by the user.
	 * @param randomHexes tells whether to randomize the hexes in the new map.
	 * @param randomPorts tells whether to randomize the ports in the new map.
	 * @param randomNumbers tells whether to randomize the numbers on the hexes in the new map.
	 */
	public void createNewMap(boolean randomHexes, boolean randomPorts, boolean randomNumbers) {
		
	}
}
