package client.map.state;

import java.util.ArrayList;

import client.clientFacade.ClientFacade;
import client.map.IMapView;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.mapClasses.Piece;
import shared.models.mapClasses.WaterHex;

/**
 * This is the last phase of the Setup. Players take turns in reverse
 * 	order and are only allowed to place one settlement with adjoining 
 * 	road
 * 		Transitions to NotMyTurn
 * 
 */
public class Setup2State extends Setup1State {

	public Setup2State(IMapView view) {
		super(view);
		this.num_roads = 14;
		this.num_settlements = 4;
	}
}
