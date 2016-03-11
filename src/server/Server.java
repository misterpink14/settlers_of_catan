package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import server.database.DatabaseException;
import server.database.DatabaseRepresentation;
import server.httpHandlers.RequestHandler;

/**
 * The HTTP Server. Passes all requests to their appropriate handlers
 * @author ssnyder
 *
 */
public class Server {

	private static final int SERVER_PORT_NUMBER = 8081;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	static int port = SERVER_PORT_NUMBER;
	
	private static Logger logger;
	
	static {
		try {
			initLog();
		}
		catch (IOException e) {
			System.out.println("Could not initialize log: " + e.getMessage());
		}
	}
	
	private static void initLog() throws IOException {
		Level logLevel = Level.FINE;
		
		logger = Logger.getLogger("contactmanager"); 
		logger.setLevel(logLevel);
		logger.setUseParentHandlers(false);
		
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(logLevel);
		consoleHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(consoleHandler);

		FileHandler fileHandler = new FileHandler("log.txt", false);
		fileHandler.setLevel(logLevel);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
	}

	
	private HttpServer server;
	
	private Server() {
		return;
	}
	
	private void run() {
		
		logger.info("Initializing Model");
		
		try
		{
			DatabaseRepresentation.initialize();
		} catch (DatabaseException e1)
		{
			System.out.println("Failed to initialize the Database");
			e1.printStackTrace();
		}		
		
		logger.info("Initializing HTTP Server");
		
		try {
			server = HttpServer.create(new InetSocketAddress(port),
											MAX_WAITING_CONNECTIONS);
		} 
		catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);			
			return;
		}

		server.setExecutor(null); // use the default executor
		
		server.createContext("/", rq);
		
		logger.info("Starting HTTP Server");

		server.start();
	}

	private HttpHandler rq = new RequestHandler();
	
	public static void main(String[] args) {
		port = Integer.parseInt(args[0]);
		new Server().run();
	}

}