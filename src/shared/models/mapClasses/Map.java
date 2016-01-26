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
	private HexMap Hexes = new HexMap("");
	private VertexMap Vertexes = new VertexMap("");
	private EdgeMap Edges = new EdgeMap("");
	private RobberLocation Robber = new RobberLocation(null);
	

	/* Constraints within the game board. Use for generating the board */
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
	private final int NUM_THREE = 4;
	
	
	
// PUBLIC
	/**
	 * Constructor generates a new map. Requires a String to be validated/parsed
	 */
	public Map(String json) 
	{
		this.generateMap(json);
	}
	
	
	/**
	 * Gets a Hex piece at a given location
	 * 
	 * @return
	 */
	public Hex getHex(HexLocation loc)
	{
		return Hexes.getHex(loc);
	}
	
	
	/**
	 * Create a new game board, using all 34 Hex pieces, the robber is placed on the desert as well.
	 */
	private void generateMap(String json) 
	{
		
	}
	

	/**
	 * Places a robber to a given HexLocation
	 * 
	 * @param location
	 */
	public void placeRobber(HexLocation location)
	{
		
	}
	
	
	/**
	 * Places a road at a given EdgeLocation
	 * 
	 * @param location
	 */
	public void placeRoad(EdgeLocation location)
	{
		
	}
	
	
	/**
	 * Places a settlement at a given EdgeLocation
	 * 
	 * @param location
	 */
	public void placeSettlement(VertexLocation location) 
	{
		
	}
	
	
	/** 
	 * Places a city at a given EdgeLocation
	 * 
	 * @param location
	 */
	public void placeCity(VertexLocation location)
	{
		
	}
	

	/**
	 * Moves a robber to a given HexLocation
	 *  Constraints: must not already be there, cannot be a sea piece
	 * 
	 * @param location
	 */
	public void canPlaceRobber(HexLocation location)
	{
		
	}
	
	
	/**
	 * Check if player can place a road at a given EdgeLocation
	 *  Constraints: Must be adjacent to player's existing roads
	 * 
	 * @param location
	 */
	public void canPlaceRoad(EdgeLocation location)
	{
		
	}
	
	
	/**
	 * Check if player can place a settlement at a given VertexLocation
	 *  Constrains: Must be adjacent to a player's existing roads
	 * 
	 * @param location
	 */
	public void canPlaceSettlement(VertexLocation location) 
	{
		
	}
	
	
	/** 
	 * Check if player can place a city at a given VertexLocation
	 *  Constrains: Must be adjacent to a player's existing roads and must replace a settlement
	 * 
	 * @param location
	 */
	public void canPlaceCity(VertexLocation location)
	{
		
	}
	
	
	/**
	 * Give the ResourceType associated with the Hex
	 * 
	 * @param location
	 * @return
	 */
	public ResourceType getResourceType(HexLocation location)
	{
		return ResourceType.WHEAT;
	}

}