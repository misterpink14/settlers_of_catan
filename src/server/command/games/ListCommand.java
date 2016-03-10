package server.command.games;

import server.command.ICommand;

/**
 * Command for getting the list of games on the server
 * 	Server end-point: /games/list GET
 * 
 * @author benthompson
 */
public class ListCommand implements ICommand {

	
	String Username, Password;
	
	public ListCommand(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
	
	@Override
	public String execute() {
		return null;
	}

}
