package server.command.moves;

import java.util.Map;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for building a road
 * 	Server end-point: /moves/buildRoad POST
 * 
 * @author benthompson
 */
public class BuildRoadCommand extends ACommand {

	/**
	 * {
		  "type": "buildRoad",
		  "playerIndex": "integer",
		  "roadLocation": {
		    "x": "integer",
		    "y": "integer",
		    "direction": "string"
		  },
		  "free": "Boolean"
		}
	 * 
	 * @param cookies
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public BuildRoadCommand(Map<String, String> cookies, IServerFacade facade, String jsonBody) throws ServerException {
		super(cookies.get("catan.user"), facade, Integer.parseInt(cookies.get("catan.game"))); 
		// TODO parse the jsonBody
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
