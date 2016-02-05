package shared.models.mapClasses;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.VertexDirection;

/**
 * Represents a WaterHex piece located within the Map class
 * 
 * @author benthompson
 *
 */
public class WaterHex extends Hex
{
	PortType Port = null;
	VertexDirection Dir;
	
	
// CONSTRUCTOR
	/**
	 * Constructor. Pass in null for no port.
	 * 
	 * @param type
	 * @param port
	 */
	public WaterHex(PortType port, VertexDirection dir) 
	{
		super(HexType.WATER);
		this.Port = port;
		this.Dir = dir;
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
	
	
	public VertexDirection getDir()
	{
		return this.Dir;
	}
	
	
// PUBLIC METHOD
	/**
	 * Checks if this Water Hex is a Port or not
	 * 
	 * @return
	 */
	public boolean isPort(VertexDirection dir)
	{
		if (Port == null)
		{
			return false;
		}
		else
		{
			return Dir.equals(dir);
		}
	}

}
