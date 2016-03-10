package server.command.games;

import server.command.ICommand;

/**
 * Command for creating a new game
 * 	Server end-point: /games/create POST
 * 
 * @author benthompson
 */
public class CreateCommand implements ICommand {
	
	
	String Username, Password, Title;
	int ID;
	int[] Players;

	public CreateCommand(String username, String password, String title, int id, int[] players) {
		this.Username = username;
		this.Password = password;
		this.Title = title;
		this.ID = id;
		this.Players = players;
	}

	@Override
	public String execute() {
		return null;
	}

}
