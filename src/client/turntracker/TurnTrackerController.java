package client.turntracker;

import shared.definitions.CatanColor;
import shared.definitions.GameState;
import shared.observers.TurnTrackerObserver;

import javax.swing.JOptionPane;

import client.base.*;
import client.clientFacade.ClientFacade;
import client.data.PlayerInfo;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {
	
	TurnTrackerObserver obs;
	
	GameState state;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		obs = new TurnTrackerObserver(this);
		state = GameState.LOGIN;
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		try {
			ClientFacade.getInstance().finishTurn();
		} catch (Exception e) {
			JOptionPane.showMessageDialog((TurnTrackerView) getView(), "Finishing your turn failed. That sucks!");
		}
	}
	
	private void initFromModel() {
		//PlayerInfo localPlayer = ClientFacade.getInstance().getUserData();
		//getView().setLocalPlayerColor(localPlayer.getColor());
	}
	
	public void setObserver() {
		ClientFacade.getInstance().game.addObserver(obs);
	}
	
	public void update(GameState state) {
		this.state = state;
		if(state == GameState.SETUP1) {
			this.getView().updateGameState("Game Setup", false);
			if(ClientFacade.getInstance().isTurn()) {
				
			}
		}
		if(state == GameState.MYTURN) {
			this.getView().updateGameState("It is your turn", false);
		}
		if(state == GameState.NOTMYTURN) {
			this.getView().updateGameState("It is not your turn", false);
		}
		
	}

}

