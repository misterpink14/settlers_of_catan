package client.clientFacade;

import java.util.HashMap;
import java.util.List;

import client.serverProxy.FakeProxy;
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
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.models.Game;
import shared.models.UserManager.User;
import shared.models.cardClasses.InsufficientCardNumberException;

/** ClientFacade class
 * 
 * @author Bo Pace
 *
 */
public class ClientFacade {
	
	User clientUser;
	public Game game;
	public FakeProxy proxy;
	
	public ClientFacade() {
		
	}

	/**
	 * This function will facilitate the creation of a
	 * user. The ClientFacade class will interface with
	 * the server proxy to communicate with the server
	 * and create the user.
	 */
	public void createPlayer() {
		proxy.registerUser(new Credentials());
	}
	
	/**
	 * This function will interface with the server
	 * proxy in order to log a player into the server.
	 * @ return Returns a user object to be used when
	 * performing other actions on the server.
	 */
	public void login(Credentials credentials) {
		proxy.loginUser(credentials);
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
		if (!game.CanRollNumber()) {
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
			proxy.rollNumber(new RollNumber());
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
	public boolean canBuildRoad(int x, int y, String direction, int ownerId) {
		if (!game.CanBuildRoad(x, y, direction, ownerId)) {
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
	public void buildRoad(int x, int y, String direction, int ownerId) {
		if (canBuildRoad(x, y, direction, ownerId)) {
			proxy.buildRoad(new BuildRoad());
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
	public boolean canBuildCity(int x, int y, String direction, int ownerId) {
		if (!game.CanBuildCity(x, y, direction, ownerId)) {
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
	public void buildCity(int x, int y, String direction, int ownerId) {
		if (canBuildCity(x, y, direction, ownerId)) {
			proxy.buildCity(new BuildCity());
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
	public boolean canBuildSettlement(int x, int y, String direction, int ownerId) {
		if (!game.CanBuildSettlement(x, y, direction, ownerId)) {
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
	public void buildSettlement(int x, int y, String direction, int ownerId) {
		if (canBuildSettlement(x, y, direction, ownerId)) {
			proxy.buildSettlement(new BuildSettlement());
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
		if (!game.CanBuyDevCard()) {
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
			proxy.buyDevCard(new BuyDevCard());
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
		proxy.offerTrade(new OfferTrade());
	}
	
	/**
	 * Send the command to the server proxy to trade
	 * in four of a kind for another resource
	 * @throws
	 */
	public void tradeFour(int playerID, List<Integer> playersToOfferTo, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) throws InsufficientCardNumberException {
		game.offerATrade(playerID, playersToOfferTo, out, in);
	}
	
	/**
	 * @throws Exception 
	 * Send the command to the server proxy to trade
	 * with an adjoining harbor
	 * @throws
	 */
	public void tradeHarbor() throws Exception {
		if(isTurn()) {
			proxy.maritimeTrade(new MaritimeTrade());
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * @throws InsufficientCardNumberException 
	 * Send the command to the server proxy to play
	 * a development card.
	 * @throws
	 */
	public void playDevCard(DevCardType type) throws InsufficientCardNumberException {
		game.playDevCard(type);
	}
	
	/**
	 * @throws Exception 
	 * Send the end turn command to the server proxy.
	 * @throws
	 */
	public void finishTurn() throws Exception {
		if(game.CanFinishTurn()) {
			proxy.finishTurn(new FinishTurn());
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * @throws Exception 
	 * Send a message to the server proxy in order to
	 * deliver it to another player.
	 * @throws
	 */
	public void sendChat() throws Exception {
		if(game.CanSendChat()) {
			proxy.sendChat(new SendChat());
		} else {
			throw new Exception();
		}
	}
}
