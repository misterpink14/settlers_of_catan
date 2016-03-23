package shared.communication.proxy;

import shared.locations.EdgeLocation;

public class BuildRoad {

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
	
	
	public BuildRoad(int playerIndex, Boolean free, EdgeLocation roadLocation) {
		this.playerIndex = playerIndex;
		this.free = free;
		this.roadLocation = roadLocation;
	}


	public BuildRoad(EdgeLocation edgeLoc, boolean isFree)
	{
		this.roadLocation = edgeLoc;
		this.free = isFree;
	}
	
}
