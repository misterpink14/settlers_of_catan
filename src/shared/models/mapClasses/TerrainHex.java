package shared.models.mapClasses;

import shared.definitions.HexType;

/**
 * Represents a TerrainHex piece located within the Map class
 * 
 * @author benthompson
 *
 */
public class TerrainHex extends Hex
{
	private int Token;
	
	

	public TerrainHex(HexType type, int token) 
	{
		super(type);
		this.Token = token;
	}


	public TerrainHex(HexType type, int token, boolean hasRobber) 
	{
		super(type, hasRobber);
		this.Token = token;
	}
	
	
	public int getToken()
	{
		return this.Token;
	}
	
	
	/**
	 * Checks if the Terrain Hex has the Robber in it's location
	 * 
	 * @return
	 */
	public boolean hasRobber()
	{
		return this.IsOccupied;
	}

}
