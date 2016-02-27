package client.resources;

import java.util.*;

import client.base.*;
import shared.definitions.GameState;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController {

	private Map<ResourceBarElement, IAction> elementActions;
	
	GameState state;
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		elementActions = new HashMap<ResourceBarElement, IAction>();
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {
		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
		if(this.state == GameState.MYTURN) {
			executeElementAction(ResourceBarElement.ROAD);
		}
	}

	@Override
	public void buildSettlement() {
		if(this.state == GameState.MYTURN) {
			executeElementAction(ResourceBarElement.SETTLEMENT);
		}
	}

	@Override
	public void buildCity() {
		if(this.state == GameState.MYTURN) {
			executeElementAction(ResourceBarElement.CITY);
		}
	}

	@Override
	public void buyCard() {
		if(this.state == GameState.MYTURN) {
			executeElementAction(ResourceBarElement.BUY_CARD);
		}
	}

	@Override
	public void playCard() {
		if(this.state == GameState.MYTURN) {
			executeElementAction(ResourceBarElement.PLAY_CARD);
		}
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}
	
	public void update(GameState state) {
		switch(state) {
		case MYTURN:
			this.state = GameState.MYTURN;
			break;
		default:
			this.state = GameState.NOTMYTURN;
			break;
		
		}
	}

}

