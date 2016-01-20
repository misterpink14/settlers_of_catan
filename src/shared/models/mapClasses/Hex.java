package shared.models.mapClasses;

import java.util.HashMap;
import java.util.Map;

import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.locations.EdgeDirection;
import shared.locations.VertexDirection;

/**
 * This class is a representation of a hex piece on the settlers of catan map. 
 *  It is one of 34.
 *  
 * @author benthompson
 *
 */
public abstract class Hex 
{
	/**The Hex's type*/
	private HexType Type;
	private Map<EdgeDirection, Piece> Edges = new HashMap<EdgeDirection, Piece>();
	private Map<VertexDirection, Piece> Vertexes = new HashMap<VertexDirection, Piece>();
	
	/**
	 * This boolean is true when the a piece has been placed on this tile.
	 */
	protected boolean IsOccupied = false;

	
	/**
	 * This class is a representation of a hex piece on the settlers of catan map. 
	 *  It is one of 34.
	 */
	public Hex(HexType type) 
	{
		this.Type = type;
	}
	
	
	/**
	 * This class is a representation of a tile on the settlers of catan map. 
	 * It is one of 34.
	 */
	public Hex(HexType type, boolean isOccupied) 
	{
		this.Type = type;
		this.IsOccupied = isOccupied;
	}
	
	
	/**
	 * Get the HexType of the Hex
	 * 
	 * @return
	 */
	public HexType getType() 
	{
		return this.Type;
	}
	
	
	/**
	 * Add an edge to the Hex
	 * 
	 * @param edge
	 * @param pieceType
	 */
	public void addEdge(EdgeDirection edge, PieceType pieceType)
	{
		
	}
	
	
	/**
	 * Add a vertex to the Hex
	 * 
	 * @param edge
	 * @param pieceType
	 */
	public void addVertex(VertexDirection edge, PieceType pieceType)
	{
		
	}

}
