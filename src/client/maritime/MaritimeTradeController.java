package client.maritime;

import shared.definitions.*;
import shared.models.playerClasses.Player;
import shared.observers.MaritimeTradeObserver;

import java.util.ArrayList;
import java.util.List;

import client.base.*;
import client.clientFacade.ClientFacade;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	private MaritimeTradeObserver obs;
	ResourceType[] resourcesToGive;
	ResourceType[] resourcesToGet;
	ResourceType giveResource;
	ResourceType getResource;
	int ratio;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		super(tradeView);
		setTradeOverlay(tradeOverlay);
		obs = new MaritimeTradeObserver(this);
		ClientFacade.getInstance().game.addObserver(obs);
		this.getTradeView().enableMaritimeTrade(false);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		getTradeOverlay().setTradeEnabled(false);
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		
		Player p = ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(
						ClientFacade.getInstance().getUserData().getPlayerIndex());
		
		// These need to be set to the ratio available
		
		// 4 if no harbor, 3 if a norm, and 2 if a special
		int playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
		int brickRatio = ClientFacade.getInstance().game.getMaritimeTradeRatio(playerIndex, ResourceType.BRICK);
		int woodRatio = ClientFacade.getInstance().game.getMaritimeTradeRatio(playerIndex, ResourceType.WOOD);
		int wheatRatio = ClientFacade.getInstance().game.getMaritimeTradeRatio(playerIndex, ResourceType.WHEAT);
		int oreRatio = ClientFacade.getInstance().game.getMaritimeTradeRatio(playerIndex, ResourceType.ORE);
		int sheepRatio = ClientFacade.getInstance().game.getMaritimeTradeRatio(playerIndex, ResourceType.SHEEP);
		
		List<ResourceType> toGive = new ArrayList<ResourceType>();
		if (p.getNumOfResource(ResourceType.BRICK) >= brickRatio) {
			toGive.add(ResourceType.BRICK);
		}
		if (p.getNumOfResource(ResourceType.WOOD) >= woodRatio) {
			toGive.add(ResourceType.WOOD);
		}
		if (p.getNumOfResource(ResourceType.WHEAT) >= wheatRatio) {
			toGive.add(ResourceType.WHEAT);
		}
		if (p.getNumOfResource(ResourceType.SHEEP) >= sheepRatio) {
			toGive.add(ResourceType.SHEEP);
		}
		if (p.getNumOfResource(ResourceType.ORE) >= oreRatio) {
			toGive.add(ResourceType.ORE);
		}
		resourcesToGive = (ResourceType[])toGive.toArray();
		
		List<ResourceType> resourcesAvailable = new ArrayList<ResourceType>();
		for (ResourceType r : ResourceType.values()) {
			// if bank has >= 1 available, show it is available
			// I have checks in the bank now, but just need to actually find a path to the bank
			// Maybe make it a singleton?
		}
		resourcesToGet = (ResourceType[])resourcesAvailable.toArray();
		
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
		try {
			ClientFacade.getInstance().tradeHarbor(giveResource, getResource, ratio);
		} catch (Exception e) {
			
		}
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getResource = resource;
		getTradeOverlay().selectGetOption(getResource, 1);
		getTradeOverlay().setTradeEnabled(true);
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		// Need a way to calculate the cost of the resource? How do I find if they 
		// have a 4:1, 3:1, or 2:1 ratio.
		giveResource = resource;
		ratio = 0; // Should be = ratio option
		getTradeOverlay().selectGiveOption(giveResource, ratio);
		getTradeOverlay().showGetOptions(resourcesToGet);
	}

	@Override
	public void unsetGetValue() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().showGetOptions(resourcesToGet);
		getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().showGiveOptions(resourcesToGive);
		getTradeOverlay().setTradeEnabled(false);
	}

	public void update(GameState gameState) {
		if (gameState == GameState.MYTURN) {
			this.getTradeView().enableMaritimeTrade(true);
		} else {
			this.getTradeView().enableMaritimeTrade(false);
		}
	}

}

