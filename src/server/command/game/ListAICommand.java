package server.command.game;

import java.util.Map;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for getting a list of all available AI players
 * 	Server end-point: /game/listAI GET
 * 
 * @author benthompson
 */
public class ListAICommand extends ACommand {

	public ListAICommand(Map<String, String> cookies, IServerFacade facade) throws ServerException {
		super(cookies.get("catan.user"), facade);
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
