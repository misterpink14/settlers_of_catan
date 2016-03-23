package server.facade;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import server.ServerException;
import server.managers.GameManager;
import server.managers.User;
import server.managers.UserManager;
import shared.communication.proxy.AcceptTrade;
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
import shared.communication.proxy.RoadBuilding;
import shared.communication.proxy.RobPlayer;
import shared.communication.proxy.RollNumber;
import shared.communication.proxy.SendChat;
import shared.communication.proxy.SoldierMove;
import shared.communication.proxy.YearOfPlenty;
import shared.definitions.CatanColor;
import shared.models.Game;
import shared.models.chatClasses.GameChat;
import shared.models.chatClasses.Message;
import shared.models.playerClasses.GamePlayers;
import shared.models.playerClasses.Player;
import shared.serializerJSON.Serializer;

public class ServerFacade implements IServerFacade {
	
	UserManager userManager;
	GameManager gameManager;
	
	public ServerFacade() {
		userManager = new UserManager();
		gameManager = new GameManager();
	}
	
	
	@Override
	public String login(Credentials credentials) throws ServerException {
		userManager.login(credentials);
		return "Success";
	}

	@Override
	public String register(Credentials credentials) throws ServerException {
		userManager.register(credentials);
		return "Success";
	}

	@Override
	public String getGamesList() {
		
		ArrayList<Game> games = (ArrayList<Game>) gameManager.getGames();
		JsonArray gameListJson = new JsonArray();
		
		
		for (Game g: games) {
			JsonObject gameJson = new JsonObject();
			
			gameJson.addProperty("title", g.getTitle());
			gameJson.addProperty("id", g.getId());
			
			JsonArray playerListJson = new JsonArray();
			GamePlayers players = g.getPlayers();
			
			for (Player p: players.getPlayers()) {
				JsonObject playerJson = new JsonObject();
				playerJson.addProperty("color", p.getColor().name());
				playerJson.addProperty("name", p.getName());
				playerJson.addProperty("id", p.getID());
				playerListJson.add(playerJson);
			}
			gameJson.add("players", playerListJson);
			
			gameListJson.add(gameJson);
		}
		
		return gameListJson.toString();
	}

	@Override
	public String createGame(CreateGameRequestParams params) {
		
		Game g = new Game(params); //Change this to the newly created game
		this.gameManager.addGame(g);
		
		JsonObject gameJson = new JsonObject();
		
		gameJson.addProperty("title", g.getTitle());
		gameJson.addProperty("id", g.getId());
		
		JsonArray playerListJson = new JsonArray();
		GamePlayers players = g.getPlayers();
		
		for (Player p: players.getPlayers()) {
			JsonObject playerJson = new JsonObject();
			playerJson.addProperty("color", p.getColor().name());
			playerJson.addProperty("name", p.getName());
			playerJson.addProperty("id", p.getID());
			playerListJson.add(playerJson);
		}
		gameJson.add("players", playerListJson);
		
		return gameJson.toString();
	}

	@Override
	public String joinGame(JoinGameRequestParams params, Credentials credentials) throws ServerException {
		Game game = this.gameManager.getGameByID(params.id);
		Player newPlayer = new Player(credentials.playerID, credentials.username, CatanColor.valueOf(params.color), game.getPlayers().getPlayers().size());
		try {
			game.getPlayers().addPlayer(newPlayer);
		} catch (Exception e) {
			throw new ServerException("Error adding player");
		}
		return "Success";
	}

	@Override
	public String getModel(int versionNumber, int gameID) {
		Game game = this.gameManager.getGameByID(gameID);
		if (game.getVersionID() == versionNumber) {
			return "\"true\"";
		} else {
			return Serializer.getInstance().serialize(game);
		}
	}

	@Override
	public String sendChat(SendChat sendChat, int gameID) {
		Game game = this.gameManager.getGameByID(gameID);
		GameChat chat = game.getGameChat();
		Player player = game.getPlayers().getPlayerByIndex(sendChat.playerIndex);
		chat.addMessage(new Message(player.getName(), sendChat.content));
		game.setGameChat(chat);
		this.gameManager.addGame(game);
		return Serializer.getInstance().serialize(game);
	}

	@Override
	public String rollNumber(RollNumber rollNumber, int gameID) {
		Game game = this.gameManager.getGameByID(gameID);
		game.processRoll(rollNumber.roll);
		
		return Serializer.getInstance().serialize(game);
	}

	@Override
	public String robPlayer(RobPlayer robPlayer, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String finishTurn(FinishTurn finishTurn, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buyDevCard(BuyDevCard buyDevCard, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String yearOfPlenty(YearOfPlenty yearOfPlenty, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String roadBuilding(RoadBuilding roadBuilding, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String moveSoldier(SoldierMove soldierMove, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String playMonopolyCard(Monopoly monopoly, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String playMonumentCard(MonumentMove monumentMove, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildRoad(BuildRoad buildRoad, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildCity(BuildCity buildCity, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSettlement(BuildSettlement buildSettlement, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String offerTrade(OfferTrade offerTrade, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String acceptTrade(AcceptTrade acceptTrade, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String maritimeTrade(MaritimeTrade maritimeTrade, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String discardCards(DiscardedCards discardedCards, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addAI(String aiType, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getListAI() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getPlayerIDFromCredentials(Credentials credentials) throws ServerException {
		return userManager.login(credentials);
	}

}
