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
		super(facade);
	}
	

	@Override
	public void execute() {
		this.response = this.getFacade().getListAI();
	}

}
