package client.map.state;

import client.map.IMapView;

/**
 * The map is visable to the player, but actions should be disabled.
 * 	This facilitates when a player makes a move and the server needs 
 * 	to update the model so the player doesn't double dip
 * 		Transitions back to MyTurn
 * 
 */
public class WaitingState extends BaseState {

	public WaitingState(IMapView view) {
		super(view);
	}

	
}
