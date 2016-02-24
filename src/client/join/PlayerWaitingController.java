package client.join;

import client.base.*;
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
		this.getClientFacade().game.addObserver(obs);
		getView().showModal();
	}

	@Override
	public void addAI() {

		// TEMPORARY
		getView().closeModal();
	}
	
	public void update(GameState state) {
		if(state == GameState.SETUP1) {
			this.getView().closeModal();
		}
	}

}

