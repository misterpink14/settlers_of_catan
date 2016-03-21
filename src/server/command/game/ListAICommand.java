package server.command.game;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for getting a list of all available AI players
 * 	Server end-point: /game/listAI GET
 * 
 * @author benthompson
 */
public class ListAICommand extends ACommand {

	public ListAICommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}
	

	@Override
	public void execute() {
		return;
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
