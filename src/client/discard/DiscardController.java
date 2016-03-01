package client.discard;

import shared.definitions.*;
import client.base.*;
import client.clientFacade.ClientFacade;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
	
	private int maxBrick;
	private int maxOre;
	private int maxSheep;
	private int maxWheat;
	private int maxWood;
	
	private int currBrick;
	private int currOre;
	private int currSheep;
	private int currWheat;
	private int currWood;
	
	private int totalToDiscard;
	private int currTotal;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		
		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		switch(resource) {
		case BRICK:
			if(++currBrick < maxBrick)this.getDiscardView().setResourceAmountChangeEnabled(resource, true, true);
			else this.getDiscardView().setResourceAmountChangeEnabled(resource, false, true);
			this.getDiscardView().setResourceDiscardAmount(resource, currBrick);
			break;
		case ORE:
			if(++currOre < maxOre) this.getDiscardView().setResourceAmountChangeEnabled(resource, true, true);
			else this.getDiscardView().setResourceAmountChangeEnabled(resource, false, true);
			this.getDiscardView().setResourceDiscardAmount(resource, currOre);
			break;
		case SHEEP:
			if(++currSheep < maxSheep) this.getDiscardView().setResourceAmountChangeEnabled(resource, true, true);
			else this.getDiscardView().setResourceAmountChangeEnabled(resource, false, true);
			this.getDiscardView().setResourceDiscardAmount(resource, currSheep);
			break;
		case WHEAT:
			if(++currWheat < maxWheat) this.getDiscardView().setResourceAmountChangeEnabled(resource, true, true);
			else this.getDiscardView().setResourceAmountChangeEnabled(resource, false, true);
			this.getDiscardView().setResourceDiscardAmount(resource, currWheat);
			break;
		case WOOD:
			if(++currWood < maxWood) this.getDiscardView().setResourceAmountChangeEnabled(resource, true, true);
			else this.getDiscardView().setResourceAmountChangeEnabled(resource, false, true);
			this.getDiscardView().setResourceDiscardAmount(resource, currWood);
			break;
		}
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
	}

	public void update(GameState gameState) {
		if(gameState == GameState.DISCARD) {
			
		}
	}

	@Override
	public void discard() {
		// TODO Auto-generated method stub
		
	}

}

