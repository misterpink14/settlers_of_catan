package client.clientFacade;

import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import client.data.PlayerInfo;
import client.data.RobPlayerInfo;
import client.serverPoller.ServerPoller;
import client.serverProxy.ProxyInterface;
import shared.communication.proxy.BuildCity;
import shared.communication.proxy.BuildRoad;
import shared.communication.proxy.BuildSettlement;
import shared.communication.proxy.BuyDevCard;
import shared.communication.proxy.CreateGameRequestParams;
import shared.communication.proxy.Credentials;
import shared.communication.proxy.DiscardedCards;
import shared.communication.proxy.FinishTurn;
import shared.communication.proxy.JoinGameRequestParams;
import shared.communication.proxy.MaritimeTrade;
import shared.communication.proxy.Monopoly;
import shared.communication.proxy.MonumentMove;
import shared.communication.proxy.OfferTrade;
import shared.communication.proxy.RobPlayer;
import shared.communication.proxy.RollNumber;
import shared.communication.proxy.SendChat;
import shared.communication.proxy.SoldierMove;
import shared.communication.proxy.YearOfPlenty;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.RobberLocation;
import shared.locations.VertexLocation;
import shared.models.Game;
import shared.models.UserManager.User;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.models.mapClasses.Hex;
import shared.models.mapClasses.Piece;
import shared.models.playerClasses.Player;

/** ClientFacade class
 * 
 * @author Bo Pace
 *
 */
public class ClientFacade {
	
	public static ClientFacade instance;
	
	static User clientUser;
	public  Game game;
	public ProxyInterface proxy;
	private ServerPoller poller;
	
	public ClientFacade() {}
	
	public static ClientFacade getInstance() {
		if(instance == null) {
			instance = new ClientFacade();
		}
		return instance;
	}
	
	public void setup(Game game, ProxyInterface proxy) {
		this.game = game;
		this.proxy = proxy;
	}
	
	public void startPoller() {
		poller = new ServerPoller(game, proxy, 1000);
	}

