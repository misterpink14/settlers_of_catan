package server.command.user;

import server.command.ICommand;


/**
 * Command used for user registration
 * 	Server end-point: /user/register POST
 * 
 * @author benthompson
 */
public class RegisterCommand implements ICommand {

	
	String Username, Password;
	
	public RegisterCommand(String username, String password) {
		this.Username = username;
		this.Password = password;
	}
	
	@Override
	public String execute() {
		return null;
	}

}
