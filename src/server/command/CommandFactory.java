package server.command;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.command.user.LoginCommand;
import server.facade.IServerFacade;

/**
 * Factory for building a command object. This is a singleton
 * 
 * @author benthompson
 *
 */
public class CommandFactory {

	public CommandFactory() { }
	
	private static CommandFactory factory;
	
	
	public static CommandFactory getInstance() {
		if (factory == null) {
			factory = new CommandFactory();
		}
		return factory;
	}
	
	// The constructor will be taking in (String name, String password, String jsonRequestBody
	public ICommand buildCommand (String type, String json, List<String> cookie, IServerFacade facade) throws ServerException {
		
		ICommand command = new LoginCommand(json, facade);
		
//		Map<String, String> cookieData = this.parseCookie(cookie);

		switch (type) {
		// user
			case "login":
				break;
			case "register":
				break;
		// games
			case "list":
				break;
			case "create":
				break;
			case "join":
				break;
		// game
			case "model": // GET
				break;
			case "addAI":
				break;
			case "listAI":
				break;
		//moves
			case "sendChat":
				break;
			case "rollNumber":
				break;
			case "robPlayer":
				break;
			case "finishTurn":
				break;
			case "buyDevCard":
				break;
			case "Year_of_Plenty":
				break;
			case "Road_Building":
				break;
			case "Soldier":
				break;
			case "Monopoly":
				break;
			case "Monument":
				break;
			case "buildRoad":
				break;
			case "buildSettlement":
				break;
			case "buildCity":
				break;
			case "offerTrade":
				break;
			case "acceptTrade":
				break;
			case "maritimeTrade":
				break;
			case "discardCards":
				break;
			default:
				throw new ServerException("");
		}
		return command;
	}
	
	
	private Map<String, String> parseCookie(List<String> cookie) {
		return new HashMap();
	}

}
