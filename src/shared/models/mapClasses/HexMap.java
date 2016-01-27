package shared.models.mapClasses;

import java.util.HashMap;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class HexMap 
{
	private HashMap<HexLocation, Hex> Hexes = new HashMap<HexLocation, Hex>();
	
	
	
	public HexMap() {}
	
	
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
	
	
	/**
	 * Returns the Hex at a given location
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public void setHex(HexLocation loc, Hex hex)
	{

	}
	
	
	/**
	 * Returns the Hex Type of a given HexLocation
	 * 
	 * @param loc
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public HexType getHexType(HexLocation loc) throws IndexOutOfBoundsException
	{
		return Hexes.get(loc).getType();
	}

}
