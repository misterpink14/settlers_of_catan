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

	public LoginCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}

	@Override
	public String execute() {
		System.out.println("execute login");
		return this.getFacade().login(this.getCredentials());
	}
}
