package shared.models.mapClasses;

import shared.definitions.HexType;

/**
 * This class is a representation of a hex piece on the settlers of catan map. 
 *  It is one of 34.
 *  
 * @author benthompson
 *
 */
public abstract class Hex 
{
	/**The Hex's type*/
	private HexType Type;

	

// CONSTRUCTOR
	/**
	 * This class is a representation of a hex piece on the settlers of catan map. 
	 *  It is one of 34.
	 * 
	 * @param type
	 */
	public Hex(HexType type) 
	{
		this.Type = type;
	}
	
	

// GETTER
	/**
	 * Get the HexType of the Hex
	 * 
	 * @return
	 */
	public HexType getHexType() 
	{
		return this.Type;
	}

}
