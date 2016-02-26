package client.join;

import client.base.*;
import client.clientFacade.ClientFacade;
import shared.definitions.GameState;
import shared.observers.PlayerWaitingObserver;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	PlayerWaitingObserver obs;
	
	public PlayerWaitingController(IPlayerWaitingView view) {
		super(view);
		obs = new PlayerWaitingObserver(this);
	}

	@Override
	public IPlayerWaitingView getView() {
		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		ClientFacade.getInstance().game.addObserver(obs);
		ClientFacade.getInstance().startPoller();
		getView().showModal();
	}

	@Override
	public void addAI() {

		// TEMPORARY
		getView().closeModal();
	}
	
	public void update(GameState state) {
		//if the game has four players, we create a turn tracker to start the game!
		if(state != GameState.PLAYERWAITING) {
			this.getView().closeModal();	
			ClientFacade.getInstance().game.deleteObserver(obs);
		}
	}

}

