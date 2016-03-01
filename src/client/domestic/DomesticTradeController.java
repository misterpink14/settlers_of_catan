package client.domestic;

import shared.definitions.*;
import shared.models.playerClasses.Player;
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
	
	int playerIndex;
	Player currPlayer;
	
	private int brickAmount;
	private int oreAmount;
	private int sheepAmount;
	private int wheatAmount;
	private int woodAmount;
	
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
	
	public boolean canIncreaseResource(ResourceType resource, int amount) {
		switch(resource) {
		case BRICK:
			if (amount > brickAmount) {
				return false;
			}
			return true;
		case ORE:
			if (amount > oreAmount) {
				return false;
			}
			return true;
		case SHEEP:
			if (amount > sheepAmount) {
				return false;
			}
			return true;
		case WHEAT:
			if (amount > wheatAmount) {
				return false;
			}
			return true;
		case WOOD:
			if (amount > woodAmount) {
				return false;
			}
			return true;
		}
		return false;
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
				setDecreaseFalse(resource);
			}
		}
		if (resourceToSend == resource) {
			// Make sure we can decrease
			if (amountToSend > 0) {
				amountToSend--;
			}
			// if we're at zero, disable decrease button
			if (amountToSend == 0) {
				setDecreaseFalse(resource);
			}
		}
	}
	
	

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		if (resourceToReceive == resource) {
			amountToReceive++;
			if (canIncreaseResource(resource, amountToReceive + 1)) {
				setIncreaseFalse(resource);
			}
			setDecreaseTrue(resource);
		}
		if (resourceToSend == resource) {
			amountToSend++;
			if (canIncreaseResource(resource, amountToSend + 1)) {
				setIncreaseFalse(resource);
			}
			setDecreaseTrue(resource);
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
		amountToReceive = 0;
		if (resourceToReceive != null) {
			setIncreaseFalse(resourceToReceive);
			setDecreaseFalse(resourceToReceive);
		}
		resourceToReceive = resource;
		setIncreaseTrue(resource);
		setDecreaseFalse(resource);
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		amountToSend = 0;
		if (resourceToSend != null) {
			setIncreaseFalse(resourceToSend);
			setDecreaseFalse(resourceToSend);
		}
		resourceToSend = resource;
		if (canIncreaseResource(resource, amountToSend + 1)) {
			setIncreaseFalse(resource);
		} else {
			setIncreaseTrue(resource);
		}
		setDecreaseFalse(resource);
	}

	@Override
	public void unsetResource(ResourceType resource) {
		if (resourceToReceive == resource) {
			setIncreaseFalse(resourceToReceive);
			setDecreaseFalse(resourceToReceive);
			resourceToReceive = null;
			amountToReceive = 0;
		}
		if (resourceToSend == resource) {
			setIncreaseFalse(resourceToSend);
			setDecreaseFalse(resourceToSend);
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
			this.getTradeView().enableDomesticTrade(true);
			this.getTradeOverlay().setResourceSelectionEnabled(true);
			currPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(playerIndex);
			playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
			brickAmount = currPlayer.getNumOfResource(ResourceType.BRICK);
			System.out.println(brickAmount);
			oreAmount = currPlayer.getNumOfResource(ResourceType.ORE);
			sheepAmount = currPlayer.getNumOfResource(ResourceType.SHEEP);
			wheatAmount = currPlayer.getNumOfResource(ResourceType.WHEAT);
			woodAmount = currPlayer.getNumOfResource(ResourceType.WOOD);
		} else {
			this.getTradeView().enableDomesticTrade(false);
			this.getTradeOverlay().setResourceSelectionEnabled(false);
			this.getTradeOverlay().setPlayerSelectionEnabled(false);
			this.getTradeOverlay().setStateMessage("Nacho turn");
			
		}
	}

}

