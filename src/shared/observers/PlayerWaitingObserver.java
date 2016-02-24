package shared.observers;

import java.util.Observable;
import java.util.Observer;

import client.base.IController;

public class PlayerWaitingObserver implements Observer{
	IController controller;

	public PlayerWaitingObserver(IController controller) {
		this.controller = controller;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	
	}

}
