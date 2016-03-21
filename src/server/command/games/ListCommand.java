package server.command.games;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for getting the list of games on the server
 * 	Server end-point: /games/list GET
 * 
 * @author benthompson
 */
public class ListCommand extends ACommand {

	public ListCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}

	
	@Override
	public void execute() {
		System.out.println("ListCommand execute");
		// TODO use this.getCredentials() to verify that this is an actual user
		this.getFacade().getGamesList();
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
