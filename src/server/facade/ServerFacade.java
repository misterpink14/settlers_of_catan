package server.facade;

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

public class ServerFacade implements IServerFacade {
	
	UserManager userManager;
	public ServerFacade() {
		userManager = new UserManager();
	}
	
	
	@Override
	public String login(Credentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String register(Credentials credentials) {
		// TODO Auto-generated method stub
		return "Success";
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
	public String joinGame(JoinGameRequestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModel(int versionNumber) {
		// TODO Auto-generated method stub
		return null;
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
	public String serializeGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
