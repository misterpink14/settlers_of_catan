package server.command;

/**
 * The command interface for interacting with the server
 * 
 * @author benthompson
 */
public interface ICommand {
	
	/**
	 * Initiate the command. Calls the server facade and then returns the json 
	 * 	string to return to the user
	 * 
	 * @return
	 */
	public String execute();

}
