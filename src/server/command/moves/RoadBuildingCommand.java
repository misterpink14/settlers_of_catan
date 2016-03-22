package server.command.moves;


import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for playing a road building card
 * 	Server end-point: /moves/Road_Building POST
 * 
 * @author benthompson
 */
public class RoadBuildingCommand extends ACommand {

	/**
	 * {
		  "type": "Road_Building",
		  "playerIndex": "integer",
		  "spot1": {
		    "x": "integer",
		    "y": "integer",
		    "direction": "string"
		  },
		  "spot2": {
		    "x": "integer",
		    "y": "integer",
		    "direction": "string"
		  }
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public RoadBuildingCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
		super(userJson, facade);
		// TODO parse the jsonBody
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
