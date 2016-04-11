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
import server.database.IPersistencePlugin;
import server.database.MongoPlugin;
import server.database.Registry;
import server.facade.FakeServerFacade;
import server.facade.IServerFacade;
import server.facade.ServerFacade;
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
		//this.handler = new RequestHandler(false);
		return;
	}
	
	private Server(String host, int port, boolean isTest, String pluginClass, int delta) {
		this.host = host;
		this.port = port;
		if (isTest) { // don't worry about the plugins here
			System.out.println("Creating a fake facade for testing purposes");
			IServerFacade serverFacade = new FakeServerFacade();
			this.handler = new RequestHandler(serverFacade, new MongoPlugin());
		}
		else {
			IServerFacade serverFacade = new ServerFacade();
			IPersistencePlugin plugin = this.initializePlugin(pluginClass, delta);
			this.handler = new RequestHandler(serverFacade, plugin);
		}
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
		
		try
		{
			DatabaseRepresentation.initialize();
			DatabaseRepresentation.dropAndRecreateTables();
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
		server.createContext("/user", handler);
		server.createContext("/game", handler);
		server.createContext("/games", handler);
		server.createContext("/moves", handler);
		
		server.createContext("/docs/api/data", new Handlers.JSONAppender("")); 
		server.createContext("/docs/api/view", new Handlers.BasicFile(""));

		
		logger.info("Starting HTTP Server on port: " + port);
		server.start();
		initializePlugin("", -1);
	}
	
	/**
	 * This function starts up the persistence plugin to allow data permanence.
	 * @param plugin The name of the plugin the user wishes to start up.
	 * @param delta The number of commands between game state checkpoints.
	 */
	private IPersistencePlugin initializePlugin(String plugin, int delta) {
		Registry r = new Registry();
		r.loadConfig(plugin, delta);
		return r.getPlugin();
	}
	
	public static void main(String[] args) throws Exception {
		
		if (args.length != 5) {
			System.out.println("Must run server with format: host port-number test plugin delta");
			throw new Exception();
		}
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		boolean isTest = false;
		if (args.length == 3) {
			isTest = args[2].equals("true");
		}
		String pluginClass = args[3];
		int delta = Integer.parseInt(args[4]);
		new Server(host, port, isTest, pluginClass, delta).run();
	}

}
