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
	PortType Port;
	
	public WaterHex(HexType type) 
	{
		super(type);
	}

	
	public WaterHex(HexType type, PortType port) 
	{
		super(type, true);
		this.Port = port;
	}
	
	
	public boolean hasPort()
	{
		return this.IsOccupied;
	}

}
