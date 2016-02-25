package client.map.state;

import client.map.IMapView;
import client.map.IRobView;

public class RobberState extends WaitingState {
	
	private IRobView robView;

	public RobberState(IMapView view, IRobView robView) {
		super(view);
		this.robView = robView;
	}

}
