package client.clientProxy;

import com.sun.org.glassfish.gmbal.ParameterNames;
import shared.communication.proxy;

/** FakeProxy class
 * 
 * @author Cody Burt
 *
 */
public class FakeProxy implements ProxyInterface {

	/**
	 * This function will call the server API at 
	 * user / login
	 * 
	 * @param Credentials object that contains the username and password
	 * @return JSON String of the format:
	 * name: String,
	 * password: String,
	 * playerID: Integer
	 */ 
	public String postUserLogin(Credentials credentials){}
	
	/**
	 * This function will call the server API at
	 * user / register
	 * @param Credentials object that contains the username and password
	 * @return JSON String that indicates success/failure
	 * 
	 */
	public String postUserRegister(Credentials credentials){}
	
	/**
	 * This function will call the server API at
	 * games / list
	 * 
	 * @return JSON String that indicates success/failure
	 * 
	 */
	public String getGamesList(){}
	
	/**
	 * This function will call the server API at
	 * games / create
	 * @param CreateGameRequestParams object that contains the name of 
	 * the game and settings for randomTiles, randomNumbers, and
	 * randomPorts
	 * @return JSON String that contains the game's title, id, and 
	 * a list of empty players
	 */
	public String postGamesCreate(CreateGameRequestParams params){}
	
	/**
	 * This function will call the server API at
	 * games / join
	 * @param JoinGameRequestParams object that contains the id of the
	 * game the player wants to join and the color they want to be
	 * @return JSON String that indicates whether it was a success or
	 * failure
	 */
	public String postGamesJoin(JoinGameRequestsParams params){}
	
	/**
	 * This function will call the server API at
	 * games / save
	 * @param SaveGameRequestParams object that contains the id of the
	 * game the player wants to join and file name they want to save it as
	 * @return JSON String that indicates whether it was a success or
	 * failure
	 */
	public String postGamesSave(SaveGameRequestParams saveGameRequest){}
	
	/**
	 * This function will call the server API at
	 * games / load
	 * @param LoadGameRequestParams object that contains the file name 
	 * they want to load from
	 * @return JSON String that indicates whether it was a success or
	 * failure
	 */
	public String postGamesLoad(LoadGameRequstParams loadGameRequest){}
	
	/**
	 * This function will call the server API at
	 * game / model
	 * @param The version number of the current state
	 * @return JSON String that contains the current game state
	 */
	public String getGameModel(int versionNumber){}
	
	/**
	 * This function will call the server API at
	 * game / reset
	 * @return JSON String that contains the current game state
	 */
	public String postGameReset(){}
	
	/**
	 * This function will call the server API at
	 * game / commands
	 * @param ListOfCommands object that contains the desired
	 * commands to be executed
	 * @return JSON String that contains the client model after
	 * that list of commands have been executed
	 */
	public String postGameCommands(ListOfCommands listOfCommands){}
	
	/**
	 * This function will call the server API at
	 * game / commands
	 * @return JSON String that contains list of commands
	 * executed in the game
	 */
	public String getGameCommands(){}
	
	/**
	 * This function will call the server API at
	 * moves / sendChat
	 * @param SendChat object that contains the player index
	 * of the message sender and the message content
	 * @return JSON String that contains the client model
	 */
	public String postMovesSendChat(SendChat sendChat){}
	
	/**
	 * This function will call the server API at
	 * moves / rollNumber
	 * @param RollNumber object that contains the player index
	 * and what number they rolled
	 * @return JSON String that contains the client model
	 */
	public String postMovesRollNumber(RollNumber robNumber){}
	
	/**
	 * This function will call the server API at
	 * moves / robPlayer
	 * @param RobPlayer object that contains the index of the
	 * player robbing, and the new location of the robber
	 * @return JSON String that contains the client model
	 */
	public String postMovesRobPlayer(RobPlayer robPlayer){}
	
	/**
	 * This function will call the server API at
	 * moves / finishTurn
	 * @param FinishTurn object that contains the player index
	 * that's ending their turn
	 * @return JSON String that contains the client model
	 */
	public String postMovesFinishTurn(FinishTurn finishTurn){}
	
