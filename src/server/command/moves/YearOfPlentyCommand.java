package server.command.moves;


import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for for playing a year of plenty card
 * 	Server end-point: /moves/Year_Of_Plenty POST
 * 
 * @author benthompson
 */
public class YearOfPlentyCommand extends ACommand {

	/**
	 * {
		  "type": "Year_of_Plenty",
		  "playerIndex": "integer",
		  "resource1": "Resource",
		  "resource2": "Resource"
		}
	 * 
	 * @param userJson
	 * @param facade
	 * @param jsonBody
	 * @throws ServerException 
	 */
	public YearOfPlentyCommand(String userJson, IServerFacade facade, String jsonBody) throws ServerException {
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
