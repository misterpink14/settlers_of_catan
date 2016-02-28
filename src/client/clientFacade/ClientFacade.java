package client.clientFacade;

import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import client.data.PlayerInfo;
import client.serverPoller.ServerPoller;
import client.serverProxy.ProxyInterface;
import shared.communication.proxy.BuildCity;
import shared.communication.proxy.BuildRoad;
import shared.communication.proxy.BuildSettlement;
import shared.communication.proxy.BuyDevCard;
import shared.communication.proxy.CreateGameRequestParams;
import shared.communication.proxy.Credentials;
import shared.communication.proxy.FinishTurn;
import shared.communication.proxy.JoinGameRequestParams;
import shared.communication.proxy.MaritimeTrade;
import shared.communication.proxy.OfferTrade;
import shared.communication.proxy.RollNumber;
import shared.communication.proxy.SendChat;
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
		return game.isTurn(0);
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
	public boolean canBuildRoad(int x, int y, String direction) {
		if (!game.CanBuildRoad(x, y, direction, this.getUserData().getPlayerIndex())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a road on the board.
	 * @throws
	 */
	public String buildRoad(int x, int y, String direction) throws Exception {
		if (canBuildRoad(x, y, direction)) {
			try {
				return proxy.buildRoad(new BuildRoad());
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
	public boolean canBuildCity(int x, int y, String direction) {
		if (!game.CanBuildCity(x, y, direction, this.getUserData().getId())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a city on the board.
	 * @throws
	 */
	public String buildCity(int x, int y, String direction) throws Exception {
		if (canBuildCity(x, y, direction)) {
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
	public boolean canBuildSettlement(int x, int y, String direction) {
		if (!game.CanBuildSettlement(x, y, direction, this.getUserData().getPlayerIndex())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Send the command to the server proxy to build
	 * a settlement on the board.
	 * @throws
	 */
	public String buildSettlement(int x, int y, String direction) throws Exception {
		if (canBuildSettlement(x, y, direction)) {
			try {
				return proxy.buildSettlement(new BuildSettlement());
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
				return proxy.maritimeTrade(new MaritimeTrade(givingUp, getting, ratio, clientUser.getPlayerID()));
			} catch (Exception e) {
				throw new Exception("Error trading with harbor");
			}
		} else {
			return "failure";
		}
	}
	
	/**
	 * @throws InsufficientCardNumberException 
	 * Send the command to the server proxy to play
	 * a development card.
	 * @throws
	 */
	public void playDevCard(DevCardType type) throws InsufficientCardNumberException {
		game.playDevCard(type, this.getUserData().getPlayerIndex());
	}
	
	/**
	 * @throws Exception 
	 * Send the end turn command to the server proxy.
	 * @throws
	 */
	public String finishTurn() throws Exception {
		if(game.CanFinishTurn()) {
			try {
				return proxy.finishTurn(new FinishTurn());
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
}
