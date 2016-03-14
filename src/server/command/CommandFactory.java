package server.command;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import server.command.user.LoginCommand;

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
	public ICommand buildCommand (String type, String json, InputStream cookie) {
		return new LoginCommand("", "");
	}
	
	
	private Map<String, String> parseCookie() {
		return new HashMap();
	}

}
