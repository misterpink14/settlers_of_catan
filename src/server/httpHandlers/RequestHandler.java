package server.httpHandlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.command.ACommand;
import server.command.CommandFactory;
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
	IServerFacade facade;
	

// Constructor
	public RequestHandler(boolean isTest) {
		if (isTest) {
			facade = new FakeServerFacade();
		}
		else {
			facade = new ServerFacade();
		}
	}
	
	
	
// Implemented Method
	/**
	 * Handles the request from the client. Will call the appropriate factory
	 * based on the first part of the url.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		
		System.out.println("handle");
		try {
			
			ACommand command = CommandFactory.getInstance().buildCommand(
				this.getCommandType(exchange),
				this.getJson(exchange),
				this.parseCookie(exchange),
				this.facade,
				exchange.getRequestMethod()
			);
			
			// TODO - make this the response with the appropriate cookie
			System.out.println(command.execute());
		} catch (ServerException e) {
			e.printStackTrace();
			this.handleError(exchange);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
// Private METHODS
	/**
	 * Get's the cookie from a given HttpExchange object
	 * 
	 * @param exchange
	 * @return
	 * @throws ServerException 
	 */
	Map<String, String> parseCookie(HttpExchange exchange) throws ServerException {
		
		Map<String, String> cookies = new HashMap<String, String>();
		List<String> cookieList = exchange.getRequestHeaders().get("Cookie");
		
		if (cookieList == null) {
			return cookies;
		}
		
		for (String cookie : cookieList) {

			try {
				String decodedCookie = URLDecoder.decode(cookie, "UTF-8"); 
				String[] values =  decodedCookie.split("=");
				cookies.put(values[0], values[1]);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new ServerException("Unable to decode cookie: " + cookie);
			}
		}
		return cookies;
	}
	
	
	/**
	 * Get's the json body from a given HttpExchange object
	 * 
	 * @param exchange
	 * @return
	 * @throws IOException
	 */
	String getJson(HttpExchange exchange) throws IOException {
		return IOUtils.toString(exchange.getRequestBody());
	}
	
	
	/**
	 * Get's the command type from the second folder of a uri. Uses the given 
	 * 	HttpExchange object to get the command
	 * 
	 * @param exchange
	 * @return
	 * @throws ServerException
	 */
	String[] getCommandType(HttpExchange exchange) throws ServerException {

		String[] path = exchange.getRequestURI().toString().split("/");
		if (path.length != 3) {
			throw new ServerException("Invalid URL path length");
		}
		String[] commandType = {path[1], path[2]};
		return commandType;
	}
	
	
	/**
	 * Handles errors thrown from unexpected pre conditions
	 * 
	 * @param exchange
	 * @throws IOException
	 */
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
