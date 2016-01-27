package shared.models.mapClasses;

import java.util.HashMap;
import java.util.Map;
import shared.locations.VertexLocation;

public class VertexMap 
{
	private Map<VertexLocation, Piece> Vertexes = new HashMap<VertexLocation, Piece>();
	
	
	
	public VertexMap() {}
	
	
	/**
	 * Check if a Vertex is occupied by a settlement or city
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public boolean isSettled(VertexLocation loc) throws IndexOutOfBoundsException
	{
		return true;
	}
	
	
	/**
	 * Check if a Vertex is occupied by a settlement
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public boolean isSettlement(VertexLocation loc) throws IndexOutOfBoundsException
	{
		return true;
	}
	
	
	/**
	 * Check if a Vertex is occupied by a settlement
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public boolean isCity(VertexLocation loc) throws IndexOutOfBoundsException
	{
		return true;
	}
	
	
	/**
	 * Get the Piece at a given VertexDirection
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Piece getPiece(VertexLocation loc) throws IndexOutOfBoundsException
	{
		return Vertexes.get(loc);
	}
	
	
	/**
	 * Set the Piece at the given VertexLocation
	 * 
	 * @param loc
	 * @param p
	 * @throws IndexOutOfBoundsException
	 */
	public void setVertex(VertexLocation loc, Piece p) throws IndexOutOfBoundsException
	{
		
	}
	
}
