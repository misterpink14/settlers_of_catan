package server.command.moves;

import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for building a settlement
 * 	Server end-point: /moves/buildSettlement POST
 * 
 * @author benthompson
 */
public class BuildSettlementCommand extends ACommand {

	/**
	 * {
		  "type": "buildSettlement",
		  "playerIndex": "integer",
		  "vertexLocation": {
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
	public BuildSettlementCommand(String userJson, IServerFacade facade, String jsonBody) {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
