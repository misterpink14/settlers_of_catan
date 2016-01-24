package shared.models.mapClasses;

import java.util.HashMap;
import java.util.Map;
import shared.locations.VertexLocation;

public class VertexMap 
{
	private Map<VertexLocation, Piece> Vertexes = new HashMap<VertexLocation, Piece>();
	
	
	
	/**
	 * Constructor
	 * 
	 * @param json
	 */
	public VertexMap(String json)
	{
		
	}
	
	
	/**
	 * Check if a Vertex is occupied by a settlement or city
	 * 
	 * @param loc
	 * @return
	 */
	public boolean isSettled(VertexLocation loc)
	{
		return true;
	}
	
	
	/**
	 * Check if a Vertex is occupied by a settlement
	 * 
	 * @param loc
	 * @return
	 */
	public boolean isSettlement(VertexLocation loc)
	{
		return true;
	}
	
	
	/**
	 * Check if a Vertex is occupied by a settlement
	 * 
	 * @param loc
	 * @return
	 */
	public boolean isCity(VertexLocation loc)
	{
		return true;
	}
	
	
	/**
	 * Get the Piece at a given VertexDirection
	 * 
	 * @param loc
	 * @return
	 */
	public Piece getPiece(VertexLocation loc)
	{
		return Vertexes.get(loc);
	}
	
	
	/**
	 * Set the Piece at the given VertexLocation
	 * 
	 * @param loc
	 * @param p
	 */
	public void setVertex(VertexLocation loc, Piece p)
	{
		
	}
	
}
