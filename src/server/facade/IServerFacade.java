package server.facade;

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
/**
 * The server facade executes the commands from the client communicator. It recieves the direction to execute from the Http handlers and
 * Uses sql statements contained in the DAO classes to recieve the necessary information for the methods. It returns specialized packets of
 * information to be returned to the client.
 * @author Skyler
 */
public interface IServerFacade {
	
	/**
	 * This function will login the User
	 * 
	 * @param Credentials object that contains the username and password
	 * @return JSON String of Game
	 * @throws ServerException 
	 */ 
	public String login(Credentials credentials) throws ServerException;
	
	/**
	 * This function will register a new user
	 * 
	 * @param Credentials object that contains the username and password
	 * @return JSON String of Game
	 * @throws ServerException 
	 */
	public String register(Credentials credentials) throws ServerException;
	
	/**
	 * This function will return a list of all the games
	 * 
	 * @return JSON String of Game
	 */
	public String getGamesList();
	
	/**
	 * This function will create a new game
	 * @param CreateGameRequestParams object that contains the name of 
	 * the game and settings for randomTiles, randomNumbers, and
	 * randomPorts
	 * @return JSON String of Game
	 */
	public String createGame(CreateGameRequestParams params);
	
	/**
	 * This function will allow the user to join a game
	 * @param JoinGameRequestParams object that contains the id of the
	 * game the player wants to join and the color they want to be
	 * @return JSON String of Game
	 * @throws ServerException 
	 */
	public String joinGame(JoinGameRequestParams params, Credentials credentials) throws ServerException;
	
	/**
	 * This function will return the Game at the specified versionNumber
	 * @param The version number of the current state
	 * @return JSON String of Game
	 */
	public String getModel(int versionNumber, int gameID);
	
	/**
	 * This function will add the Chat to the Chat Log
	 * @param SendChat object that contains the player index
	 * of the message sender and the message content
	 * @return JSON String of Game
	 */
	public String sendChat(SendChat sendChat);
	
	/**
	 * This function will roll the dice
	 * @param RollNumber object that contains the player index
	 * and what number they rolled
	 * @return JSON String of Game
	 */
	public String rollNumber(RollNumber rollNumber);
	
	/**
	 * This function will allow the player to rob
	 * @param RobPlayer object that contains the index of the
	 * player robbing, and the new location of the robber
	 * @return JSON String of Game
	 */
	public String robPlayer(RobPlayer robPlayer);
	
	/**
	 * This function will finish the turn
	 * @param FinishTurn object that contains the player index
	 * that's ending their turn
	 * @return JSON String of Game
	 */
	public String finishTurn(FinishTurn finishTurn);
	
	/**
	 * This function will buy a dev card
	 * @param BuyDevCard object that contains the player index
	 * buying the card
	 * @return JSON String of Game
	 */
	public String buyDevCard(BuyDevCard buyDevCard);
	
	/**
	 * This function will play a Year of Plenty card
	 * @param YearOfPlenty object that contains the player index
	 * playing the card and the two resources they gain
	 * @return JSON String of Game
	 */
	public String yearOfPlenty(YearOfPlenty yearOfPlenty);
	
	/**
	 * This function will play a road building card
	 * @param RoadBuilding object that contains the player index
	 * and the two locations they want to build roads
	 * @return JSON String of Game
	 */
	public String roadBuilding(RoadBuilding roadBuilding);
	
	/**
	 * This function will play a Soldier card
	 * @param SoldierMove object that contains the player index
	 * doing the robbing, the player index of the one they're
	 * robbing, and the new location of the robber
	 * @return JSON String of Game
	 */
	public String moveSoldier(SoldierMove soldierMove);
	
	/**
	 * This function will play a Monopoly card
	 * @param Monopoly object that contains the player index
	 * and the resource they will monopolize
	 * @return JSON String of Game
	 */
	public String playMonopolyCard(Monopoly monopoly);
	
	/**
	 * This function will play a Monument Card
	 * @param MonumentMove object that contains the player index
	 * playing the monument card
	 * @return JSON String of Game
	 */
	public String playMonumentCard(MonumentMove monumentMove);
	
	/**
	 * This function will build a road
	 * @param BuildRoad object that contains the player index
	 * building the road, the location where they want to
	 * build, and whether or not it's free or not
	 * @return JSON String of Game
	 */
	public String buildRoad(BuildRoad buildRoad);
	
	/**
	 * This function will build a city
	 * @param BuildCity object that contains the player index
	 * building the city and the location of the city
	 * @return JSON String of Game
	 */
	public String buildCity(BuildCity buildCity);
	
	/**
	 * This function will build a settlement
	 * @param BuildSettlement object that contains the player index
	 * building the settlement, the location, and whether it's free
	 * @return JSON String of Game
	 */
	public String buildSettlement(BuildSettlement buildSettlement);
	
	/**
	 * This function will offer a trade
	 * @param OfferTrade object that contains the player index
	 * sending the offer, the player index receiving the offer,
	 * and the list resources offered and desired
	 * @return JSON String of Game
	 */
	public String offerTrade(OfferTrade offerTrade);
	
	/**
	 * This function will accept or reject a trade
	 * @param AcceptTrade object that contains the player index
	 * responding to the trade and whether they accept or reject it
	 * @return JSON String of Game
	 */
	public String acceptTrade(AcceptTrade acceptTrade);
	
	/**
	 * This function will do a Maritime Trade
	 * @param MaritimeTrade object that contains the player index
	 * of the player trading, the ratio they're trading at, the
	 * desired resource and the offered resource
	 * @return JSON String of Game
	 */
	public String maritimeTrade(MaritimeTrade maritimeTrade);
	
	/**
	 * This function will discard the specified cards
	 * @param DiscardedCards object that contains the player index
	 * discarding cards and the list of resources they're discarding
	 * @return JSON String of Game
	 */
	public String discardCards(DiscardedCards discardedCards);
	
	
	/**
	 * Adds an AI to the game the client has joined
	 * 
	 * @param aiType
	 * @return JSON String of Game
	 */
	public String addAI(String aiType);
	
	/**
	 * Gets the list of available AI's from the server
	 * 
	 * @return JSON String of Game
	 */
	public String getListAI();

	int getPlayerIDFromCredentials(Credentials credentials) throws ServerException;
}