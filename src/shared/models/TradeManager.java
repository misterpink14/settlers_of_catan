package shared.models;
import java.util.ArrayList;
import java.util.Map;

import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.playerClasses.GamePlayers;
import shared.models.tradeClasses.*;

public class TradeManager {
	
	ArrayList<Trade> trades;
	GamePlayers players;

	/**This class handles the specifics of trades made between players*/
	public TradeManager(GamePlayers players) {
		trades = new ArrayList<Trade>();
		this.players = players;
	}
	
	public void importTradeManager(String json) {
		
	}
	
	/**
	 * Verifies that the players are able to trade the proposed trade
	 * @return A boolean representing whether the trade is a valid trade.
	 */
	public boolean canTrade(int traderID, int tradeeID, Map<ResourceType, Integer> out, Map<ResourceType, Integer> in) {
		//it must be the trader's turn
		if(!players.isTurn(traderID)) {
			return false;
		}
		
		//check if each part of the trade is consistent with what each player has in his/her hand.
		for(ResourceType type : out.keySet()) {
			int num = out.get(type);
			if(players.getNumOfResource(traderID, type) < num) {
				return false;
			}
		}
		
		for(ResourceType type : in.keySet()) {
			int num = in.get(type);
			if(players.getNumOfResource(tradeeID, type) < num) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Adds a new Trade object to the list
	 */
	public void createTrade() {}
	
	/**
	 * Exchange two player's resources
	 * @throws InsufficientCardNumberException 
	 */
	public void ExecuteTrade(int traderID, int tradeeID, Map<ResourceType, Integer> out, Map<ResourceType, Integer> in) throws InsufficientCardNumberException {
		//move the trader's resources to the tradee
		for(ResourceType type : out.keySet()) {
			int num = out.get(type);
			players.removeResourceToHand(traderID, type, num);
			players.addResourceToHand(tradeeID, type, num);
		}
		
		//move the tradee's resources to the trader
		for(ResourceType type : in.keySet()) {
			int num = in.get(type);
			players.removeResourceToHand(tradeeID, type, num);
			players.addResourceToHand(traderID, type, num);
		}
	}

}
