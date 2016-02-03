package shared.models.mapClasses;

import java.util.HashMap;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class HexMap 
{
	private HashMap<HexLocation, Hex> Hexes = new HashMap<HexLocation, Hex>();
	
	
	
// CONSTRUCTOR
	public HexMap() {}
	
	
// GETTER
	/**
	 * Returns the Hex at a given location
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Hex getHex(HexLocation loc) throws IndexOutOfBoundsException
	{
		return Hexes.get(loc);
	}
	
	
// SETTER
	/**
	 * Returns the Hex at a given location
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public void setHex(HexLocation loc, Hex hex)
	{
		this.Hexes.put(loc, hex);
	}
	
	
// METHODS
	public boolean canPlaceRobber(HexLocation loc)
	{
		if (this.getHexType(loc) == HexType.WATER)
		{
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * Returns the Hex Type of a given HexLocation
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	private HexType getHexType(HexLocation loc) throws IndexOutOfBoundsException
	{
		return Hexes.get(loc).getHexType();
	}
	
	

}
