package client.roll;

import javax.swing.JOptionPane;

import client.base.*;
import client.clientFacade.ClientFacade;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		setResultView(resultView);
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
		try {
			String roll = ClientFacade.getInstance().rollDice();
			getResultView().setRollValue(Integer.parseInt(roll));
			getResultView().showModal();
		} catch (Exception e) {
			JOptionPane.showMessageDialog((RollView)this.getRollView(), "There was an error when rolling the dice");
		}
	}

}

