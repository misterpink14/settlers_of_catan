package server.command.user;

import server.command.ICommand;

public class LoginCommand implements ICommand {
	
	
	String Username;
	String Password;

	public LoginCommand(String username, String password) {
		this.Username = username;
		this.Password = password;
	}

	@Override
	public String execute() {
		return null;
	}

}
