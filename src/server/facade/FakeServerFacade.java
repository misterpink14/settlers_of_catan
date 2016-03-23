package server.facade;

import java.util.ArrayList;
import java.util.List;

import server.ServerException;
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
import shared.models.Game;

public class FakeServerFacade implements IServerFacade {

	@Override
	public String login(Credentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String register(Credentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createGame(CreateGameRequestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String joinGame(JoinGameRequestParams params, Credentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModel(int versionNumber, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendChat(SendChat sendChat, int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollNumber(RollNumber rollNumber, int gameID) {
		// TODO Auto-generated method stub
		return null;
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
		return -1;
	}

	@Override
	public String getModel(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