	/**
	 * This function will facilitate the creation of a
	 * user. The ClientFacade class will interface with
	 * the server proxy to communicate with the server
	 * and create the user.
	 * @return Returns status from proxy
	 */
	public String createPlayer(Credentials cred) {
		try {
			return proxy.registerUser(cred);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "False";
	}
	
	/**
	 * This function will interface with the server
	 * proxy in order to log a player into the server.
	 * @ return Returns status from proxy
	 */
	public String login(Credentials credentials) {
		try {
			return proxy.loginUser(credentials);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "False";
	}
	
	/**
	 * creates a new game
	 * @throws Exception 
	 */
	public String createGame(CreateGameRequestParams params) throws Exception {
		return this.proxy.createGame(params);
	}
	
	public String joinGame(JoinGameRequestParams params) throws Exception {
		return proxy.joinGame(params);
	}
	
	/** This function will query the model to make sure
	 * it's the user's turn before they can do anything.
	 * @return Returns true if it's the player's turn,
	 * otherwise, returns false;
	 */
	public boolean isTurn() {
		return game.isTurn(getUserData().getPlayerIndex());
	}
	
	public String getGamesList() throws Exception {
		try {
			return proxy.getGamesList();
		} catch (Exception e) {
			throw new Exception("Error retrieving list of games");
		}
	}
	
	/**
	 * Checks with the model to see if it's the
	 * player's turn and if they are able to roll
	 * the dice.
	 * @return returns true if they can, false if
	 * they can't
	 */
	public boolean canRollDice() {
		if (!game.CanRollNumber(this.getUserData().getPlayerIndex())) {
			return false;
		}
		return true;
	}
	
	/**
	 * If it's the beginning of the user's turn, they
	 * can roll the dice. First, check if the user can
	 * roll. Then, interface with the server proxy to
	 * send the command.
	 * @return 
	 * @throws 
	 */
	public int rollDice() throws Exception {
		if (canRollDice()) {
			try {
				RollNumber r = new RollNumber(ClientFacade.getInstance().getUserData().getPlayerIndex());
				proxy.rollNumber(r);
				return r.getRoll();
			} catch (Exception e) {
				throw new Exception("Error rolling dice");
			}
		} else {
			return -1;
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
	public boolean canBuildRoad(EdgeLocation edgeLoc) {
		return this.canBuildRoad(edgeLoc, false);
	}
	
	public boolean canBuildRoad(EdgeLocation edgeLoc, boolean isFree) {
		return this.canBuildRoad(edgeLoc, isFree, false);
	}
	
	public boolean canBuildRoad(EdgeLocation edgeLoc, boolean isFree, boolean isSetup) {
		return game.CanBuildRoad(edgeLoc, this.getUserData().getPlayerIndex(), isFree, isSetup);// {
//			return false;
//		}
//		return true;
	}
	
	
	/**
	 * Send the command to the server proxy to build
	 * a road on the board.
	 * @throws
	 */
	public String buildRoad(EdgeLocation edgeLoc) throws Exception { // Used during Gameplay
		return this.buildRoad(edgeLoc, false);
	}
	
	public String buildRoad(EdgeLocation edgeLoc, boolean isFree) throws Exception { // Used for playing the RoadBuilder card
		return this.buildRoad(edgeLoc, isFree, false);
	}
	
	public String buildRoad(EdgeLocation edgeLoc, boolean isFree, boolean isSetup) throws Exception { // Used in Setup1 and Setup2
		if (canBuildRoad(edgeLoc, isFree, isSetup)) {
			try {
				BuildRoad buildRoad = new BuildRoad(edgeLoc, isFree);
				buildRoad.playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
				return proxy.buildRoad(buildRoad);
			} catch (Exception e) {
				throw new Exception("Error building road");
			}
		} else {
			return "failure";
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
	public boolean canBuildCity(VertexLocation vertLoc) {
		if (!game.CanBuildCity(vertLoc, this.getUserData().getId())) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Send the command to the server proxy to build
	 * a city on the board.
	 * @throws
	 */
	public String buildCity(VertexLocation vertLoc) throws Exception {
		if (canBuildCity(vertLoc)) {
			try {
				return proxy.buildCity(new BuildCity());
			} catch (Exception e) {
				throw new Exception("Error building city");
			}
		} else {
			return "failure";
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
	public boolean canBuildSettlement(VertexLocation vertLoc) {
		return this.canBuildSettlement(vertLoc, true);
	}
	
	public boolean canBuildSettlement(VertexLocation vertLoc, boolean isFree) {
		return this.canBuildSettlement(vertLoc, isFree, true);
	}
	
	public boolean canBuildSettlement(VertexLocation vertLoc, boolean isFree, boolean isSetup) {
		if (!game.CanBuildSettlement(vertLoc, this.getUserData().getPlayerIndex(), isFree, isSetup)) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Send the command to the server proxy to build
	 * a settlement on the board.
	 * @throws
	 */
	public String buildSettlement(VertexLocation vertLoc) throws Exception { // Used during Gameplay
		return this.buildSettlement(vertLoc, false);
	}
	
	public String buildSettlement(VertexLocation vertLoc, boolean isFree) throws Exception { // Used for playing the RoadBuilder card
		return this.buildSettlement(vertLoc, isFree, false);
	}
	
	public String buildSettlement(VertexLocation vertLoc, boolean isFree, boolean isSetup) throws Exception {
		if (canBuildSettlement(vertLoc, isFree, isSetup)) {
			try {
				BuildSettlement buildSettlement = new BuildSettlement();
				buildSettlement.free = isFree;
				buildSettlement.playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
				buildSettlement.vertexLocation = vertLoc;
				return proxy.buildSettlement(buildSettlement);
			} catch (Exception e) {
				throw new Exception("Error building settlement");
			}
		} else {
			return "failure";
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
		if (!game.CanBuyDevCard(this.getUserData().getPlayerIndex())) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Send the command to the server proxy to build
	 * a settlement on the board.
	 * @throws
	 */
	public String buyDevCard() throws Exception {
		if (canBuyDevCard()) {
			try {
				return proxy.buyDevCard(new BuyDevCard());
			} catch (Exception e) {
				throw new Exception("Error buying dev card");
			}
		} else {
			return "failure";
		}
	}
	
	
	/**
	 * Send the command to the server proxy to offer
	 * a trade with another player.
	 * @throw
	 */
	public String offerTrade() throws Exception {
		try {
			return proxy.offerTrade(new OfferTrade());
		} catch (Exception e) {
			throw new Exception("Error offering trade");
		}
	}
	
	/**
	 * Send the command to the server proxy to discard
	 * the selected cards
	 * @throw
	 */
	public String discardCards(int sheepDiscard, int woodDiscard, int brickDiscard, int wheatDiscard, int oreDiscard) throws Exception {
		try {
			return proxy.discardCards(new DiscardedCards(getUserData().getPlayerIndex(), sheepDiscard, woodDiscard, oreDiscard, wheatDiscard, brickDiscard));
		} catch (Exception e) {
			throw new Exception("Error discarding cards");
		}
	}
	
	
	/**
	 * Send the command to the server proxy to trade
	 * in four of a kind for another resource
	 * @throws
	 */
	public void tradeFour(List<Integer> playersToOfferTo, HashMap<ResourceType, Integer> out, HashMap<ResourceType, Integer> in) throws InsufficientCardNumberException {
		game.offerATrade(this.getUserData().getPlayerIndex(), playersToOfferTo, out, in);
	}
	
	
	/**
	 * @param ratio 
	 * @param getResource 
	 * @param giveResource 
	 * @throws Exception 
	 * Send the command to the server proxy to trade
	 * with an adjoining harbor
	 * @throws
	 */
	public String tradeHarbor(ResourceType givingUp, ResourceType getting, int ratio) throws Exception {
		if(isTurn()) {
			try {
				return proxy.maritimeTrade(new MaritimeTrade(givingUp, getting, ratio, getUserData().getPlayerIndex()));
			} catch (Exception e) {
				throw new Exception("Error trading with harbor");
			}
		} else {
			return "failure";
		}
	}
	
	
	/**
	 * @throws Exception 
	 * @throws InsufficientCardNumberException 
	 * Send the command to the server proxy to play
	 * a development card.
	 * @throws
	 */
	public void playDevCard(DevCardType type, ResourceType type1, ResourceType type2) throws Exception {
		switch(type) {
		case MONOPOLY:
			Monopoly monopoly = new Monopoly();
			monopoly.playerIndex = getUserData().getPlayerIndex();
			monopoly.resource = type1;
			proxy.playMonopolyCard(monopoly);
			break;
		case MONUMENT:
			MonumentMove monumentMove = new MonumentMove();
			monumentMove.playerIndex = getUserData().getPlayerIndex();
			proxy.playMonumentCard(monumentMove);
			break;
		case ROAD_BUILD:
			break;
		case SOLDIER:
			break;
		case YEAR_OF_PLENTY:
			YearOfPlenty yearOfPlenty = new YearOfPlenty();
			yearOfPlenty.playerIndex = getUserData().getPlayerIndex();
			yearOfPlenty.resourceOne = type1;
			yearOfPlenty.resourceTwo = type2;
			proxy.yearOfPlenty(yearOfPlenty);
			break;
		default:
			break;
		
		}
	}
	
	
	/**
	 * @throws Exception 
	 * Send the end turn command to the server proxy.
	 * @throws
	 */
	public String finishTurn() throws Exception {
		if(game.CanFinishTurn()) {
			try {
				FinishTurn finishTurn = new FinishTurn();
				finishTurn.playerIndex = ClientFacade.getInstance().getUserData().getPlayerIndex();
				return proxy.finishTurn(finishTurn);
			} catch (Exception e) {
				throw new Exception("Error finishing turn");
			}
		} else {
			return "failure";
		}
	}
	
	
	/**
	 * @param message 
	 * @throws Exception 
	 * Send a message to the server proxy in order to
	 * deliver it to another player.
	 * @throws
	 */
	public String sendChat(String message) {
		if(game.CanSendChat()) {
			try {
				return proxy.sendChat(new SendChat(getUserData().getPlayerIndex(), message));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//throw new Exception();
		}
		return "False";
	}
	
	
	/**
	 * gets an object with the information about the current user.
	 * @return
	 */
	public PlayerInfo getUserData() {
		PlayerInfo loginUser = new PlayerInfo();
		JsonParser parser = new JsonParser();
		JsonObject loginUserJson = parser.parse(proxy.getUserCookie()).getAsJsonObject();
		loginUser.setId(loginUserJson.get("playerID").getAsInt());
		loginUser.setName(loginUserJson.get("name").getAsString());
		Player p = game.getPlayers().getPlayerByID(loginUser.getId());
		if (p != null) {
			loginUser.setColor(p.getColor());
			loginUser.setPlayerIndex(p.getIndex());
		}
		return loginUser;
	}
	
	
	/**
	 * Get the hex from a coordinate
	 * @param HexLocation
	 * @return
	 */
	public Hex getHex(HexLocation loc) {
		return this.game.getHex(loc);
	}
	
	public Piece getEdge(EdgeLocation loc) {
		return this.game.getEdge(loc);
	}
	
	public Piece getVertex(VertexLocation loc) {
		return this.game.getVertex(loc);
	}
	
	public CatanColor getColorById(int playerIndex) { // this is actually the index
		return this.game.getPlayers().getPlayerByIndex(playerIndex).getColor();
	}
	
	public CatanColor getUserColor() {
		return this.getColorById(this.getUserData().getPlayerIndex());
	}
	
	public RobberLocation getRobberLocation() {
		return this.game.getRobberLocation();
	}
	
	public int getNumOfPlayerResource(ResourceType resourceType) {
		Player currentPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByID(
				ClientFacade.getInstance().getUserData().getId());
		
		return currentPlayer.getNumOfResource(resourceType);
	}
	
	public int getNumOfPlayerRoads() {
		Player currentPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByID(
				ClientFacade.getInstance().getUserData().getId());
		
		return currentPlayer.getRoads();
	}
	
	public int getPlayerWithMostRoads() {
		int index = -1;
		int highestnumber = 0;
		for(Player p : game.getPlayers().getPlayers()) {
			if (p.getRoads() > highestnumber) {
				highestnumber = p.getRoads();
				index = p.getIndex();
			}
		}
		
		return index;
	}
	
	public int getNumOfPlayerSettlements() {
		Player currentPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByID(
				ClientFacade.getInstance().getUserData().getId());
		
		return currentPlayer.getSettlements();
	}
	
	public int getNumOfPlayerCities() {
		Player currentPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByID(
				ClientFacade.getInstance().getUserData().getId());
		
		return currentPlayer.getCities();
	}
	
	public int getNumOfPlayerSoldiers() {
		Player currentPlayer = ClientFacade.getInstance().game.getPlayers().getPlayerByID(
				ClientFacade.getInstance().getUserData().getId());
		
		return currentPlayer.getArmy();
	}

	public Boolean settlementTouchesPlayerRoad(VertexLocation loc) {	
		return this.game.settlementTouchesPlayerRoad(loc, this.getUserData().getPlayerIndex());
	}
	
	public boolean canPlaceRobber(HexLocation hexLoc) {
		return getInstance().game.CanPlaceRobber(hexLoc, getUserData().getPlayerIndex());
	}

	public void placeRobber(HexLocation hexLoc) throws Exception {
		SoldierMove move = new SoldierMove();
		move.newLocation = hexLoc;
		move.playerIndex = getUserData().getPlayerIndex();
		this.proxy.moveSoldier(move);
	}
	
	public void robPlayer(int victim, HexLocation hexLoc) throws Exception {
		RobPlayer rob = new RobPlayer();
		rob.playerIndex = getUserData().getPlayerIndex();
		rob.victimIndex = victim;
		rob.newLocation = hexLoc;
		proxy.robPlayer(rob);
	}
}
