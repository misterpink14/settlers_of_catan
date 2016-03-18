package server.command.user;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for user login.
 * 	Server end-point: /user/login POST
 * 
 * @author benthompson
 */
public class LoginCommand extends ACommand {

	public LoginCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}

	@Override
	public String execute() {
		System.out.println("execute login");
		return this.getFacade().login(this.getCredentials());
	}
}
