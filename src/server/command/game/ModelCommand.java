package server.command.game;

import server.command.ICommand;

/**
 * Command for getting a game's model
 * 	Server end-point: /game/model GET
 * 
 * @author benthompson
 */
public class ModelCommand implements ICommand {
	
	
	String Username, Password;
	int GameID;

	public ModelCommand(String username, String password, int gameID) {
		this.Username = username;
		this.Password = password;
		this.GameID = gameID;
	}

	@Override
	public String execute() {
		return null;
	}

}
