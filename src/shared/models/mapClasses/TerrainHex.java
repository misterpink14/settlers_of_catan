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
	

// CONSTRUTOR
	/**
	 * Constructor. HexType cannot be WATER. Token must be a valid number.
	 * 
	 * @param type
	 * @param token
	 * @throws InvalidHexTypeException
	 * @throws InvalidTokenException 
	 * @throws InvalidTypeException 
	 */
	public TerrainHex(HexType type, int token) throws InvalidTokenException, InvalidTypeException
	{
		super(type);
		if (type == HexType.WATER)
		{
			throw new InvalidTypeException();
		}
		if (token < 2 || token > 12)
		{
			throw new InvalidTokenException();
		}
		this.Token = token;
	}
	
	
	
// GETTER
	public int getToken()
	{
		return this.Token;
	}

}
