package shared.models;
import java.util.ArrayList;

import shared.models.tradeClasses.*;

public class TradeManager {
	
	ArrayList<Trade> trades = new ArrayList<Trade>();

	/**This class handles the specifics of trades made between players*/
	public TradeManager() {}
	
	/**
	 * Verifies that the players are able to trade the proposed trade
	 * @return A boolean representing whether the trade is a valid trade.
	 */
	public boolean canTrade() {return false;}
	
	/**
	 * Adds a new Trade object to the list
	 */
	public void createTrade() {}
	
	/**
	 * Exchange two player's resources
	 */
	public void ExecuteTrade() {}

}
