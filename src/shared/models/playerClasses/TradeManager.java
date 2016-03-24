package shared.models.playerClasses;
import java.util.ArrayList;
import java.util.HashMap;

import shared.communication.proxy.OfferTrade;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.cardClasses.ResourceCards;
import shared.models.tradeClasses.*;

public class TradeManager {
	
	ArrayList<Trade> trades;
	GamePlayers players;
	OfferTrade trade;
	boolean tradeOffered = false;

	/**This class handles the specifics of trades made between players*/
	public TradeManager(GamePlayers players) {
		trades = new ArrayList<Trade>();
		this.players = players;
	}
	
	public void importTradeManager(String json) {
		
	}
	
	public void offerTrade(OfferTrade trade) {
		tradeOffered = true;
		this.trade = trade;
	}
	
	public OfferTrade getTrade() {
		return trade;
	}
	
	public boolean isTradeOffered() {
		return tradeOffered;
	}
	
	public void rejectTrade() {
		tradeOffered = false;
		trade = null;
	}
	
	
	/**
	 * Verifies that the players are able to trade the proposed trade
	 * @return A boolean representing whether the trade is a valid trade.
	 */
	public boolean canTrade(int traderIndex, int tradeeIndex, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) {
		//it must be the trader's turn
		if(!players.getPlayerByIndex(traderIndex).isTurn()) {
			return false;
		}
		
		//check if each part of the trade is consistent with what each player has in his/her hand.
		for(ResourceType type : out.keySet()) {
			int num = out.get(type);
			if(players.getPlayerByIndex(traderIndex).getNumOfResource(type) < num) {
				return false;
			}
		}
		
		for(ResourceType type : in.keySet()) {
			int num = in.get(type);
			if(players.getPlayerByIndex(tradeeIndex).getNumOfResource(type) < num) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Exchange two player's resources
	 * @throws InsufficientCardNumberException 
	 */
	public void ExecuteTrade(int traderIndex, int tradeeIndex, ResourceCards out, ResourceCards in) throws InsufficientCardNumberException {
		tradeOffered = false;
		//move the trader's resources to the tradee
		Player trader = players.getPlayerByIndex(traderIndex);
		Player tradee = players.getPlayerByIndex(tradeeIndex);
		trader.removeResourceFromHand(ResourceType.BRICK, out.getBrickCards());
		tradee.addResourceToHand(ResourceType.BRICK, out.getBrickCards());
		trader.removeResourceFromHand(ResourceType.ORE, out.getOreCards());
		tradee.addResourceToHand(ResourceType.ORE, out.getOreCards());
		trader.removeResourceFromHand(ResourceType.SHEEP, out.getSheepCards());
		tradee.addResourceToHand(ResourceType.SHEEP, out.getSheepCards());
		trader.removeResourceFromHand(ResourceType.WHEAT, out.getWheatCards());
		tradee.addResourceToHand(ResourceType.WHEAT, out.getWheatCards());
		trader.removeResourceFromHand(ResourceType.WOOD, out.getWoodCards());
		tradee.addResourceToHand(ResourceType.WOOD, out.getWoodCards());
		
		//move the tradee's resources to the trader
		tradee.removeResourceFromHand(ResourceType.BRICK, in.getBrickCards());
		trader.addResourceToHand(ResourceType.BRICK, in.getBrickCards());
		tradee.removeResourceFromHand(ResourceType.ORE, in.getOreCards());
		trader.addResourceToHand(ResourceType.ORE, in.getOreCards());
		tradee.removeResourceFromHand(ResourceType.SHEEP, in.getSheepCards());
		trader.addResourceToHand(ResourceType.SHEEP, in.getSheepCards());
		tradee.removeResourceFromHand(ResourceType.WHEAT, in.getWheatCards());
		trader.addResourceToHand(ResourceType.WHEAT, in.getWheatCards());
		tradee.removeResourceFromHand(ResourceType.WOOD, in.getWoodCards());
		trader.addResourceToHand(ResourceType.WOOD, in.getWoodCards());
		
		trade = null;
	}
	
	/**
	 * Exchange four of this player's resources for
	 * one of another.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeFour(int traderIndex, ResourceType tradeIn, ResourceType tradeOut) throws InsufficientCardNumberException {
		players.getPlayerByIndex(traderIndex).removeResourceFromHand(tradeIn, 4);
		players.getPlayerByIndex(traderIndex).addResourceToHand(tradeOut, 1);
	}
	
	/**
	 * Trade this player's resources using a harbor
	 * he/she has built on.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeTwoWithPort(int traderIndex, ResourceType portType, ResourceType returnType) throws InsufficientCardNumberException {
		players.getPlayerByIndex(traderIndex).removeResourceFromHand(portType, 2);
		players.getPlayerByIndex(traderIndex).addResourceToHand(returnType, 1);
	}
	
	/**
	 * Trade this player's resources using a harbor
	 * he/she has built on.
	 * @throws InsufficientCardNumberException 
	 */
	public void tradeThreeWithPort(int traderIndex, ResourceType portType, ResourceType returnType) throws InsufficientCardNumberException {
		players.getPlayerByIndex(traderIndex).removeResourceFromHand(portType, 3);
		players.getPlayerByIndex(traderIndex).addResourceToHand(returnType, 1);	
	}

}
