package shared.models.mapClasses;

import java.util.HashMap;
import java.util.Map;

import shared.definitions.PieceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class VertexMap 
{
	private Map<VertexLocation, Piece> Vertexes = new HashMap<VertexLocation, Piece>();
	
	
	
// CONSTRUCTOR
	public VertexMap() {}

	
	
// GETTER
	/**
	 * Get the Piece at a given VertexDirection
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Piece getPiece(VertexLocation loc) throws IndexOutOfBoundsException
	{
		if (!Vertexes.containsKey(loc.getNormalizedLocation()))
		{
			throw new IndexOutOfBoundsException();
		}
		return Vertexes.get(loc.getNormalizedLocation());
	}

	
	
// SETTER
	/**
	 * Set the Piece at the given VertexLocation
	 * 
	 * @param loc
	 * @param p
	 */
	public void setVertex(VertexLocation loc, Piece p) throws InvalidTypeException
	{
		if (p.getType() == PieceType.ROAD)
		{
			throw new InvalidTypeException();
		}
		this.Vertexes.put(loc.getNormalizedLocation(), p);
	}
	
	

// METHODS
	/**
	 * Can do method for checking if a player can place a settlement
	 * 
	 * @param loc
	 * @param ownerId
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public boolean canPlaceCity(VertexLocation loc, int ownerId) throws IndexOutOfBoundsException
	{
		loc = loc.getNormalizedLocation();
		if (!this.Vertexes.containsKey(loc))
		{
			throw new IndexOutOfBoundsException();
		}
		if (this.Vertexes.get(loc) == null)
		{
			return false;
		}
		
		Piece p = this.Vertexes.get(loc);
		if (p.getOwner() == ownerId && p.getType() == PieceType.SETTLEMENT)
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * Can do method for checking if a player can place a settlement
	 * 
	 * @param loc
	 * @param ownerId
	 * @return
	 * @throws IndexOutOfBoundsException
	 * @throws InvalidTypeException 
	 */
	public boolean canPlaceSettlement(VertexLocation loc, int ownerId) throws IndexOutOfBoundsException, InvalidTypeException
	{
		loc = loc.getNormalizedLocation();
		if (!this.Vertexes.containsKey(loc))
		{
			throw new IndexOutOfBoundsException();
		}
		if (this.Vertexes.get(loc) == null)
		{
			return this._canPlaceSettlement(loc, ownerId);
		}
		return true;
	}
	
	
	/**
	 * Helper method for canPlaceSettlement. Checks vertexes that are 1 away from the given location. Expects that loc is the
	 * 	normalized location
	 *  
	 * 
	 * @param loc
	 * @param ownerId
	 * @return
	 * @throws IndexOutOfBoundsException
	 * @throws InvalidTypeException 
	 */
	private boolean _canPlaceSettlement(VertexLocation loc, int ownerId) throws IndexOutOfBoundsException, InvalidTypeException
	{
		switch(loc.getDir())
		{
			case NorthEast:
			{
				if (!this.checkLocation(VertexDirection.NorthWest, loc.getHexLoc()) && 
						!this.checkLocation(VertexDirection.East, loc.getHexLoc()) &&
						!this.checkLocation(VertexDirection.NorthWest, loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast)))
				{
					return false;
				}
				break;
			}
			case NorthWest:
			{
				if (!this.checkLocation(VertexDirection.West, loc.getHexLoc()) && 
						!this.checkLocation(VertexDirection.NorthEast, loc.getHexLoc()) &&
						!this.checkLocation(VertexDirection.NorthWest, loc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest)))
				{
					return false;
				}
				break;
			}
			default:
				throw new InvalidTypeException();
		}
		return true;
	}
	
	
	/**
	 * Check's if the given location
	 * 
	 * @param dir
	 * @param hexLoc
	 * @return
	 */
	private boolean checkLocation(VertexDirection dir, HexLocation hexLoc)
	{
		VertexLocation new_loc = (new VertexLocation(hexLoc, dir)).getNormalizedLocation();
		if (this.Vertexes.containsKey(new_loc) && this.Vertexes.get(new_loc) != null)
		{
			return false;
		}
		return true;
	}
	
}
