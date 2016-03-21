package server.command.game;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for getting a game's model
 * 	Server end-point: /game/model GET
 * 
 * @author benthompson
 */
public class ModelCommand extends ACommand {

	public ModelCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}
	

	@Override
	public void execute() {
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
