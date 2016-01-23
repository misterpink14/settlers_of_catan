package shared.models;

import java.util.ArrayList;

import shared.models.playerClasses.Player;

public class GamePlayers {
	
	/**The players array contains four players that will be used by four clients.*/
	ArrayList<Player> players = new ArrayList<Player>();

	public GamePlayers(String json) {}

	/**
	 * This accepts a client and adds it as a player object to an array.
	 */
	public void addPlayer() {
		
	}
	
	/** This function checks a specific player to see
	 * if it is currently his/her turn.
	 * @return Returns true if it's the player's turn,
	 * otherwise, returns false;
	 */
	public boolean isTurn(int playerID) {
		return true;
	}
	
	/**
	 * Checks if the specified player can roll
	 * the dice.
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice(int playerID) {
		return true;
	}
	
	/**
	 * Checks to see if a specified player can
	 * build a road at the specified location.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildRoad(int playerID) {
		return true;
	}
	
	/**
	 * Checks to see if a specified player can
	 * build a settlement at the specified location.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildSettlement(int playerID) {
		return true;
	}
	
	/**
	 * Checks to see if a specified player can
	 * build a city at the specified location
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildCity(int playerID) {
		return true;
	}
	
	/**
	 * Checks if a specified player can buy a
	 * development card at this time.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuyDevCard(int playerID) {
		return true;
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 */
	public void buyRoad(int playerID) {
		
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 */
	public void buySettlement(int playerID) {
		
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 */
	public void buyCity(int playerID) {
		
	}
	
	/**
	 * Deducts resources from a specified player
	 * in order to buy a development card.
	 */
	public void buyDevCard(int playerID) {
		
	}
	
	/**
	 * Exchange four of one player's resources for
	 * one of another.
	 */
	public void tradeFour(int playerID) {
		
	}
	
	/**
	 * Trade a player's resources using a harbor
	 * he/she has built on.
	 */
	public void tradeHarbor(int playerID) {
		
	}
	
	/**
	 * Allows a player to move the robber in exchange for a Soldier Card
	 */
	public void playSoldierCard(int playerID) {}
	
	/**
	 * Allows a player to build two roads in exchange for a Road Builder Card
	 */
	public void playRoadBuilderCard(int playerID) {}
	
	/**
	 * Allows a player to take all owned cards of a specified resource in exchange for a Monopoly Card
	 */
	public void playMonopolyCard(int playerID) {}
	
	/**
	 * Allows a player to choose two resources to be added to it's hand in exchange for a Year Of Plenty Card
	 */
	public void playYearOfPlentyCard(int playerID) {}
	
	
	/**
	 * End a player's turn and mark that it is the
	 * next player's turn.
	 */
	public void finishTurn(int playerID) {
		
	}
}
