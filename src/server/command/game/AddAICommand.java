package server.command.game;

import server.command.ACommand;

/**
 * Command for adding an AI to a given game
 * 	Server end-point: /game/addAI POST
 * 
 * @author benthompson
 */
public class AddAICommand extends ACommand {
	
	
	String Username, Password, AIType;
	int GameID;

	public AddAICommand(String username, String password, int gameID, String aiType) {
		this.Username = username;
		this.Password = password;
		this.GameID = gameID;
		this.AIType = aiType;
	}

	@Override
	public String execute() {
		return null;
	}

}
