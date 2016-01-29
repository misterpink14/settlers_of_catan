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
	
	
// CONSTRUCTOR
	/**
	 * Constructor. Pass in null for no port.
	 * 
	 * @param type
	 * @param port
	 */
	public WaterHex(PortType port) 
	{
		super(HexType.WATER);
		this.Port = port;
	}
	
	
	
// GETTER
	/**
	 * Returns the Port Type of the Water Hex
	 * 
	 * @return
	 */
	public PortType getPortType() 
	{
		return this.Port;
	}
	
	
// PUBLIC METHOD
	/**
	 * Checks if this Water Hex is a Port or not
	 * 
	 * @return
	 */
	public boolean isPort()
	{
		return Port != null;
	}

}
