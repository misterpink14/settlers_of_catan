package server.command.game;

import server.command.ACommand;

/**
 * Command for getting a list of all available AI players
 * 	Server end-point: /game/listAI GET
 * 
 * @author benthompson
 */
public class ListAICommand extends ACommand {
	
	
	String Username, Password;

	public ListAICommand(String username, String password) {
		this.Username = username;
		this.Password = password;
	}

	@Override
	public String execute() {
		return null;
	}

}
