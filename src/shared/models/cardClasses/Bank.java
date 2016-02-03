package shared.models.cardClasses;

import shared.definitions.ResourceType;

public class Bank 
{	
	/**The container for the resource cards in the bank*/
	private ResourceCards resourceCards = new ResourceCards(19,19,19,19,19);

	/**
	 * The Bank keeps track of how many resource cards are without owners.
	 */
	public Bank() {}
	
	public void importBank(String json) {
		
	}
	
	/**
	 * Subtracts cards from the bank to give to a player
	 * @throws InsufficientCardNumberException 
	 */
	public void takeResourceCards(ResourceType type, int num) throws InsufficientCardNumberException {
		resourceCards.removeCard(type, num);
	}
	
	/**
	 * Adds cards to the bank from a player's hand
	 */
	public void AddResourceCards(ResourceType type, int num) {
		resourceCards.addCard(type, num);
	}
	
	/**Checks if there is sufficient resources of a specified in this type to remove the specified number.*/
	public boolean canRemove(ResourceType type, int num) {
		return resourceCards.canRemove(type, num);
	}

}
