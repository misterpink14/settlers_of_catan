package server.command.user;

import server.command.ICommand;

/**
 * Command for user login.
 * 	Server end-point: /user/login POST
 * 
 * @author benthompson
 */
public class LoginCommand implements ICommand {
	
	
	String Username, Password;

	public LoginCommand(String username, String password) {
		this.Username = username;
		this.Password = password;
	}

	@Override
	public String execute() {
		return "good";
	}

}
