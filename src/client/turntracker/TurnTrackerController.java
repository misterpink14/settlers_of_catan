package client.turntracker;

import shared.definitions.CatanColor;
import shared.definitions.GameState;
import shared.observers.TurnTrackerObserver;
import client.base.*;
import client.clientFacade.ClientFacade;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {
	
	TurnTrackerObserver obs;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		obs = new TurnTrackerObserver(this);
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {

	}
	
	private void initFromModel() {
		//<temp>
		getView().setLocalPlayerColor(CatanColor.RED);
		//</temp>
	}
	
	public void setObserver() {
		ClientFacade.getInstance().game.addObserver(obs);
	}
	
	public void update(GameState state) {
		if(state == GameState.SETUP1) {
			this.getView().updateGameState("Game Setup", false);
		}
		
	}

}

