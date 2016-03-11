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
/**
 * The server facade executes the commands from the client communicator. It recieves the direction to execute from the Http handlers and
 * Uses sql statements contained in the DAO classes to recieve the necessary information for the methods. It returns specialized packets of
 * information to be returned to the client.
 * @author Skyler
 */
public interface ServerFacade {
	
	/**
	 * This function will login the User
	 * 
	 * @param Credentials object that contains the username and password
	 * @return int returns the player Index or -1 if invalid Credentials
	 */ 
	public int login(Credentials credentials);
	
	/**
	 * This function will register a new user
	 * 
	 * @param Credentials object that contains the username and password
	 * @return int returns the player Index or -1 if invalid Credentials
	 */
	public int register(Credentials credentials);
	
	/**
	 * This function will return a list of all the games
	 * 
	 * @return List<Game> containing all the games
	 */
	public List<Game> getGamesList();
	
	/**
	 * This function will create a new game
	 * @param CreateGameRequestParams object that contains the name of 
	 * the game and settings for randomTiles, randomNumbers, and
	 * randomPorts
	 * @return Game
	 */
	public Game createGame(CreateGameRequestParams params);
	
	/**
	 * This function will allow the user to join a game
	 * @param JoinGameRequestParams object that contains the id of the
	 * game the player wants to join and the color they want to be
	 * @return boolean True means success joining the game, false means failure
	 */
	public boolean joinGame(JoinGameRequestParams params);
	
	/**
	 * This function will return the Game at the specified versionNumber
	 * @param The version number of the current state
	 * @return Game at that state
	 */
	public Game getModel(int versionNumber);
	
	/**
	 * This function will add the Chat to the Chat Log
	 * @param SendChat object that contains the player index
	 * of the message sender and the message content
	 * @return Game
	 */
	public Game sendChat(SendChat sendChat);
	
	/**
	 * This function will roll the dice
	 * @param RollNumber object that contains the player index
	 * and what number they rolled
	 * @return Game
	 */
	public Game rollNumber(RollNumber rollNumber);
	
	/**
	 * This function will allow the player to rob
	 * @param RobPlayer object that contains the index of the
	 * player robbing, and the new location of the robber
	 * @return Game
	 */
	public Game robPlayer(RobPlayer robPlayer);
	
	/**
	 * This function will finish the turn
	 * @param FinishTurn object that contains the player index
	 * that's ending their turn
	 * @return Game
	 */
	public Game finishTurn(FinishTurn finishTurn);
	
	/**
	 * This function will buy a dev card
	 * @param BuyDevCard object that contains the player index
	 * buying the card
	 * @return Game
	 */
	public Game buyDevCard(BuyDevCard buyDevCard);
	
	/**
	 * This function will play a Year of Plenty card
	 * @param YearOfPlenty object that contains the player index
	 * playing the card and the two resources they gain
	 * @return Game
	 */
	public Game yearOfPlenty(YearOfPlenty yearOfPlenty);
	
	/**
	 * This function will play a road building card
	 * @param RoadBuilding object that contains the player index
	 * and the two locations they want to build roads
	 * @return Game
	 */
	public Game roadBuilding(RoadBuilding roadBuilding);
	
	/**
	 * This function will play a Soldier card
	 * @param SoldierMove object that contains the player index
	 * doing the robbing, the player index of the one they're
	 * robbing, and the new location of the robber
	 * @return Game
	 */
	public Game moveSoldier(SoldierMove soldierMove);
	
	/**
	 * This function will play a Monopoly card
	 * @param Monopoly object that contains the player index
	 * and the resource they will monopolize
	 * @return Game
	 */
	public Game playMonopolyCard(Monopoly monopoly);
	
	/**
	 * This function will play a Monument Card
	 * @param MonumentMove object that contains the player index
	 * playing the monument card
	 * @return Game
	 */
	public Game playMonumentCard(MonumentMove monumentMove);
	
	/**
	 * This function will build a road
	 * @param BuildRoad object that contains the player index
	 * building the road, the location where they want to
	 * build, and whether or not it's free or not
	 * @return Game
	 */
	public Game buildRoad(BuildRoad buildRoad);
	
	/**
	 * This function will build a city
	 * @param BuildCity object that contains the player index
	 * building the city and the location of the city
	 * @return Game
	 */
	public Game buildCity(BuildCity buildCity);
	
	/**
	 * This function will build a settlement
	 * @param BuildSettlement object that contains the player index
	 * building the settlement, the location, and whether it's free
	 * @return Game
	 */
	public Game buildSettlement(BuildSettlement buildSettlement);
	
	/**
	 * This function will offer a trade
	 * @param OfferTrade object that contains the player index
	 * sending the offer, the player index receiving the offer,
	 * and the list resources offered and desired
	 * @return Game
	 */
	public Game offerTrade(OfferTrade offerTrade);
	
	/**
	 * This function will accept or reject a trade
	 * @param AcceptTrade object that contains the player index
	 * responding to the trade and whether they accept or reject it
	 * @return Game
	 */
	public Game acceptTrade(AcceptTrade acceptTrade);
	
	/**
	 * This function will do a Maritime Trade
	 * @param MaritimeTrade object that contains the player index
	 * of the player trading, the ratio they're trading at, the
	 * desired resource and the offered resource
	 * @return Game
	 */
	public Game maritimeTrade(MaritimeTrade maritimeTrade);
	
	/**
	 * This function will discard the specified cards
	 * @param DiscardedCards object that contains the player index
	 * discarding cards and the list of resources they're discarding
	 * @return Game
	 */
	public Game discardCards(DiscardedCards discardedCards);
	
	
	/**
	 * Adds an AI to the game the client has joined
	 * 
	 * @param aiType
	 * @return Game
	 */
	public Game addAI(String aiType);
	
	/**
	 * Gets the list of available AI's from the server
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getListAI();
}