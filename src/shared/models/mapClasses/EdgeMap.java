package shared.models.mapClasses;

import java.util.HashMap;
import java.util.Map;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

public class EdgeMap {
	
	private Map<EdgeLocation, Piece> Edges = new HashMap<EdgeLocation, Piece>();
	
	
	
	/**
	 * Constructor
	 * 
	 * @param json
	 */
	public EdgeMap(String json)
	{
		
	}
	
	
	/**
	 * Set the Piece at a Given EdgeDirection
	 * 
	 * @param loc
	 * @param p
	 */
	public void setEdge(EdgeLocation loc, Piece p)
	{
		this.Edges.put(loc, p);
	}
	
	
	/**
	 * Return the Piece at the given EdgeLocation
	 * 
	 * @param loc
	 * @return
	 */
	public Piece getEdge(EdgeLocation loc)
	{
		return Edges.get(loc);
	}

}
