package shared.models.mapClasses;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.RobberLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import shared.definitions.*;


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
	
	

	/* Constraints within the game board. Use for generating the board 
	private final int NUM_SEA = 18; // Number of sea pieces
	private final int NUM_HARBORS = 9; // Number of sea pieces with harbors
	private final int NUM_TERRAINS = 19; // Number of terrain pieces
	// Individual Terrain Types
	private final int NUM_ORE = 3;
	private final int NUM_GRAIN = 4;
	private final int NUM_LUMBER = 4;
	private final int NUM_WOOL = 4;
	private final int NUM_BRICK = 3;
	private final int NUM_DESERT = 1;
	// Token
	private final int[] TOKEN_VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private final int NUM_TOKENS = NUM_TERRAINS - 1;
	private final int NUM_TOKEN = 2;
	private final int NUM_TWO = 1;
	private final int NUM_TWELVE = 1;
	// Harbor
	private final int NUM_HARBOR = 1;
	private final int NUM_THREE = 4;*/
	
	
	
// CONSTRUCTORS
	public Map() {}
	
	
	/**
	 * Constructor generates a new map. Requires a String to be validated/parsed
	 */
	public Map(HexMap h, VertexMap v, EdgeMap e, RobberLocation r, PlayerMap playerPieces) 
	{
		this.Hexes = h;
		this.Vertexes = v;
		this.Edges = e;
		this.Robber = r;
		this.PlayerPieces = (playerPieces == null) ? new PlayerMap() : playerPieces;
	}
	
	
	public void setHexMap(HexMap hexes)
	{
		this.Hexes = hexes;
	}

	
	public void setEdgeMap(EdgeMap edges)
	{
		this.Edges = edges;
	}
	
	
	public void setVertexMap(VertexMap vertexes)
	{
		this.Vertexes = vertexes;
	}
	
	
	public void setRobberLocation(RobberLocation robber)
	{
		this.Robber = robber;
	}
	
	
	public void setPlayerMap(PlayerMap playerPieces)
	{
		this.PlayerPieces = playerPieces;
	}
	
	

