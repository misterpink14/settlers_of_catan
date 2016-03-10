package server.command.games;

import server.command.ICommand;

/**
 * Command for joining a specific game
 * 	Server end-point: /games/join POST
 * 
 * @author benthompson
 */
public class JoinCommand implements ICommand {
	
	
	String Username, Password, Color;
	int GameID;

	public JoinCommand(String username, String password, int gameID, String color) {
		this.Username = username;
		this.Password = password;
		this.GameID = gameID;
		this.Color = color;
	}

	@Override
	public String execute() {
		return null;
	}

}