	/**
	 * This function will call the server API at
	 * moves / buyDevCard
	 * @param BuyDevCard object that contains the player index
	 * buying the card
	 * @return JSON String that contains the client model
	 */
	public String postMovesBuyDevCard(BuyDevCard buyDevCard){}
	
	/**
	 * This function will call the server API at
	 * moves / Year_Of_Plenty
	 * @param YearOfPlenty object that contains the player index
	 * playing the card and the two resources they gain
	 * @return JSON String that contains the client model
	 */
	public String postMovesYearOfPlenty(YearOfPlenty yearOfPlenty){}
	
	/**
	 * This function will call the server API at
	 * moves / Road_Building
	 * @param RoadBuilding object that contains the player index
	 * and the two locations they want to build roads
	 * @return JSON String that contains the client model
	 */
	public String postMovesRoadBuilding(RoadBuilding roadBuilding){}
	
	/**
	 * This function will call the server API at
	 * moves / Soldier
	 * @param SoldierMove object that contains the player index
	 * doing the robbing, the player index of the one they're
	 * robbing, and the new location of the robber
	 * @return JSON String that contains the client model
	 */
	public String postMovesSoldier(SoldierMove soldierMove){}
	
	/**
	 * This function will call the server API at
	 * moves / Monopoly
	 * @param Monopoly object that contains the player index
	 * and the resource they will monopolize
	 * @return JSON String that contains the client model
	 */
	public String postMonopoly(Monopoly monopoly){}
	
	/**
	 * This function will call the server API at
	 * moves / Monument
	 * @param MonumentMove object that contains the player index
	 * playing the monument card
	 * @return JSON String that contains the client model
	 */
	public String postMovesMonument(MonumentMove monumentMove){}
	
	/**
	 * This function will call the server API at
	 * moves / buildRoad
	 * @param BuildRoad object that contains the player index
	 * building the road, the location where they want to
	 * build, and whether or not it's free or not
	 * @return JSON String that contains the client model
	 */
	public String postMovesBuildRoad(BuildRoad buildRoad){}
	
	/**
	 * This function will call the server API at
	 * moves / buildCity
	 * @param BuildCity object that contains the player index
	 * building the city and the location of the city
	 * @return JSON String that contains the client model
	 */
	public String postMovesBuildCity(BuildCity buildCity){}
	
	/**
	 * This function will call the server API at
	 * moves / buildSettlement
	 * @param BuildSettlement object that contains the player index
	 * building the settlement, the location, and whether it's free
	 * @return JSON String that contains the client model
	 */
	public String postMovesBuildSettlement(BuildSettlement buildSettlement){}
	
	/**
	 * This function will call the server API at
	 * moves / offerTrade
	 * @param OfferTrade object that contains the player index
	 * sending the offer, the player index receiving the offer,
	 * and the list resources offered and desired
	 * @return JSON String that contains the client model
	 */
	public String postMovesOfferTrade(OfferTrade offerTrade){}
	
	/**
	 * This function will call the server API at
	 * moves / acceptTrade
	 * @param AcceptTrade object that contains the player index
	 * responding to the trade and whether they accept or reject it
	 * @return JSON String that contains the client model
	 */
	public String postMovesAcceptTrade(AcceptTrade acceptTrade){}
	
	/**
	 * This function will call the server API at
	 * moves / maritimeTrade
	 * @param MaritimeTrade object that contains the player index
	 * of the player trading, the ratio they're trading at, the
	 * desired resource and the offered resource
	 * @return JSON String that contains the client model
	 */
	public String postMovesMaritimeTrade(MaritimeTrade maritimeTrade){}
	
	/**
	 * This function will call the server API at
	 * moves / discardedCards
	 * @param DiscardedCards object that contains the player index
	 * discarding cards and the list of resources they're discarding
	 * @return JSON String that contains the client model
	 */
	public String postMovesDiscardCards(DiscardedCards discardedCards){}
	
	/**
	 * This function will call the server API at
	 * util / changeLogLevel
	 * @param ChangeLogLevelRequest that contains the server's
	 * new log level
	 * @return JSON String that indicates whether it succeded
	 */
	public String postUtilChangeLogLevel(ChangeLogLevelRequest logLevel){}
	
	
	
	
	
	
	
	
	
}