// Public METHODS
	/**
	 * Moves a robber to a given HexLocation
	 *  Constraints: must not already be there, cannot be a sea piece
	 * 
	 * @param location
	 */
	public boolean canPlaceRobber(int x, int y)
	{
		HexLocation loc = new HexLocation(x,y);
		if (!this.Robber.canPlaceRobber(loc))
		{
			return false;
		}
		
		if (!this.Hexes.canPlaceRobber(loc))
		{
			return false;
		}
		return true;
	}
	
	
	/**
	 * Check if player can place a road at a given EdgeLocation
	 *  Constraints: Must be adjacent to player's existing roads, cannot be in between two WaterHexs
	 * 
	 * @param location
	 */
	public boolean canPlaceRoad(int x, int y, String edgeDir, int ownerIndex)
	{
		try {
			EdgeLocation edgeLoc = (new EdgeLocation(
				new HexLocation(x, y), 
				this.stringToEdgeDirection(edgeDir)
			)).getNormalizedLocation();
			return this.isValidEdge(edgeLoc) && this.Edges.canBuildRoad(edgeLoc, ownerIndex);
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch (InvalidTypeException e){
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
	public boolean canPlaceSettlement(int x, int y, String vertexDir, int ownerIndex) 
	{
		try {
			VertexLocation vertexLoc = (new VertexLocation(
				new HexLocation(x, y),
				this.stringToVertexDirection(vertexDir)
			)).getNormalizedLocation();
			return this._canPlaceSettlement(vertexLoc) && this.Vertexes.canPlaceSettlement(vertexLoc, ownerIndex);
		} catch (IndexOutOfBoundsException e)
		{
			return false;
		} catch (InvalidTypeException e)
		{
			return false;
		}
	}
	
	
	/**
	 * Check if player can place a city at a given VertexLocation
	 *  Constrains: Must be adjacent to a player's existing roads and must replace a settlement.
	 * 
	 * @param location
	 */
	public boolean canPlaceCity(int x, int y, String direction, int ownerIndex)
	{
		try {
			VertexLocation loc = new VertexLocation(new HexLocation(x, y), stringToVertexDirection(direction));
			return this.Vertexes.canPlaceCity(loc, ownerIndex);
		} catch (IndexOutOfBoundsException e)
		{
			return false;
		} catch (InvalidTypeException e)
		{
			return false;
		}
	}
	
	
	/**
	 * Checks if a user can maritime trade.
	 * 
	 * @param ownerIndex
	 * @return
	 */
	public boolean canMaritimeTrade(int ownerIndex, ResourceType type)
	{
		PortType portType = this.convertResourceTypeToPortType(type);
		
		for(VertexLocation loc: this.PlayerPieces.getVertexPieceList(ownerIndex))
		{
			if (isOnPort(loc, portType))
			{
				return true;
			}
		}
		return false;
	}
	
	
	
// Private METHOD
	private boolean isOnPort(VertexLocation loc, PortType portType) 
	{
		HexLocation hexLoc = loc.getHexLoc();
		
		switch (loc.getDir())
		{
			case NorthWest:
			{
				// Check N and NW Edges
				Hex h = this.Hexes.getHex(hexLoc);
				if (h.getHexType() == HexType.WATER)
				{
					WaterHex wh = (WaterHex) h;
					if (wh.getPortType() != null && 
						(wh.getDir() == EdgeDirection.North || wh.getDir() == EdgeDirection.NorthWest))
					{
						return true;
					}
				}
				

				// Check (x-1,y) SE and NE
				HexLocation hexLoc_ = new HexLocation(hexLoc.getX()-1, hexLoc.getY());
				
				h = this.Hexes.getHex(hexLoc_);
				if (h.getHexType() == HexType.WATER)
				{
					WaterHex wh = (WaterHex) h;
					if (wh.getPortType() != null && 
						(wh.getDir() == EdgeDirection.SouthEast || wh.getDir() == EdgeDirection.NorthEast))
					{
						return true;
					}
				}
				
				
				
				// Check (x, y-1) S and SW
				hexLoc_ = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
				
				h = this.Hexes.getHex(hexLoc_);
				if (h.getHexType() == HexType.WATER)
				{
					WaterHex wh = (WaterHex) h;
					if (wh.getPortType() != null && 
						(wh.getDir() == EdgeDirection.South || wh.getDir() == EdgeDirection.SouthWest))
					{
						return true;
					}
				}
				break;
			}
			case NorthEast:
			{
				// Check N and NE Edges
				Hex h = this.Hexes.getHex(hexLoc);
				if (h.getHexType() == HexType.WATER)
				{
					WaterHex wh = (WaterHex) h;
					if (wh.getPortType() != null && 
						(wh.getDir() == EdgeDirection.North || wh.getDir() == EdgeDirection.NorthEast))
					{
						return true;
					}
				}
				
				
				
				// Check (x-1, y) S and SE
				HexLocation hexLoc_ = new HexLocation(hexLoc.getX(), hexLoc.getY()-1);
				
				h = this.Hexes.getHex(hexLoc_);
				if (h.getHexType() == HexType.WATER)
				{
					WaterHex wh = (WaterHex) h;
					if (wh.getPortType() != null && 
						(wh.getDir() == EdgeDirection.South || wh.getDir() == EdgeDirection.SouthEast))
					{
						return true;
					}
				}
				

				// Check (x+1,y-1) NW and SW
				hexLoc_ = new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1);
				
				h = this.Hexes.getHex(hexLoc_);
				if (h.getHexType() == HexType.WATER)
				{
					WaterHex wh = (WaterHex) h;
					if (wh.getPortType() != null && 
						(wh.getDir() == EdgeDirection.SouthWest || wh.getDir() == EdgeDirection.NorthWest))
					{
						return true;
					}
				}
				break;
			}
		}
		
		
		
		return false;
	}
	
	
	
	private PortType convertResourceTypeToPortType (ResourceType t)
	{
		PortType portType = null;
		switch(t)
		{
			case WOOD:
			{
				portType = PortType.WOOD;
				break;
			}
			case BRICK:
			{
				portType = PortType.BRICK;
				break;
			}
			case SHEEP: 
			{
				portType = PortType.SHEEP;
				break;
			}
			case WHEAT:
			{
				portType = PortType.WHEAT;
				break;
			}
			case ORE:
			{
				portType = PortType.ORE;
				break;
			}
			default:
			{
				portType = PortType.THREE;
				break;
			}
		
		}
		return portType;
	}
	
	
	/**
	 * Helper function for canPlaceSettlement(). Verifies that all surrounding hexes of a vertex aren't Water
	 * 
	 * @return
	 * @throws InvalidTypeException 
	 */
	private boolean _canPlaceSettlement(VertexLocation vertexLoc) throws InvalidTypeException, IndexOutOfBoundsException
	{
		HexLocation hexLoc = vertexLoc.getHexLoc();
		if (this.Hexes.getHex(hexLoc).getHexType() != HexType.WATER)
		{
			return true;
		}
		
		switch (vertexLoc.getDir())
		{
			case NorthEast:
			{
				// -1, 0 ; 0, -1
				if (this.Hexes.getHex(new HexLocation(hexLoc.getX()-1, hexLoc.getY())) == null ||
					this.Hexes.getHex(new HexLocation(hexLoc.getX(), hexLoc.getY()-1)) == null)
				{
					return false;
				}
				break;
			}
			case NorthWest:
			{
				// 0, -1 ; 1, -1
				if (this.Hexes.getHex(new HexLocation(hexLoc.getX(), hexLoc.getY()-1)) == null ||
						this.Hexes.getHex(new HexLocation(hexLoc.getX()+1, hexLoc.getY()-1)) == null)
					{
						return false;
					}
				break;
			}
			default:
			{
				throw new InvalidTypeException();
			}
		}
		return true;
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
			if (this.Hexes.getHexType(hexLoc) != HexType.WATER)
			{
				return true;
			}
			else 
			{
				hexLoc = hexLoc.getNeighborLoc(edgeLoc.getDir());
				return this.Hexes.getHexType(hexLoc) != HexType.WATER;
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	
	/**
	 * Converts a string representation of an EdgeDirection to its emunerated value
	 * 
	 * @param edgeDir
	 * @return
	 * @throws InvalidTypeException
	 */
	private EdgeDirection stringToEdgeDirection(String edgeDir) throws InvalidTypeException
	{
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
	private VertexDirection stringToVertexDirection(String edgeDir) throws InvalidTypeException
	{
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

}
