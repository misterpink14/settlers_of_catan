package client.domestic;

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
	
	public void setDecreaseFalse(ResourceType resource) {
		switch(resource) {
		case BRICK:
			brickDecrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, brickIncrease, brickDecrease);
			break;
		case ORE:
			oreDecrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, oreIncrease, oreDecrease);
			break;
		case SHEEP:
			sheepDecrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sheepIncrease, sheepDecrease);
			break;
		case WHEAT:
			wheatDecrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, wheatIncrease, wheatDecrease);
			break;
		case WOOD:
			woodDecrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, woodIncrease, woodDecrease);
			break;
		}
	}
	
	public void setDecreaseTrue(ResourceType resource) {
		switch(resource) {
		case BRICK:
			brickDecrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, brickIncrease, brickDecrease);
			break;
		case ORE:
			oreDecrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, oreIncrease, oreDecrease);
			break;
		case SHEEP:
			sheepDecrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sheepIncrease, sheepDecrease);
			break;
		case WHEAT:
			wheatDecrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, wheatIncrease, wheatDecrease);
			break;
		case WOOD:
			woodDecrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, woodIncrease, woodDecrease);
			break;
		}
	}
	
	public void setIncreaseFalse(ResourceType resource) {
		switch(resource) {
		case BRICK:
			brickIncrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, brickIncrease, brickDecrease);
			break;
		case ORE:
			oreIncrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, oreIncrease, oreDecrease);
			break;
		case SHEEP:
			sheepIncrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sheepIncrease, sheepDecrease);
			break;
		case WHEAT:
			wheatIncrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, wheatIncrease, wheatDecrease);
			break;
		case WOOD:
			woodIncrease = false;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, woodIncrease, woodDecrease);
			break;
		}
	}
	
	public void setIncreaseTrue(ResourceType resource) {
		switch(resource) {
		case BRICK:
			brickIncrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, brickIncrease, brickDecrease);
			break;
		case ORE:
			oreIncrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, oreIncrease, oreDecrease);
			break;
		case SHEEP:
			sheepIncrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, sheepIncrease, sheepDecrease);
			break;
		case WHEAT:
			wheatIncrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, wheatIncrease, wheatDecrease);
			break;
		case WOOD:
			woodIncrease = true;
			this.getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, woodIncrease, woodDecrease);
			break;
		}
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
			if (resourceToReceive.getCards(resource) == 0) {
				setDecreaseFalse(resource);
			}
		}
		if (resourceToSend.getCards(resource) > 0) {
			try {
				resourceToSend.removeCard(resource, 1);
			} catch (InsufficientCardNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (resourceToSend.getCards(resource) == 0) {
				setDecreaseFalse(resource);
			}
		}
	}
	
	

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		
		if (receivingResource[getIsTrading(resource)]) {
			resourceToReceive.addCard(resource, 1);
		}
		if (sendingResource[getIsTrading(resource)])  {
			resourceToSend.addCard(resource, 1);
			if (resourceToSend.getCards(resource) == currPlayer.getNumOfResource(resource)) {
				setIncreaseFalse(resource);
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
		receivingResource[getIsTrading(resource)] = true;
		sendingResource[getIsTrading(resource)] = false;
		resourceToReceive.setZeroCards(resource);
		resourceToSend.setZeroCards(resource);
		setIncreaseTrue(resource);
		setDecreaseFalse(resource);
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		receivingResource[getIsTrading(resource)] = false;
		sendingResource[getIsTrading(resource)] = true;
		resourceToReceive.setZeroCards(resource);
		resourceToSend.setZeroCards(resource);
		if (currPlayer.getNumOfResource(resource) == 0) {
			setIncreaseFalse(resource);
		} else {
			setIncreaseTrue(resource);
		}
		setDecreaseFalse(resource);
	}

	@Override
	public void unsetResource(ResourceType resource) {
		receivingResource[getIsTrading(resource)] = false;
		sendingResource[getIsTrading(resource)] = false;
		resourceToReceive.setZeroCards(resource);
		resourceToSend.setZeroCards(resource);
		setIncreaseFalse(resource);
		setDecreaseFalse(resource);
		this.getTradeOverlay().setResourceAmountVisible(resource, false);
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
			//this.getTradeView().enableDomesticTrade(false);
			
			currPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(playerIndex);
			playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
				
		} else {
			this.getTradeView().enableDomesticTrade(false);
			/*this.getTradeOverlay().setResourceSelectionEnabled(false);
			this.getTradeOverlay().setPlayerSelectionEnabled(false);
			this.getTradeOverlay().setStateMessage("Nacho turn");*/
			
		}
	}

}

