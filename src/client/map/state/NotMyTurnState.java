package client.map.state;

import client.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class NotMyTurnState implements MapStateInterface {

	@Override
	public void initFromModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceCity(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceRobber(HexLocation hexLoc) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void placeRoad(EdgeLocation edgeLoc) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeSettlement(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeCity(VertexLocation vertLoc) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeRobber(HexLocation hexLoc) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelMove() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSoldierCard() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void playRoadBuildingCard() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void robPlayer(RobPlayerInfo victim) throws Exception {
		// TODO Auto-generated method stub

	}

}
