package client.join;

import java.util.ArrayList;

import client.base.*;
import client.clientFacade.ClientFacade;
import client.clientFacade.ClientFacadeTest;
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
		this.setAIUsers();
	}

	@Override
	public void addAI() {
		
		String aiType = getView().getSelectedAI();
		ClientFacade.getInstance().addAI(aiType);
	}
	
	public void update(GameState state) {
		//if the game has four players, we create a turn tracker to start the game!
		if(ClientFacade.getInstance().game.getPlayers().getNumberOfPlayers() == 4) {
			this.getView().closeModal();	
			ClientFacade.getInstance().game.deleteObserver(obs);
		}
	}
	
	
	private void setAIUsers () {
		
		ArrayList<String> aiList = ClientFacade.getInstance().getListAI();
		String [] aiArray = new String[aiList.size()];
		
		int i = 0;
		for (String s: aiList) {
			aiArray[i] = s;
			i++;
		}
		
		getView().setAIChoices(aiArray);	
	}
}

