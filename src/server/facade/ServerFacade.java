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
		User user = this.userManager.getUser(credentials.username);
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
	public String sendChat(SendChat sendChat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollNumber(RollNumber rollNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String robPlayer(RobPlayer robPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String finishTurn(FinishTurn finishTurn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buyDevCard(BuyDevCard buyDevCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String yearOfPlenty(YearOfPlenty yearOfPlenty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String roadBuilding(RoadBuilding roadBuilding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String moveSoldier(SoldierMove soldierMove) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String playMonopolyCard(Monopoly monopoly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String playMonumentCard(MonumentMove monumentMove) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildRoad(BuildRoad buildRoad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildCity(BuildCity buildCity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSettlement(BuildSettlement buildSettlement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String offerTrade(OfferTrade offerTrade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String acceptTrade(AcceptTrade acceptTrade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String maritimeTrade(MaritimeTrade maritimeTrade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String discardCards(DiscardedCards discardedCards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addAI(String aiType) {
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
