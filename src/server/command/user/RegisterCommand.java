package server.command.user;

import server.command.ACommand;
import server.facade.IServerFacade;


/**
 * Command used for user registration
 * 	Server end-point: /user/register POST
 * 
 * @author benthompson
 */
public class RegisterCommand extends ACommand {

	public RegisterCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}
	
	
	
	@Override
	public void execute() {
		System.out.println("execute register");
		this.response = this.getFacade().register(this.getCredentials());
	}



	@Override
	public String getResponse() {
		return this.response;
	}



	@Override
	public String getCookie() {
		// TODO Auto-generated method stub
		return "";
	}

}
