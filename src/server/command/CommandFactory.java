package server.command;

import java.rmi.ServerException;
import java.util.Map;

import server.command.games.CreateCommand;
import server.command.games.JoinCommand;
import server.command.games.ListCommand;
import server.command.user.LoginCommand;
import server.command.user.RegisterCommand;
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
	

	
	public ACommand buildCommand (String[] type, String jsonBody, Map<String, String> cookies, 
									IServerFacade facade, String httpMethod) throws ServerException {
		
		ACommand command = new LoginCommand(jsonBody, facade);
		this.validateHTTPMethod(httpMethod, type);

		switch (type[0]) {
			case "user":
				command = this.buildUserCommand(type[1], jsonBody, facade);
				break;
			case "games":
				command = this.buildGamesCommand(type[1], cookies.get("catan.user"), facade, jsonBody);
				break;
//			case "game":
//				command = this.buildGameCommand(type[1], cookies.get("catan.user"), facade, jsonBody);
//				break;
//			case "moves":
//				command = this.buildMovesCommand(type[1], cookies.get("catan.user"), facade, jsonBody);
//				break;
			default:
				throw new ServerException("Invalid uri");
		}
		return command;
	}
	
	
	ACommand buildUserCommand(String type, String json, IServerFacade facade) throws ServerException {

		switch(type) {
			case "login":
				return new LoginCommand(json, facade);
			case "register":
				return new RegisterCommand(json, facade);
			default:
				throw new ServerException("Invalid uri");
		}
	}
	
	
	ACommand buildGamesCommand(String type, String userJson, IServerFacade facade, String jsonBody) throws ServerException {

		switch(type) {
			case "list": // GET
				return new ListCommand(userJson, facade);
			case "create":
				return new CreateCommand(userJson, facade, jsonBody);
			case "join":
				return new JoinCommand(userJson, facade, jsonBody);
			default:
				throw new ServerException("Invalid uri");
		}
	}
	
	
//	ICommand buildGameCommand() {
//
//		switch(type) {
//			case "model": // GET
//				break;
//			case "addAI":
//				break;
//			case "listAI":
//				break;
//			default:
//				throw new ServerException("Invalid uri");
//		}
//	}
//	
//	
//	ICommand buildMovesCommand() {
//
//		switch(type) {
//			case "sendChat":
//				break;
//			case "rollNumber":
//				break;
//			case "robPlayer":
//				break;
//			case "finishTurn":
//				break;
//			case "buyDevCard":
//				break;
//			case "Year_of_Plenty":
//				break;
//			case "Road_Building":
//				break;
//			case "Soldier":
//				break;
//			case "Monopoly":
//				break;
//			case "Monument":
//				break;
//			case "buildRoad":
//				break;
//			case "buildSettlement":
//				break;
//			case "buildCity":
//				break;
//			case "offerTrade":
//				break;
//			case "acceptTrade":
//				break;
//			case "maritimeTrade":
//				break;
//			case "discardCards":
//				break;
//			default:
//				throw new ServerException("Invalid uri");
//		}
//	}
	
	
	void validateHTTPMethod(String method, String[] type) throws ServerException {
		boolean isPost = method.toUpperCase() == "POST";
		if (!isPost) {
			if (type[0] == "games" && type[1] == "list" || type[0] == "game" && type[1] == "model") {
				return;
			}
			throw new ServerException("Invalid HTTP method");
		}
	}
}
