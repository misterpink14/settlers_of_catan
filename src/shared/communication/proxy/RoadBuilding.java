package shared.communication.proxy;
import shared.locations.EdgeLocation;

public class RoadBuilding
{

	/**
	 * The index of the player playing the Road Building card
	 */
	public int playerIndex;
	
	/**
	 * The first spot they want to build a road
	 */
	public EdgeLocation firstSpot;
	
	/**
	 * The second spot they want to build a road
	 */
	public EdgeLocation secondSpot;
	
	public RoadBuilding() 
	{}

	public RoadBuilding(int playerIndex, EdgeLocation firstSpot, EdgeLocation secondSpot) {
		this.playerIndex = playerIndex;
		this.firstSpot = firstSpot;
		this.secondSpot = secondSpot;
	}

	
	
}
