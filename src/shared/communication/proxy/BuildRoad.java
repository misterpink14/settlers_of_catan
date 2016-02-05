package shared.communication.proxy;

import shared.locations.EdgeLocation;

public class BuildRoad
{

	/**
	 * The index of the player sending the message
	 */
	public int playerIndex;
	
	/**
	 * Whether or not this is a free placement
	 */
	public Boolean free;
	
	public EdgeLocation roadLocation;
	
	public BuildRoad() 
	{}


	
}
