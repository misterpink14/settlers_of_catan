package server.facade;

import java.sql.SQLException;
import server.database.*;
import shared.communication.*;
/**
 * The server facade executes the commands from the client communicator. It recieves the direction to execute from the Http handlers and
 * Uses sql statements contained in the DAO classes to recieve the necessary information for the methods. It returns specialized packets of
 * information to be returned to the client.
 * @author ssnyder
 */
public class ServerFacade {
	
	/**
	 * Validates the username and password the client entered and returns a success or failure
	 * @param input
	 * @return
	 * @throws DatabaseException
	 * @throws SQLException
	 */
	/*public static SomeTransferObject ValidateUser(ValidateUserInput input) throws DatabaseException, SQLException
	{
		DatabaseRepresentation db = new DatabaseRepresentation();
		
		db.startTransaction();
		return null;
		
	}*/
	
}