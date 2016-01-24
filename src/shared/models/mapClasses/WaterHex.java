package shared.models.mapClasses;

import shared.definitions.HexType;
import shared.definitions.PortType;

/**
 * Represents a WaterHex piece located within the Map class
 * 
 * @author benthompson
 *
 */
public class WaterHex extends Hex
{
	PortType Port = null;
	
	public WaterHex(HexType type) 
	{
		super(type);
	}

	
	/**
	 * Constructor
	 * 
	 * @param type
	 * @param port
	 */
	public WaterHex(PortType port) 
	{
		super(HexType.WATER);
		this.Port = port;
	}
	
	
	/**
	 * Checks if this Water Hex is a Port or not
	 * 
	 * @return
	 */
	public boolean isPort()
	{
		return Port != null;
	}
	
	
	/**
	 * Returns the Port Type of the Water Hex
	 * 
	 * @return
	 */
	public PortType getPortType() 
	{
		return this.Port;
	}

}
