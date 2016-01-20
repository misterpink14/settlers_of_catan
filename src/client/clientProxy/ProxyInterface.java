package client.clientProxy;

import com.sun.org.glassfish.gmbal.ParameterNames;

/** ClientFacade class
 * 
 * @author Cody Burt
 *
 */
public interface ProxyInterface {

	/**
	 * This function will call the server API at 
	 * user slash login
	 * 
	 * @param JSON String of the server call parameters
	 * @return JSON String
	 */ 
	public String postUserLogin();
	
	/**
	 * This function will call the server API at
	 * user slash register
	 * @ParameterNames
	 */
	public String postUserRegister();
	
	public String getGamesList();
	
	public String postGamesCreate();
	
	public String postGamesJoin();
	
	public String postGamesSave();
	
	public String postGamesLoad();
	
	public String getGameModel();
	
	public String postGameReset();
	
	public String postGameCommands();
	
	public String getGameCommands();
	
	public String postMovesSendChat();
	
	public String postMovesRollNumber();
	
	public String postMovesRobPlayer();
	
	public String postMovesFinishTurn();
	
	public String postMovesBuyDevCard();
	
	public String postMovesYearOfPlenty();
	
	public String postMovesRoadBuilding();
	
	public String postMovesSoldier();
	
	public String postMovesMonument();
	
	public String postMovesBuildRoad();
	
	public String postMovesBuildCity();
	
	public String postMovesBuildSettlement();
	
	public String postMovesOfferTrade();
	
	public String postMovesAcceptTrade();
	
	public String postMovesMaritimeTrade();
	
	public String postMovesDiscardCards();
	
	public String postUtilChangeLogLevel();
	
	
	
	
	
	
	
	
	
}
