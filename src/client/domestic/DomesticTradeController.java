package client.domestic;

import shared.definitions.*;
import shared.observers.DomesticTradeObserver;
import client.base.*;
import client.clientFacade.ClientFacade;
import client.misc.*;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private DomesticTradeObserver obs;
	
	private ResourceType resourceToSend = null;
	private ResourceType resourceToReceive = null;
	private int amountToSend = 0;
	private int amountToReceive = 0;
	private int playerTradingWith = -1;
	
	private boolean brickIncrease = false;
	private boolean brickDecrease = false;
	private boolean oreIncrease = false;
	private boolean oreDecrease = false;
	private boolean sheepIncrease = false;
	private boolean sheepDecrease = false;
	private boolean wheatIncrease = false;
	private boolean wheatDecrease = false;
	private boolean woodIncrease = false;
	private boolean woodDecrease = false;

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		obs = new DomesticTradeObserver(this);
		ClientFacade.getInstance().game.addObserver(obs);
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {

		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		if (resourceToReceive == resource) {
			// Make sure we can decrease
			if (amountToReceive > 0) {
				amountToReceive--;
			}
			// if we're at zero, disable decrease button
			if (amountToReceive == 0) {
				this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToReceive, true, false);
				switch(resource) {
				case BRICK:
					brickDecrease = false;
					break;
				case ORE:
					oreDecrease = false;
					break;
				case SHEEP:
					sheepDecrease = false;
					break;
				case WHEAT:
					wheatDecrease = false;
					break;
				case WOOD:
					woodDecrease = false;
					break;
				}
			}
		}
		if (resourceToSend == resource) {
			// Make sure we can decrease
			if (amountToSend > 0) {
				amountToSend--;
			}
			// if we're at zero, disable decrease button
			if (amountToSend == 0) {
				this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToSend, true, false);
				switch(resource) {
				case BRICK:
					brickDecrease = false;
					break;
				case ORE:
					oreDecrease = false;
					break;
				case SHEEP:
					sheepDecrease = false;
					break;
				case WHEAT:
					wheatDecrease = false;
					break;
				case WOOD:
					woodDecrease = false;
					break;
				}
			}
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		if (resourceToReceive == resource) {
			amountToReceive++;
			this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToReceive, true, true);
			switch(resource) {
			case BRICK:
				brickDecrease = true;
				break;
			case ORE:
				oreDecrease = true;
				break;
			case SHEEP:
				sheepDecrease = true;
				break;
			case WHEAT:
				wheatDecrease = true;
				break;
			case WOOD:
				woodDecrease = true;
				break;
			}
			
		}
		if (resourceToSend == resource) {
			amountToSend++;
			this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToSend, true, true);
			switch(resource) {
			case BRICK:
				brickDecrease = true;
				break;
			case ORE:
				oreDecrease = true;
				break;
			case SHEEP:
				sheepDecrease = true;
				break;
			case WHEAT:
				wheatDecrease = true;
				break;
			case WOOD:
				woodDecrease = true;
				break;
			}
		}
	}

	@Override
	public void sendTradeOffer() {

		getTradeOverlay().closeModal();
//		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		playerTradingWith = playerIndex;
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		if (resourceToReceive != null) {
			this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToReceive, false, false);
			
			switch(resourceToReceive) {
			case BRICK:
				brickIncrease = false;
				brickDecrease = false;
				break;
			case ORE:
				oreIncrease = false;
				oreDecrease = false;
				break;
			case SHEEP:
				sheepIncrease = false;
				sheepDecrease = false;
				break;
			case WHEAT:
				wheatIncrease = false;
				wheatDecrease = false;
				break;
			case WOOD:
				woodIncrease = false;
				woodDecrease = false;
				break;
			}
		}
		
		resourceToReceive = resource;
		// can increase but not decrease as amountToReceive is 0
		amountToReceive = 0;
		this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToReceive, true, false);
		switch(resource) {
		case BRICK:
			brickIncrease = true;
			break;
		case ORE:
			oreIncrease = true;
			break;
		case SHEEP:
			sheepIncrease = true;
			break;
		case WHEAT:
			wheatIncrease = true;
			break;
		case WOOD:
			woodIncrease = true;
			break;
		}
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		if (resourceToSend != null) {
			this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToSend, false, false);
			
			switch(resourceToSend) {
			case BRICK:
				brickIncrease = false;
				brickDecrease = false;
				break;
			case ORE:
				oreIncrease = false;
				oreDecrease = false;
				break;
			case SHEEP:
				sheepIncrease = false;
				sheepDecrease = false;
				break;
			case WHEAT:
				wheatIncrease = false;
				wheatDecrease = false;
				break;
			case WOOD:
				woodIncrease = false;
				woodDecrease = false;
				break;
			}
		}
		
		resourceToSend = resource;
		// can increase but not decrease as amountToSend is 0
		amountToSend = 0;
		this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToSend, true, false);
		switch(resource) {
		case BRICK:
			brickIncrease = true;
			break;
		case ORE:
			oreIncrease = true;
			break;
		case SHEEP:
			sheepIncrease = true;
			break;
		case WHEAT:
			wheatIncrease = true;
			break;
		case WOOD:
			woodIncrease = true;
			break;
		}
	}

	@Override
	public void unsetResource(ResourceType resource) {
		if (resourceToReceive == resource) {
			resourceToReceive = null;
			amountToReceive = 0;
		}
		if (resourceToSend == resource) {
			resourceToSend = null;
			amountToReceive = 0;
		}
		switch(resource) {
		case BRICK:
			brickIncrease = false;
			brickDecrease = false;
			break;
		case ORE:
			oreIncrease = false;
			oreDecrease = false;
			break;
		case SHEEP:
			sheepIncrease = false;
			sheepDecrease = false;
			break;
		case WHEAT:
			wheatIncrease = false;
			wheatDecrease = false;
			break;
		case WOOD:
			woodIncrease = false;
			woodDecrease = false;
			break;
		}
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {

		getAcceptOverlay().closeModal();
		
	}

	public void update(GameState gameState) {
		if (gameState == GameState.MYTURN) {
			// update the GUI
			// enable domestic trade button
			this.getTradeView().enableDomesticTrade(true);
			// enable send/receive options for each resource
			this.getTradeOverlay().setResourceSelectionEnabled(true);
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, brickIncrease, brickDecrease);
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, oreIncrease, oreDecrease);
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sheepIncrease, sheepDecrease);
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, wheatIncrease, wheatDecrease);
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, woodIncrease, woodDecrease);
			// enable player selection for trade
			this.getTradeOverlay().setPlayerSelectionEnabled(true);
			// change message in trade box
			//this.getTradeOverlay().setStateMessage("Set the trade you want to make");
		} else {
			this.getTradeView().enableDomesticTrade(false);
			this.getTradeOverlay().setResourceSelectionEnabled(false);
			this.getTradeOverlay().setPlayerSelectionEnabled(false);
			this.getTradeOverlay().setStateMessage("Nacho turn");
			
		}
	}

}

