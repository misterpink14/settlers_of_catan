package server.command.game;

import server.command.ACommand;

/**
 * Command for getting a game's model
 * 	Server end-point: /game/model GET
 * 
 * @author benthompson
 */
public class ModelCommand extends ACommand {
	
	
	String Username, Password;
	int GameID;

	public ModelCommand(String username, String password, String json) {
		this.Username = username;
		this.Password = password;
	}

	@Override
	public String execute() {
		return null;
	}

}
