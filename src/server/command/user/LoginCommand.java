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
	public void execute() {
		System.out.println("execute login");
		this.getFacade().login(this.getCredentials());
	}

	@Override
	public String getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCookie() {
		// TODO Auto-generated method stub
		return null;
	}
}
