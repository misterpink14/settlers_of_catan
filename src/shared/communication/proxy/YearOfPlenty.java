package shared.communication.proxy;
import shared.definitions.ResourceType;

public class YearOfPlenty
{

	/**
	 * The index of the player playing the card
	 */
	public int playerIndex;
	
	/**
	 * The first resource you want
	 */
	public ResourceType resourceOne;
	
	/**
	 * The second resource you want
	 */
	public ResourceType resourceTwo;
	
	public YearOfPlenty() 
	{}

	public YearOfPlenty(int playerIndex, ResourceType resourceOne, ResourceType resourceTwo) {
		this.playerIndex = playerIndex;
		this.resourceOne = resourceOne;
		this.resourceTwo = resourceTwo;
	}
	
	

	
}
