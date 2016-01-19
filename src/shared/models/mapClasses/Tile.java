package shared.models.mapClasses;

import shared.definitions.HexType;

public class Tile {
	/**This specifies what type of resource this tile on the board represents.*/
	HexType type;
	/**When the dice add up to this number, this tile will give its resource to any players with a settlement
	 * built on it.*/
	int numberAssigned;
	/**This boolean is true when the robber has been placed on this tile.*/
	boolean robberPresent = false;
	
	/**
	 * This class is a representation of a tile on the settlers of catan map. It is one of 34.
	 */
	public Tile(HexType type, int numberAssigned, boolean robberPresent) {
		this.type = type;
		this.numberAssigned = numberAssigned;
		this.robberPresent = robberPresent;
	}

}
