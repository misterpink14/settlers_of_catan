package client.clientFacade;

/** ClientFacade class
 * 
 * @author Bo Pace
 *
 */
public class ClientFacade {

	/**
	 * This function will facilitate the creation of a
	 * user. The ClientFacade class will interface with
	 * the server proxy to communicate with the server
	 * and create the user.
	 */
	public void createPlayer() {
		
	}
	
	/**
	 * This function will interface with the server
	 * proxy in order to log a player into the server.
	 */
	public void login() {
		
	}
	
	/** This function will query the model to make sure
	 * it's the user's turn before they can do anything.
	 * @return Returns true if it's the player's turn,
	 * otherwise, returns false;
	 */
	public boolean isTurn() {
		/* Check if it's the player's turn */
		return true;
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn and if they are able to roll
	 * the dice.
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice() {
		if (isTurn()) {
			/* Do something */
		}
		/* Check if player can roll */
		return true;
	}
	
	/**
	 * If it's the beginning of the user's turn, they
	 * can roll the dice. First, check if the user can
	 * roll. Then, interface with the server proxy to
	 * send the command.
	 */
	public void rollDice() {
		if (canRollDice()) {
			/* Do something */
		}
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn, if they have the resources
	 * to build a road or not, and if they've chosen
	 * a valid place to put the road.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildRoad() {
		if (isTurn()) {
			/* Do something */
		}
		/* Check if player can build road */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a road on the board.
	 */
	public void buildRoad() {
		
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn, if they have the resources
	 * to build a city or not, and if they've chosen
	 * a valid place to put the city.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildCity() {
		if (isTurn()) {
			/* Do something */
		}
		/* Check if player can build city */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a city on the board.
	 */
	public void buildCity() {
		
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn, if they have the resources
	 * to build a settlement or not, and if they've
	 * chosen a valid place to put the settlement.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuildSettlement() {
		if (isTurn()) {
			/* Do something */
		}
		/* Check if player can build settlement */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a settlement on the board.
	 */
	public void buildSettlement() {
		
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn and if they have the resources
	 * to buy a development card or not.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuyDevelCard() {
		if (isTurn()) {
			/* Do something */
		}
		/* Check if player can buy development card */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a settlement on the board.
	 */
	public void buyDevelCard() {
		
	}
	
	/**
	 * Send the command to the server proxy to trade
	 * with a player.
	 */
	public void tradeWithPlayer() {
		
	}
	
	/**
	 * Send the command to the server proxy to trade
	 * in four of a kind for another resource
	 */
	public void tradeFour() {
		
	}
	
	/**
	 * Send the command to the server proxy to trade
	 * with an adjoining harbor
	 */
	public void tradeHarbor() {
		
	}
	
	/**
	 * Send the command to the server proxy to play
	 * a development card.
	 */
	public void playDevelCard() {
		
	}
	
	/**
	 * Send the end turn command to the server proxy.
	 */
	public void endTurn() {
		
	}
	
	/**
	 * Send a message to the server proxy in order to
	 * deliver it to another player.
	 */
	public void sendMessage() {
		
	}
}
