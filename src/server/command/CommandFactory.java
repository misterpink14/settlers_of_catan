package server.command;

import java.rmi.ServerException;
import java.util.Map;

import server.command.game.AddAICommand;
import server.command.game.ListAICommand;
import server.command.game.ModelCommand;
import server.command.games.CreateCommand;
import server.command.games.JoinCommand;
import server.command.games.ListCommand;
import server.command.moves.AcceptTradeCommand;
import server.command.moves.BuildCityCommand;
import server.command.moves.BuildRoadCommand;
import server.command.moves.BuildSettlementCommand;
import server.command.moves.BuyDevCardCommand;
import server.command.moves.DiscardCardsCommand;
import server.command.moves.FinishTurnCommand;
import server.command.moves.MaritimeTradeCommand;
import server.command.moves.MonopolyCommand;
import server.command.moves.MonumentCommand;
import server.command.moves.OfferTradeCommand;
import server.command.moves.RoadBuildingCommand;
import server.command.moves.RobPlayerCommand;
import server.command.moves.RollNumberCommand;
import server.command.moves.SendChatCommand;
import server.command.moves.SoldierCommand;
import server.command.moves.YearOfPlentyCommand;
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
			case "game":
				command = this.buildGameCommand(type[1], cookies.get("catan.user"), facade, jsonBody);
				break;
			case "moves":
				command = this.buildMovesCommand(type[1], cookies.get("catan.user"), facade, jsonBody);
				break;
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
	
	
	ACommand buildGamesCommand(String type, String userJson, IServerFacade facade, String jsonBody) 
																			throws ServerException {

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
	
	
	ACommand buildGameCommand(String type, String userJson, IServerFacade facade, String jsonBody) 
																			throws ServerException {

		switch(type) {
			case "model": // GET
				return new ModelCommand(userJson, facade);
			case "addAI":
				return new AddAICommand(userJson, facade, jsonBody);
			case "listAI": // GET
				return new ListAICommand(userJson, facade);
			default:
				throw new ServerException("Invalid uri");
		}
	}
	
	
	ACommand buildMovesCommand(String type, String userJson, IServerFacade facade, String jsonBody) 
																			throws ServerException {

		switch(type) {
			case "sendChat":
				return new SendChatCommand(userJson, facade, jsonBody);
			case "rollNumber":
				return new RollNumberCommand(userJson, facade, jsonBody);
			case "robPlayer":
				return new RobPlayerCommand(userJson, facade, jsonBody);
			case "finishTurn":
				return new FinishTurnCommand(userJson, facade, jsonBody);
			case "buyDevCard":
				return new BuyDevCardCommand(userJson, facade, jsonBody);
			case "Year_of_Plenty":
				return new YearOfPlentyCommand(userJson, facade, jsonBody);
			case "Road_Building":
				return new RoadBuildingCommand(userJson, facade, jsonBody);
			case "Soldier":
				return new SoldierCommand(userJson, facade, jsonBody);
			case "Monopoly":
				return new MonopolyCommand(userJson, facade, jsonBody);
			case "Monument":
				return new MonumentCommand(userJson, facade, jsonBody);
			case "buildRoad":
				return new BuildRoadCommand(userJson, facade, jsonBody);
			case "buildSettlement":
				return new BuildSettlementCommand(userJson, facade, jsonBody);
			case "buildCity":
				return new BuildCityCommand(userJson, facade, jsonBody);
			case "offerTrade":
				return new OfferTradeCommand(userJson, facade, jsonBody);
			case "acceptTrade":
				return new AcceptTradeCommand(userJson, facade, jsonBody);
			case "maritimeTrade":
				return new MaritimeTradeCommand(userJson, facade, jsonBody);
			case "discardCards":
				return new DiscardCardsCommand(userJson, facade, jsonBody);
			default:
				throw new ServerException("Invalid uri");
		}
	}
	
	
	void validateHTTPMethod(String method, String[] type) throws ServerException {
		
		boolean isPost = method.toUpperCase() == "POST";
		if (!isPost) {
			if (type[0] == "games" && type[1] == "list" || 
					type[0] == "game" && type[1] == "model" || 
					type[0] == "game" && type[1]  == "listAI") {
				
				return;
			}
			throw new ServerException("Invalid HTTP method");
		}
	}
}
