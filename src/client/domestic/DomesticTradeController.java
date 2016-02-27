package client.domestic;

import shared.definitions.*;
import shared.models.cardClasses.ResourceCards;
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
			}
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		if (resourceToReceive == resource) {
			amountToReceive++;
		}
		if (resourceToSend == resource) {
			amountToSend++;
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
		resourceToReceive = resource;
		// can increase but not decrease as amountToReceive is 0
		this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToReceive, true, false);
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		resourceToSend = resource;
		// can increase but not decrease as amountToSend is 0
		this.getTradeOverlay().setResourceAmountChangeEnabled(resourceToSend, true, false);
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
			// enable player selection for trade
			this.getTradeOverlay().setPlayerSelectionEnabled(true);
			// change message in trade box
			this.getTradeOverlay().setStateMessage("Set the trade you want to make");
		} else {
			this.getTradeView().enableDomesticTrade(false);
			this.getTradeOverlay().setResourceSelectionEnabled(false);
			this.getTradeOverlay().setPlayerSelectionEnabled(false);
			this.getTradeOverlay().setStateMessage("Nacho turn");
			
		}
	}

}

