package server.command.user;

import server.command.AUserCommand;
import server.command.ICommand;
import server.facade.IServerFacade;


/**
 * Command used for user registration
 * 	Server end-point: /user/register POST
 * 
 * @author benthompson
 */
public class RegisterCommand extends AUserCommand implements ICommand {

	public RegisterCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}
	
	
	
	@Override
	public String execute() {
		System.out.println("execute login");
		return this.getFacade().register(this.getCredentials());
	}

}
