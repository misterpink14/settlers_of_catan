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
import server.httpHandlers.Handlers;
import server.httpHandlers.RequestHandler;

/**
 * The HTTP Server. Passes all requests to the handler
 * @author ssnyder
 *
 */
public class Server {

	private static final int DEFAULT_PORT_NUMBER = 8081;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private int port;
	private String host;
	private HttpHandler handler;
	private static Logger logger;
	private HttpServer server;
	
	
	// Initialize the log
	static {
		try {
			initLog();
		}
		catch (IOException e) {
			System.out.println("Could not initialize log: " + e.getMessage());
		}
	}
	
	
	
	private Server() {
		this.port = DEFAULT_PORT_NUMBER;
		this.handler = new RequestHandler(false);
		return;
	}
	
	private Server(String host, int port, boolean isTest) {
		this.host = host;
		this.port = port;
		this.handler = new RequestHandler(isTest);
		return;
	}
	
	
	
	/**
	 * Initializes the log
	 * 
	 * @throws IOException
	 */
	private static void initLog() throws IOException {
		Level logLevel = Level.FINE;
		
		logger = Logger.getLogger("catan"); 
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
	
	
	/**
	 * Set's up the server before starting it. // TODO add testing capabilities
	 * 
	 */
	private void run() {
		
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
		server.createContext("/user", handler);
		server.createContext("/game", handler);
		server.createContext("/games", handler);
		server.createContext("/moves", handler);
		
		server.createContext("/docs/api/data", new Handlers.JSONAppender("")); 
		server.createContext("/docs/api/view", new Handlers.BasicFile(""));

		
		logger.info("Starting HTTP Server on port: " + port);
		server.start();
	}
	
	
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		if (args.length > 4) {
			System.out.println("Must run server with format: host port-number test");
			throw new Exception();
		}
		
		if (args.length != 0) { 
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			boolean isTest = false;
			if (args.length == 3) {
				isTest = args[2].equals("true");
			}
			new Server(host, port, isTest).run();
		}
		else {
			new Server().run();
		}
	}

}
