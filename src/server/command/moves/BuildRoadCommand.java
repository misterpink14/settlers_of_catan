package server.command.moves;

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
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public BuildRoadCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
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
