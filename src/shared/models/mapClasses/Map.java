package shared.models.mapClasses;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.RobberLocation;
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
	 *  The most center hex, in both x and y, is 0 with negative values to the left and positive values to the right
	 */
	private HexMap Hexes = new HexMap();
	private VertexMap Vertexes = new VertexMap();
	private EdgeMap Edges = new EdgeMap();
	private RobberLocation Robber = new RobberLocation(null);
	

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
	
	
	
// PUBLIC
	/**
	 * Constructor generates a new map. Requires a String to be validated/parsed
	 */
	public Map() 
	{
	}
	

	/**
	 * Moves a robber to a given HexLocation
	 *  Constraints: must not already be there, cannot be a sea piece
	 * 
	 * @param location
	 */
	public boolean canPlaceRobber(HexLocation loc)
	{
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
	 *  Constraints: Must be adjacent to player's existing roads
	 * 
	 * @param location
	 */
	public boolean canPlaceRoad(EdgeLocation loc)
	{
		return true;
	}
	
	
	/**
	 * Check if player can place a settlement at a given VertexLocation
	 *  Constrains: Must be adjacent to a player's existing roads
	 * 
	 * @param location
	 */
	public boolean canPlaceSettlement(VertexLocation loc) 
	{
		return true;
	}
	
	
	/** 
	 * Check if player can place a city at a given VertexLocation
	 *  Constrains: Must be adjacent to a player's existing roads and must replace a settlement
	 * 
	 * @param location
	 */
	public boolean canPlaceCity(VertexLocation loc)
	{
		return true;
	}
	
	
	
	public boolean canMaritimeTrade()
	{
		boolean maybe = true;
		return maybe;
	}
	
}
