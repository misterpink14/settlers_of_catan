package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for building a city
 * 	Server end-point: /moves/buildCity POST
 * 
 * @author benthompson
 */
public class BuildCityCommand extends ACommand {

	/**
	 * {
		  "type": "buildCity",
		  "playerIndex": "integer",
		  "vertexLocation": {
		    "x": "integer",
		    "y": "integer",
		    "direction": "string"
		  }
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 */
	public BuildCityCommand(String userJson, IServerFacade facade, String jsonBody) {
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
