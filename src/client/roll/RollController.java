package client.roll;

import java.util.Timer;
import java.util.TimerTask;

import client.base.*;
import client.clientFacade.ClientFacade;
import shared.definitions.GameState;
import shared.observers.RollObserver;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;
	private Timer timer;
	private RollObserver obs;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {
		super(view);
		obs = new RollObserver(this);
		setResultView(resultView);
	}
	
	public void start() {
		ClientFacade.getInstance().game.addObserver(obs);
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	@Override
	public void rollDice() {
		if (this.getRollView().isModalShowing()) {
			this.getRollView().closeModal();
		}
		int rollNum = -1;
		try {
			rollNum = ClientFacade.getInstance().rollDice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getResultView().setRollValue(rollNum);
		getResultView().showModal();
		timer.cancel();
	}
	
	private class AutoRoll extends TimerTask {

		@Override
		public void run() {
			rollDice();
		}
	}

	public void update(GameState gameState) {
		if (gameState == GameState.ROLLING) {
			getRollView().showModal();
			timer = new Timer();
			timer.schedule(new AutoRoll(), 5000);
		}
	}
}

