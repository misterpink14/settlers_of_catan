package shared.communication.proxy;
import java.util.ArrayList;

import shared.definitions.ResourceType;

public class OfferTrade
{

	/**
	 * The index of the player offering the trade
	 */
	public int playerIndex;
	
	/**
	 * The index of the player receiving the offer
	 */
	public int receiverIndex;
	
	/**
	 * The resources the player wants to get
	 */
	public ArrayList<ResourceType> playerGets;
	
	/**
	 * The resources the player wants to give
	 */
	public ArrayList<ResourceType> playerGive;
	
	public OfferTrade() 
	{}


	
}
