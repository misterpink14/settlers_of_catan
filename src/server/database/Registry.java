package server.database;

/**
 * 
 * @author Bo Pace
 *
 */

public class Registry {
	
	/**
	 * The plugin currently being used by the server.
	 */
	IPersistencePlugin plugin;
	
	/**
	 * Loads the correct .jar file for the persistence plugin.
	 * @param plugin The location of the plugin we want to load.
	 */
	public void loadConfig(String pluginLocation, int delta) {
		
	}
	
	/**
	 * Gets the plugin (SQLite3 or Mongo) as specified by the user.
	 * @return
	 */
	public IPersistencePlugin getPlugin() {
		return plugin;
	}

}
