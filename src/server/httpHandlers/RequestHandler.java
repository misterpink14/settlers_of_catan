package server.httpHandlers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.logging.*;

//import com.sun.net.httpserver.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import shared.communication.*;
import server.database.DatabaseException;
import server.facade.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handles the HTTP requests for DownloadBatch. Calls the matching method in the Server Facade
 * @author ssnyder
 *
 */
public class RequestHandler implements HttpHandler
{

	private Logger logger = Logger.getLogger("contactmanager"); 
	
	private XStream xmlStream = new XStream(new DomDriver());
	
	public RequestHandler()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Handles the request from the client. Will call the appropriate factory
	 * based on the first part of the url.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		/*DownloadBatchInput params = (DownloadBatchInput)xmlStream.fromXML(exchange.getRequestBody());
		try
		{
			DownloadBatchOutput output = ServerFacade.DownloadBatch(params);
			if(output == null)
			{
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
				return;
			}
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			xmlStream.toXML(output, exchange.getResponseBody());
			exchange.getResponseBody().close();	
			return;
		} catch (DatabaseException e)
		{
			System.out.println("DownloadBatch threw a database error");
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage(), e);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
			return;
		} catch (SQLException e)
		{
			System.out.println("DownloadBatch threw a sql error");
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage(), e);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, -1);
			return;
		}*/
	}
	
	/**
	 * Call the userCommandFactory with params from the client request
	 * @param params
	 * @return
	 */
	String handleUser(String params) {
		return "";
	}
	
	/**
	 * Call the gamesCommandFactory with params from the client request
	 * @param params
	 * @return
	 */
	String handleGames(String params) {
		return "";
	}
	
	/**
	 * Call the gameCommandFactory with params from the client request
	 * @param params
	 * @return
	 */
	String handleGame(String params) {
		return "";
	}

	/**
	 * Call the moveCommandFactory with params from the client request
	 * @param params
	 * @return
	 */
	String handleMoves(String params) {
		return "";
	}
}
