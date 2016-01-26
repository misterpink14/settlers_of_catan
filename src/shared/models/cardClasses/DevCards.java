package shared.models.cardClasses;

import shared.definitions.DevCardType;

public class DevCards {
	
	/**The number of soldier cards in this group*/
	int soldierCards = 0;
	/**The number of year of plenty cards in this group*/
	int yearOfPlentyCards = 0;
	/**The number of monopoly cards in this group*/
	int monopolyCards = 0;
	/**The number of road builder cards in this group*/
	int roadBuilderCards = 0;
	/**The number of monument cards in this group*/
	int monumentCards = 0;

	/**
	 * A container for holding development cards.
	 */
	public DevCards(int soldierCards, int yearOfPlentyCards, int monopolyCards, int roadBuilderCards, int monumentCards) {
		this.soldierCards = soldierCards;
		this.yearOfPlentyCards = yearOfPlentyCards;
		this.monopolyCards = monopolyCards;
		this.roadBuilderCards = roadBuilderCards;
		this.monumentCards = monumentCards;
	}

	public int getSoldierCards() {
		return soldierCards;
	}

	public int getYearOfPlentyCards() {
		return yearOfPlentyCards;
	}

	public int getMonopolyCards() {
		return monopolyCards;
	}

	public int getRoadBuilderCards() {
		return roadBuilderCards;
	}

	public int getMonumentCards() {
		return monumentCards;
	}
	
	/**
	 * Adds a card of a specific type to this group
	 * @param type the type of development card to add
	 */
	public void addCard(DevCardType type) {
		switch(type){
			case MONOPOLY:
				this.monopolyCards++;
				break;
			case MONUMENT:
				this.monumentCards++;
				break;
			case ROAD_BUILD:
				this.roadBuilderCards++;
				break;
			case SOLDIER:
				this.soldierCards++;
				break;
			case YEAR_OF_PLENTY:
				this.yearOfPlentyCards++;
				break;
		}
	}
	
	/**
	 * Removes a card of a specific type from this group
	 * @param type the type of development card to remove
	 */
	public void removeCard(DevCardType type) {
		switch(type){
			case MONOPOLY:
				this.monopolyCards--;
				break;
			case MONUMENT:
				this.monumentCards--;
				break;
			case ROAD_BUILD:
				this.roadBuilderCards--;
				break;
			case SOLDIER:
				this.soldierCards--;
				break;
			case YEAR_OF_PLENTY:
				this.yearOfPlentyCards--;
				break;
		}
	}

}