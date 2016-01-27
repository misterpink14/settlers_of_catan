package shared.models.cardClasses;

import shared.definitions.ResourceType;

public class ResourceCards {

	/**The number of wood cards in this group*/
	int woodCards = 0;
	/**The number of brick cards in this group*/
	int brickCards = 0;
	/**The number of sheep cards in this group*/
	int sheepCards = 0;
	/**The number of wheat cards in this group*/
	int wheatCards = 0;
	/**The number of ore cards in this group*/
	int oreCards = 0;
	
	/**
	 * A container for holding development cards.
	 */
	public ResourceCards(int woodCards, int brickCards, int sheepCards, int wheatCards, int oreCards) {
		this.woodCards = woodCards;
		this.brickCards = brickCards;
		this.sheepCards = sheepCards;
		this.wheatCards = wheatCards;
		this.oreCards = oreCards;
	}

	public int getWoodCards() {
		return woodCards;
	}

	public int getBrickCards() {
		return brickCards;
	}

	public int getSheepCards() {
		return sheepCards;
	}

	public int getWheatCards() {
		return wheatCards;
	}

	public int getOreCards() {
		return oreCards;
	}
	
	/**
	 * Adds a card of a specific type to this group
	 * @param type the type of resource card to add
	 */
	public void addCard(ResourceType type, int num) {
		switch(type){
			case BRICK:
				this.brickCards += num;
				break;
			case ORE:
				this.oreCards += num;
				break;
			case SHEEP:
				this.sheepCards += num;
				break;
			case WHEAT:
				this.wheatCards += num;
				break;
			case WOOD:
				this.woodCards += num;
				break;
		}
	}
	
	/**
	 * Removes a card of a specific type from this group
	 * @param type the type of resource card to remove
	 * @exception InsufficientCardNumberException
	 */
	public void removeCard(ResourceType type, int num) throws InsufficientCardNumberException 
	{
		switch(type){
			case BRICK:
				if(getBrickCards() == 0){throw new InsufficientCardNumberException();}
				this.brickCards -= num;
				break;
			case ORE:
				if(getOreCards() == 0){throw new InsufficientCardNumberException();}
				this.oreCards -= num;
				break;
			case SHEEP:
				if(getSheepCards() == 0){throw new InsufficientCardNumberException();}
				this.sheepCards -= num;
				break;
			case WHEAT:
				if(getWheatCards() == 0){throw new InsufficientCardNumberException();}
				this.wheatCards -= num;
				break;
			case WOOD:
				if(getWoodCards() == 0){throw new InsufficientCardNumberException();}
				this.woodCards -= num;
				break;
		}
	}

}
