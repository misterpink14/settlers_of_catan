package server.command.game;


import java.util.Map;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for getting a game's model
 * 	Server end-point: /game/model GET
 * 
 * @author benthompson
 */
public class ModelCommand extends ACommand {

	public ModelCommand(Map<String, String> cookies, IServerFacade facade) throws ServerException {
		super(cookies.get("catan.user"), facade, Integer.parseInt(cookies.get("catan.game")));
	}
	

	@Override
	public void execute() {
	}


	@Override
	public String getResponse() {
		return this.getFacade().getModel(this.gameID);
	}

}
