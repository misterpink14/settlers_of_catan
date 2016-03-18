package server.command.games;

import server.command.AUserCommand;
import server.command.ICommand;
import server.facade.IServerFacade;

/**
 * Command for getting the list of games on the server
 * 	Server end-point: /games/list GET
 * 
 * @author benthompson
 */
public class ListCommand extends AUserCommand implements ICommand {

	public ListCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}

	
	@Override
	public String execute() {
		System.out.println("ListCommand execute");
		// TODO use this.getCredentials() to verify that this is an actual user
		return this.getFacade().getGamesList();
	}

}
