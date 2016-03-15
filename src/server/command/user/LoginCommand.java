package server.command.user;

import server.command.AUserCommand;
import server.command.ICommand;
import server.facade.IServerFacade;

/**
 * Command for user login.
 * 	Server end-point: /user/login POST
 * 
 * @author benthompson
 */
public class LoginCommand extends AUserCommand implements ICommand {
	
	IServerFacade facade;

	public LoginCommand(String json, IServerFacade facade) {
		super(json);
		this.facade = facade;
	}

	@Override
	public String execute() {
		System.out.println("inexecute");
		return "USER: " + this.username + " PASS: " + this.password;
	}
}
