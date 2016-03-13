package server.httpHandlers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.rmi.ServerException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.command.ICommand;
import server.command.user.LoginCommand;
import server.facade.FakeServerFacade;
import server.facade.IServerFacade;
import server.facade.ServerFacade;

/**
 * Handles the HTTP requests for the server. Calls the appropriate factory based on the url of the request
 * @author ssnyder
 *
 */
public class RequestHandler implements HttpHandler
{

	Logger logger = Logger.getLogger("settlers"); 
	ICommand command;
	IServerFacade facade;
	
	
	public RequestHandler(boolean isTest) {
		if (isTest) {
			facade = new FakeServerFacade();
		}
		else {
			facade = new ServerFacade();
		}
	}
	
	/**
	 * Handles the request from the client. Will call the appropriate factory
	 * based on the first part of the url.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		String[] path = exchange.getRequestURI().toString().split("/");
		if (path.length != 3) {
			this.handleError(exchange);
			return;
		}
		String factoryType = path[1];
		String commandType = path[2];
		
		try {
			switch(factoryType) {
				case "user":
					this.handleUser(commandType);
					break;
				case "games":
					this.handleGames(commandType);
					break;
				case "game":
					this.handleGame(commandType);
					break;
				case "moves":
					this.handleMoves(commandType);
					break;
				default:
					this.handleError(exchange);
					return;
			}
			
			System.out.println(command.execute());
		} catch (ServerException e) {
			e.printStackTrace();
			this.handleError(exchange);
		}
	}
	
	/**
	 * Call the userCommandFactory with params from the client request
	 * @param params
	 * @return
	 * @throws ServerException 
	 */
	void handleUser(String commandType) throws ServerException {

		this.command = new LoginCommand("", "");
	}
	
	/**
	 * Call the gamesCommandFactory with params from the client request
	 * @param params
	 * @return
	 */
	void handleGames(String commandType) {

		this.command = new LoginCommand("", "");
	}
	
	/**
	 * Call the gameCommandFactory with params from the client request
	 * @param params
	 * @return
	 */
	void handleGame(String commandType) {

		this.command = new LoginCommand("", "");
	}

	/**
	 * Call the moveCommandFactory with params from the client request
	 * @param params
	 * @return
	 */
	void handleMoves(String commandType) {

		this.command = new LoginCommand("", "");
	}
	
	void handleError(HttpExchange exchange) throws IOException {
		String errorMessage = "Invalid request";
		logger.log(Level.SEVERE, "Bad request " + errorMessage);
		exchange.getResponseHeaders().add("Content-Type", "application/text");
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		exchange.getResponseBody().write(errorMessage.getBytes());
		exchange.getResponseBody().close();
		exchange.close();
	}
}
