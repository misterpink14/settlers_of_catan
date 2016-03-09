package shared.models.mapClasses;

import java.util.ArrayList;

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
	 */
	public Map(HexMap h, VertexMap v, EdgeMap e, RobberLocation r, PlayerMap playerPieces) {
		
		this.Hexes = h;
		this.Vertexes = v;
		this.Edges = e;
		this.Robber = r;
		this.PlayerPieces = (playerPieces == null) ? new PlayerMap() : playerPieces;
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
	
	public RobberLocation getRobberLocation() {
		return this.Robber;
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
	 * Use to add a settlement to the player map
	 * 
	 * @param loc
	 * @param playerIndex
	 */
	public void addSettlementToPlayerMap(VertexLocation loc, int playerIndex) {
		this.PlayerPieces.addSettlement(loc, playerIndex);
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

}
