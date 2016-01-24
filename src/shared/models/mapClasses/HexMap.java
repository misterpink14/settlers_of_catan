package shared.models.mapClasses;

import java.util.HashMap;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public class HexMap 
{
	private HashMap<HexLocation, Hex> Hexes = new HashMap<HexLocation, Hex>();
	
	
	
	/**
	 * Constructor
	 * 
	 * @param json
	 */
	public HexMap(String json) 
	{
		
	}
	
	
	/**
	 * Returns the Hex at a given location
	 * 
	 * @param loc
	 * @return
	 */
	public Hex getHex(HexLocation loc)
	{
		return Hexes.get(loc);
	}
	
	
	/**
	 * Returns the Hex at a given location
	 * 
	 * @param loc
	 * @return
	 */
	public void setHex()
	{

	}
	
	
	/**
	 * Returns the Hex Type of a given HexLocation
	 * 
	 * @param loc
	 * @return
	 */
	public HexType getHexType(HexLocation loc)
	{
		return Hexes.get(loc).getType();
	}

}
