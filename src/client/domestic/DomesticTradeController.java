package client.domestic;

import shared.communication.proxy.OfferTrade;
import shared.definitions.*;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.cardClasses.ResourceCards;
import shared.models.playerClasses.Player;
import shared.observers.DomesticTradeObserver;

import java.util.Arrays;

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
	
	private ResourceCards resourceToSend;
	private boolean[] sendingResource;
	private ResourceCards resourceToReceive;
	private boolean[] receivingResource;
	private int playerTradingWith = -1;
	
	int playerIndex;
	Player currPlayer;

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
		this.getTradeView().enableDomesticTrade(false);
		resourceToSend = new ResourceCards(0,0,0,0,0);
		receivingResource = new boolean[5];
		Arrays.fill(receivingResource, Boolean.FALSE);
		resourceToReceive = new ResourceCards(0,0,0,0,0);
		sendingResource = new boolean[5];
		Arrays.fill(sendingResource, Boolean.FALSE);
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

		this.getTradeOverlay().setResourceSelectionEnabled(false);
		currPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(playerIndex);
		playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
		
		this.getTradeOverlay().setResourceSelectionEnabled(true);
		this.getTradeOverlay().setPlayerSelectionEnabled(true);
		
		getTradeOverlay().showModal();
	}
	
	public void setIncreaseDecrease(ResourceType resource) {
		if (sendingResource[getIsTrading(resource)]) {
			if (resourceToSend.getCards(resource) == 0) {
				if (resourceToSend.getCards(resource) == currPlayer.getNumOfResource(resource)) {
					this.getTradeOverlay().setResourceAmountChangeEnabled(resource, false, false);
				} else {
					this.getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
				}
			} else if (resourceToSend.getCards(resource) == currPlayer.getNumOfResource(resource)) {
				this.getTradeOverlay().setResourceAmountChangeEnabled(resource, false, true);
			} else {
				this.getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true);
			}
		}
		if (receivingResource[getIsTrading(resource)]) {
			if(resourceToReceive.getCards(resource) == 0) {
				this.getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
			} else {
				this.getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true);
			}
		}
	}
	
	public boolean isReadyToTrade() {
		if (playerTradingWith != -1 && !resourceToSend.isEmpty() && !resourceToReceive.isEmpty()) {
			this.getTradeOverlay().setStateMessage("Trade!");
			return true;
		}
		this.getTradeOverlay().setStateMessage("set the trade you want to make");
		return false;
	}
	
	public int getIsTrading(ResourceType resource) {
		switch(resource) {
		case BRICK:
			return 0;
		case ORE:
			return 1;
		case SHEEP:
			return 2;
		case WHEAT:
			return 3;
		case WOOD:
			return 4;
		}
		return -1;
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		
		if (resourceToReceive.getCards(resource) > 0) {
			try {
				resourceToReceive.removeCard(resource, 1);
			} catch (InsufficientCardNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setIncreaseDecrease(resource);
		}
		if (resourceToSend.getCards(resource) > 0) {
			try {
				resourceToSend.removeCard(resource, 1);
			} catch (InsufficientCardNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setIncreaseDecrease(resource);
		}
		this.getTradeOverlay().setTradeEnabled(isReadyToTrade());
	}
	
	

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		
		if (receivingResource[getIsTrading(resource)]) {
			resourceToReceive.addCard(resource, 1);
			setIncreaseDecrease(resource);
		}
		if (sendingResource[getIsTrading(resource)])  {
			resourceToSend.addCard(resource, 1);
			setIncreaseDecrease(resource);
		}
		this.getTradeOverlay().setTradeEnabled(isReadyToTrade());
	}

	@Override
	public void sendTradeOffer() {

		getTradeOverlay().closeModal();
		getWaitOverlay().showModal();
		OfferTrade offer = new OfferTrade(currPlayer.getIndex(), playerTradingWith, resourceToSend, resourceToReceive);
		try {
			ClientFacade.getInstance().offerTrade(offer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasBeenOfferedTrade() {
		OfferTrade offerTrade = ClientFacade.getInstance().getOfferTrade();
		if (offerTrade == null) {
			return false;
		}
		System.out.println(offerTrade.receiverIndex);
		System.out.println(playerIndex);
		if (offerTrade.receiverIndex == playerIndex) {
			return true;
		}
		return false;
	}
	
	public boolean isOfferingTrade() {
		OfferTrade offerTrade = ClientFacade.getInstance().getOfferTrade();
		if (offerTrade == null) {
			return false;
		}
		if (offerTrade.playerIndex == playerIndex) {
			return true;
		}
		return false;
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		playerTradingWith = playerIndex;
		this.getTradeOverlay().setTradeEnabled(isReadyToTrade());
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		receivingResource[getIsTrading(resource)] = true;
		sendingResource[getIsTrading(resource)] = false;
		resourceToReceive.setZeroCards(resource);
		resourceToSend.setZeroCards(resource);
		this.getTradeOverlay().setResourceAmount(resource, "0");
		setIncreaseDecrease(resource);
		this.getTradeOverlay().setResourceAmountVisible(resource, true);
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		receivingResource[getIsTrading(resource)] = false;
		sendingResource[getIsTrading(resource)] = true;
		resourceToReceive.setZeroCards(resource);
		resourceToSend.setZeroCards(resource);
		this.getTradeOverlay().setResourceAmount(resource, "0");
		setIncreaseDecrease(resource);
		this.getTradeOverlay().setResourceAmountVisible(resource, true);
	}

	@Override
	public void unsetResource(ResourceType resource) {
		receivingResource[getIsTrading(resource)] = false;
		sendingResource[getIsTrading(resource)] = false;
		resourceToReceive.setZeroCards(resource);
		resourceToSend.setZeroCards(resource);
		this.getTradeOverlay().setResourceAmount(resource, "0");
		setIncreaseDecrease(resource);
		this.getTradeOverlay().setResourceAmountVisible(resource, false);
		this.getTradeOverlay().setTradeEnabled(isReadyToTrade());
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
			this.getTradeView().enableDomesticTrade(true);
			
			currPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(playerIndex);
			playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
			
			if (isOfferingTrade()) {
				this.getWaitOverlay().showModal();
			}
		} else {
			currPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(playerIndex);
			playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
			this.getTradeView().enableDomesticTrade(false);
			/*this.getTradeOverlay().setResourceSelectionEnabled(false);
			this.getTradeOverlay().setPlayerSelectionEnabled(false);
			this.getTradeOverlay().setStateMessage("Nacho turn");*/
			if (hasBeenOfferedTrade()) {
				System.out.println("gets here...");
				this.getAcceptOverlay().showModal();
			}
		}
	}

}

