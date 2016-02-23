package client.map.state;

import client.base.IView;
import client.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public abstract class MapState implements MapStateInterface {
	
	public void MapStateInterface ()
	{
		
	}

	
	public void initFromModel() throws Exception {
		throw new Exception("Unimplemented");
	}
	
	
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Check if a settlement can be placed
	 * 
	 * @param vertLoc
	 * @return
	 * @throws Exception 
	 */
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Check if a city can be placed
	 * 
	 * @param vertLoc
	 * @return
	 * @throws Exception 
	 */
	public boolean canPlaceCity(VertexLocation vertLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Check if a robber can be placed
	 * 
	 * @param hexLoc
	 * @return
	 * @throws Exception 
	 */
	public boolean canPlaceRobber(HexLocation hexLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Place a road, checks if a road can be placed first
	 * 
	 * @param edgeLoc
	 * @throws Exception 
	 */
	public void placeRoad(EdgeLocation edgeLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Place a settlement, checks if a settlement can be placed first
	 * 
	 * @param vertLoc
	 * @throws Exception 
	 */
	public void placeSettlement(VertexLocation vertLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Place a city, checks if a city can be placed first
	 * 
	 * @param vertLoc
	 * @throws Exception 
	 */
	public void placeCity(VertexLocation vertLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Place a robber, checks if a robber can be placed first
	 * 
	 * @param hexLoc
	 * @throws Exception 
	 */
	public void placeRobber(HexLocation hexLoc) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Start the move
	 * 
	 * @param pieceType
	 * @param isFree
	 * @param allowDisconnected
	 * @throws Exception 
	 */
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Cancel the move
	 * @throws Exception 
	 * 
	 */
	public void cancelMove() throws Exception {
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Play a soldier card
	 * @throws Exception 
	 * 
	 */
	public void playSoldierCard() throws Exception 
	{
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Play the road building card
	 * @throws Exception 
	 * 
	 */
	public void playRoadBuildingCard() throws Exception 
	{
		throw new Exception("Unimplemented");
	}
	
	/**
	 * Rob a given player
	 * 
	 * @param victim
	 * @throws Exception 
	 */
	public void robPlayer(RobPlayerInfo victim) throws Exception 
	{
		throw new Exception("Unimplemented");
	}
	
}
