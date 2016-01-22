package shared.communication.proxy;
import java.util.ArrayList;

import shared.definitions.ResourceType;;

public class DiscardedCards
{

	/**
	 * The index of the player discarding cards
	 */
	public int playerIndex;
	
	/**
	 * The cards they are discarded
	 */
	public ArrayList<ResourceType> resources;
	
	public DiscardedCards() 
	{}


	
}
