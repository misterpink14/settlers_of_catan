package server.facade;

import java.util.ArrayList;
import java.util.List;

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

public class RealServerFacade implements ServerFacade {

	@Override
	public int login(Credentials credentials) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int register(Credentials credentials) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Game> getGamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game createGame(CreateGameRequestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean joinGame(JoinGameRequestParams params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Game getModel(int versionNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game sendChat(SendChat sendChat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game rollNumber(RollNumber rollNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game robPlayer(RobPlayer robPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game finishTurn(FinishTurn finishTurn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game buyDevCard(BuyDevCard buyDevCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game yearOfPlenty(YearOfPlenty yearOfPlenty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game roadBuilding(RoadBuilding roadBuilding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game moveSoldier(SoldierMove soldierMove) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game playMonopolyCard(Monopoly monopoly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game playMonumentCard(MonumentMove monumentMove) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game buildRoad(BuildRoad buildRoad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game buildCity(BuildCity buildCity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game buildSettlement(BuildSettlement buildSettlement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game offerTrade(OfferTrade offerTrade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game acceptTrade(AcceptTrade acceptTrade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game maritimeTrade(MaritimeTrade maritimeTrade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game discardCards(DiscardedCards discardedCards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game addAI(String aiType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getListAI() {
		// TODO Auto-generated method stub
		return null;
	}

}
