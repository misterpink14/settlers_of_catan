package server.database;

/**
 * 
 * @author Bo Pace
 *
 */

public class Registry {
	
	/**
	 * Loads the correct .jar file for the persistence plugin.
	 */
	public void loadConfig() {
		
	}
	
	/**
	 * Gets the plugin (SQLite3 or Mongo) as specified by the user.
	 * @return
	 */
	public IPersistencePlugin getPlugin() {
		return null;
	}

}
