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

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		obs = new TurnTrackerObserver(this);
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
		PlayerInfo localPlayer = ClientFacade.getInstance().getUserData();
		getView().setLocalPlayerColor(localPlayer.getColor());
	}
	
	public void setObserver() {
		ClientFacade.getInstance().game.addObserver(obs);
	}
	
	public void update(GameState state) {
		initFromModel();
		
		switch (state) {
		case DISCARD:
			this.getView().updateGameState("Discard Phase", false);
			break;
		case ENDOFGAME:
			this.getView().updateGameState(ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(ClientFacade.getInstance().game.getWinner()).getName() + " has won the game", false);
			break;
		case LOGIN:
			break;
		case MYTURN:
			this.getView().updateGameState("It is your turn", false);
			break;
		case NOTMYTURN:
			this.getView().updateGameState("Waiting for turn", false);
			break;
		case OUTDATED:
			break;
		case ROBBER:
			this.getView().updateGameState("Robber", false);
			break;
		case ROLLING:
			this.getView().updateGameState("Roll Phase", false);
			break;
		case SETUP1:
			this.getView().updateGameState("Game Setup", false);
			break;
		case SETUP2:
			this.getView().updateGameState("Game Setup", false);
			break;
		case TRADEACCEPT:
			this.getView().updateGameState("Trade Phase", false);
			break;
		case TRADEOFFER:
			this.getView().updateGameState("Trade Phase", false);
			break;
		default:
			break;
		
		}
	}

}

