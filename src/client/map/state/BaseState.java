package client.map.state;

import client.data.RobPlayerInfo;
import client.map.IMapView;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class BaseState {
	
	IMapView view;
	
	
// CONSTRUCTOR
	public BaseState(IMapView view) {
		this.view = view;
	}
	
	public IMapView getView() {
		return this.view;
	}
	
// Public METHODS
	public void initFromModel() throws Exception {
		// TODO Auto-generated method stub

	}

	
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean canPlaceSettlement(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean canPlaceCity(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean canPlaceRobber(HexLocation hexLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void placeRoad(EdgeLocation edgeLoc) throws Exception {
		// TODO Auto-generated method stub

	}


	public void placeSettlement(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub

	}


	public void placeCity(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub

	}


	public void placeRobber(HexLocation hexLoc) throws Exception {
		// TODO Auto-generated method stub

	}


	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) throws Exception {
		// TODO Auto-generated method stub

	}


	public void cancelMove() throws Exception {
		// TODO Auto-generated method stub

	}


	public void playSoldierCard() throws Exception {
		// TODO Auto-generated method stub

	}


	public void playRoadBuildingCard() throws Exception {
		// TODO Auto-generated method stub

	}


	public void robPlayer(RobPlayerInfo victim) throws Exception {
		// TODO Auto-generated method stub

	}

}
