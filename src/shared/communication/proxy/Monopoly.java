package shared.communication.proxy;
import shared.definitions.ResourceType;

public class Monopoly
{

	/**
	 * The index of the player playing that card
	 */
	public int playerIndex;
	
	/**
	 * The resource they want to monopolize
	 */
	public ResourceType resource;
	
	public Monopoly() 
	{}

	public Monopoly(int playerIndex, ResourceType resource) {
		this.playerIndex = playerIndex;
		this.resource = resource;
	}

	
	
}
