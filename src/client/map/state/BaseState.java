package client.map.state;

import client.data.RobPlayerInfo;
import client.map.IMapView;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class BaseState {
	
	IMapView view;
	boolean hasRenderedOverlay = false; 
	CatanColor color;
	
	
	
// CONSTRUCTOR
	public BaseState(IMapView view) {
		this.view = view;
		this.color = ClientFacade.getInstance().getUserColor()
	}
	
	public IMapView getView() {
		return this.view;
	}
	
// Public METHODS
	public void initFromModel() {

	}

	
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return false;
	}


	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		return false;
	}


	public boolean canPlaceCity(VertexLocation vertLoc) {
		return false;
	}

	
	public boolean canPlaceRobber(HexLocation hexLoc) {
		return false;
	}

	
	public void placeRoad(EdgeLocation edgeLoc) {

	}


	public void placeSettlement(VertexLocation vertLoc) {

	}


	public void placeCity(VertexLocation vertLoc) {

	}


	public void placeRobber(HexLocation hexLoc) {

	}


	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

	}


	public void cancelMove() {

	}


	public void playSoldierCard() {

	}


	public void playRoadBuildingCard() {
	}


	public void robPlayer(RobPlayerInfo victim) {
	}

}
