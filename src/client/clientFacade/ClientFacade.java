package client.clientFacade;

import client.serverProxy.ProxyInterface;
import shared.communication.proxy.BuildCity;
import shared.communication.proxy.BuildRoad;
import shared.communication.proxy.BuildSettlement;
import shared.communication.proxy.BuyDevCard;
import shared.communication.proxy.Credentials;
import shared.communication.proxy.FinishTurn;
import shared.communication.proxy.MaritimeTrade;
import shared.communication.proxy.OfferTrade;
import shared.communication.proxy.RollNumber;
import shared.communication.proxy.SendChat;
import shared.models.Game;
import shared.models.UserManager.User;

/** ClientFacade class
 * 
 * @author Bo Pace
 *
 */
public class ClientFacade {
	
	User clientUser;
	Game game;
	
	public ClientFacade() {
		
	}
	
	ProxyInterface proxy;

	/**
	 * This function will facilitate the creation of a
	 * user. The ClientFacade class will interface with
	 * the server proxy to communicate with the server
	 * and create the user.
	 */
	public void createPlayer() {
		proxy.postUserRegister(new Credentials());
	}
	
	/**
	 * This function will interface with the server
	 * proxy in order to log a player into the server.
	 * @ return Returns a user object to be used when
	 * performing other actions on the server.
	 */
	public void login(Credentials credentials) {
		proxy.postUserLogin(credentials);
	}
	
	/** This function will query the model to make sure
	 * it's the user's turn before they can do anything.
	 * @return Returns true if it's the player's turn,
	 * otherwise, returns false;
	 */
	public boolean isTurn() {
		return game.isTurn(clientUser.getPlayerID());
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn and if they are able to roll
	 * the dice.
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice() {
		if (!isTurn()) {
			return false;
		}
		/* Check if player can roll */
		return true;
	}
	
	/**
	 * If it's the beginning of the user's turn, they
	 * can roll the dice. First, check if the user can
	 * roll. Then, interface with the server proxy to
	 * send the command.
	 * @throws 
	 */
	public void rollDice() {
		if (canRollDice()) {
			proxy.postMovesRollNumber(new RollNumber());
		} else {
			/* Throw exception */
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
		if (!isTurn()) {
			return false;
		}
		/* Check if player can build road */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a road on the board.
	 * @throws
	 */
	public void buildRoad() {
		if (canBuildRoad()) {
			proxy.postMovesBuildRoad(new BuildRoad());
		} else {
			/* Throw exception */
		}
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
		if (!isTurn()) {
			return false;
		}
		/* Check if player can build city */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a city on the board.
	 * @throws
	 */
	public void buildCity() {
		if (canBuildCity()) {
			proxy.postMovesBuildCity(new BuildCity());
		} else {
			/* Throw exception */
		}
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
		if (!isTurn()) {
			return false;
		}
		/* Check if player can build settlement */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a settlement on the board.
	 * @throws
	 */
	public void buildSettlement() {
		if (canBuildSettlement()) {
			proxy.postMovesBuildSettlement(new BuildSettlement());
		} else {
			/* Throw exception */
		}
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn and if they have the resources
	 * to buy a development card or not.
	 * @return returns true if they can, false if
	 * they can't.
	 */
	public boolean canBuyDevCard() {
		if (isTurn()) {
			return false;
		}
		/* Check if player can buy development card */
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a settlement on the board.
	 * @throws
	 */
	public void buyDevCard() {
		if (canBuyDevCard()) {
			proxy.postMovesBuyDevCard(new BuyDevCard());
		} else {
			/* Throw exception */
		}
	}
	
	/**
	 * Send the command to the server proxy to offer
	 * a trade with another player.
	 * @throw
	 */
	public void offerTrade() {
		proxy.postMovesOfferTrade(new OfferTrade());
	}
	
	/**
	 * Send the command to the server proxy to trade
	 * in four of a kind for another resource
	 * @throws
	 */
	public void tradeFour() {
		
	}
	
	/**
	 * Send the command to the server proxy to trade
	 * with an adjoining harbor
	 * @throws
	 */
	public void tradeHarbor() {
		proxy.postMovesMaritimeTrade(new MaritimeTrade());
	}
	
	/**
	 * Send the command to the server proxy to play
	 * a development card.
	 * @throws
	 */
	public void playDevCard() {
		
	}
	
	/**
	 * Send the end turn command to the server proxy.
	 * @throws
	 */
	public void finishTurn() {
		proxy.postMovesFinishTurn(new FinishTurn());
	}
	
	/**
	 * Send a message to the server proxy in order to
	 * deliver it to another player.
	 * @throws
	 */
	public void sendChat() {
		proxy.postMovesSendChat(new SendChat());
	}
}
