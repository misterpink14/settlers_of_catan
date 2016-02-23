package client.map.state;

import client.base.IView;
import client.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public interface MapStateInterface {
	
	
	
	/**
	 * Initialize the view
	 * @throws Exception 
	 * 
	 */
	void initFromModel() throws Exception;
	
	/**
	 * Check if a road can be placed
	 * 
	 * @param edgeLoc
	 * @return
	 * @throws Exception 
	 */
	boolean canPlaceRoad(EdgeLocation edgeLoc) throws Exception;
	
	/**
	 * Check if a settlement can be placed
	 * 
	 * @param vertLoc
	 * @return
	 */
	boolean canPlaceSettlement(VertexLocation vertLoc) throws Exception;
	
	/**
	 * Check if a city can be placed
	 * 
	 * @param vertLoc
	 * @return
	 */
	boolean canPlaceCity(VertexLocation vertLoc) throws Exception;
	
	/**
	 * Check if a robber can be placed
	 * 
	 * @param hexLoc
	 * @return
	 */
	boolean canPlaceRobber(HexLocation hexLoc) throws Exception;
	
	/**
	 * Place a road, checks if a road can be placed first
	 * 
	 * @param edgeLoc
	 */
	void placeRoad(EdgeLocation edgeLoc) throws Exception;
	
	/**
	 * Place a settlement, checks if a settlement can be placed first
	 * 
	 * @param vertLoc
	 */
	void placeSettlement(VertexLocation vertLoc) throws Exception;
	
	/**
	 * Place a city, checks if a city can be placed first
	 * 
	 * @param vertLoc
	 */
	void placeCity(VertexLocation vertLoc) throws Exception;
	
	/**
	 * Place a robber, checks if a robber can be placed first
	 * 
	 * @param hexLoc
	 */
	void placeRobber(HexLocation hexLoc) throws Exception;
	
	/**
	 * Start the move
	 * 
	 * @param pieceType
	 * @param isFree
	 * @param allowDisconnected
	 */
	void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) throws Exception;
	
	/**
	 * Cancel the move
	 * 
	 */
	public void cancelMove() throws Exception;
	
	/**
	 * Play a soldier card
	 * 
	 */
	public void playSoldierCard() throws Exception;
	
	/**
	 * Play the road building card
	 * 
	 */
	public void playRoadBuildingCard() throws Exception;
	
	/**
	 * Rob a given player
	 * 
	 * @param victim
	 */
	public void robPlayer(RobPlayerInfo victim) throws Exception;
	
}
