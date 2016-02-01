package shared.models.logClasses;

import java.util.ArrayList;
import java.util.List;

public class GameLog {
	/**An array that holds the entries in the game log*/
	ArrayList<Entry> entries = new ArrayList<Entry>();
	
	/**
	 * This object keeps track of the logs entered during the course of the game
	 */
	public GameLog() {}

	/**
	 * Uses json to add entries to the game log
	 */
	public void importLog(String json) {
		
	}
	
	/**
	 * Adds a log entry to the entries list
	 * 
	 * @param entry The entry to be added to the log
	 */
	public void addEntry(Entry entry) {
		entries.add(entry);
	}
	
	/**Gets all log entries*/
	public List<Entry> getEntries() {
		return entries;
	}
}
