package shared.models.mapClasses;

import java.util.HashMap;
import java.util.Map;

import shared.definitions.PieceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

public class EdgeMap { 
	
	private Map<EdgeLocation, Piece> Edges = new HashMap<EdgeLocation, Piece>();
	
	
	
// CONSTRUCTOR
	/**
	 * Constructor
	 * 
	 * @param json
	 */
	public EdgeMap() {}
	
	
	
// GETTER
	/**
	 * Return the Piece at the given EdgeLocation
	 * 
	 * @param loc
	 * @return
	 */
	public Piece getEdge(EdgeLocation loc) throws IndexOutOfBoundsException
	{
		loc = loc.getNormalizedLocation();
		if (!Edges.containsKey(loc))
		{
			throw new IndexOutOfBoundsException();
		}
		return Edges.get(loc);
	}
	
	
	
// SETTER
	/**
	 * Set an Edge at a Given EdgeDirection, use null for empty
	 * 
	 * @param loc
	 * @param p
	 */
	public void setEdge(EdgeLocation loc, Piece p) throws InvalidTypeException
	{
		loc = loc.getNormalizedLocation();
		if (p.getType() != PieceType.ROAD)
		{
			throw new InvalidTypeException();
		}
		this.Edges.put(loc, p);
	}
	
	
	
// Public METHOD
	/**
	 * Checks if a player can build a road at a given location
	 * 
	 * @param loc
	 * @param ownerId
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public boolean canBuildRoad(EdgeLocation loc, int ownerId) throws IndexOutOfBoundsException
	{
		// check if outside edge
		loc = loc.getNormalizedLocation();
		if (!this.Edges.containsKey(loc))
		{
			throw new IndexOutOfBoundsException();
		}
		if (this.Edges.get(loc) == null)
		{
			return this._canBuildRoad(loc, ownerId);
		}
		return false;
	}
	
	
	/**
	 * Checks if a player can build a settlement
	 * 
	 * @return
	 */
	public boolean canBuildSettlement(EdgeLocation edgeLoc, int ownerId)
	{
		if (!this.Edges.containsKey(edgeLoc))
		{
			throw new IndexOutOfBoundsException();
		}
		
		if (this.Edges.get(edgeLoc).getOwner() != ownerId)
		{
			return false;
		}
		return true; // TODO: this
	}
	

// Private METHODS
	/**
	 * Helper function for canBuildRoad
	 * 
	 * @param loc
	 * @param ownerId
	 * @return
	 */
	private boolean _canBuildRoad(EdgeLocation loc, int ownerId)
	{
		EdgeLocation new_loc; // Used for checking if neighbor Edges belong to the player
		EdgeDirectionPair pair = getNeighborDirection(loc.getDir());
		EdgeDirection dir1 = pair.Dir1;
		EdgeDirection dir2 = pair.Dir2;
		
		
		/* Check if any adjacent edge belongs to the player trying to place the road piece */
		// Check dir1
		new_loc = (new EdgeLocation(loc.getHexLoc(), dir1)).getNormalizedLocation();
		if (this.Edges.containsKey(new_loc) && this.Edges.get(new_loc).getOwner() == ownerId)
		{
			return true;
		}

		// Check dir2
		new_loc = (new EdgeLocation(loc.getHexLoc(), dir2)).getNormalizedLocation();
		if (this.Edges.containsKey(new_loc) && this.Edges.get(new_loc).getOwner() == ownerId)
		{
			return true;
		}

		// Check adjacent hex with opposite of dir1
		new_loc = new EdgeLocation(
				loc.getHexLoc().getNeighborLoc(loc.getDir()), 
				dir1.getOppositeDirection()
		).getNormalizedLocation();
		if (this.Edges.containsKey(new_loc) && this.Edges.get(new_loc).getOwner() == ownerId)
		{
			return true;
		}

		// Check adjacent hex with opposite of dir2
		new_loc = new EdgeLocation(
				loc.getHexLoc().getNeighborLoc(loc.getDir()), 
				dir2.getOppositeDirection()
		).getNormalizedLocation();
		if (this.Edges.containsKey(new_loc) && this.Edges.get(new_loc).getOwner() == ownerId)
		{
			return true;
		}

		// None of the adjacent edges match
		return false;
		
	}
	
	
	/**
	 * Helper function for _canBuildRoad. Gets the neighbors of the given EdgeDirection (for a Hex)
	 * 
	 * @param dir
	 * @return
	 */
	private EdgeDirectionPair getNeighborDirection(EdgeDirection dir)
	{
		EdgeDirection dir1 = null;
		EdgeDirection dir2 = null;
		
		switch (dir) 
		{
			case NorthWest:
			{
				// Check North and adjacent hex South
				dir1 = EdgeDirection.North;
				// Check SouthWest and adjacent hex NorthEast
				dir2 = EdgeDirection.SouthWest;
				break;
			}
			case North:
			{
				// Check NorthWest and adjacent hex SouthEast
				dir1 = EdgeDirection.NorthWest;
				// Check NorthEast and adjacent hex SouthWest
				dir2 = EdgeDirection.NorthEast;
				break;
			}
			case NorthEast: 
			{
				// Check North and adjacent hex South
				dir1 = EdgeDirection.North;
				// Check SouthEast and adjacent hex NorthWest
				dir2 = EdgeDirection.SouthWest;
				break;
			}
			default:
			{
				assert false;
				return null;
			}
		}
		return new EdgeDirectionPair(dir1, dir2);
	}
	
	
	
	/**
	 * Container for return data of getNeighborDirection. Reduces code duplication
	 * 
	 * @author benthompson
	 *
	 */
	class EdgeDirectionPair 
	{
		EdgeDirection Dir1;
		EdgeDirection Dir2;
		
		EdgeDirectionPair(EdgeDirection dir1, EdgeDirection dir2)
		{
			Dir1 = dir1;
			Dir2 = dir2;
		}
	}
	
}
