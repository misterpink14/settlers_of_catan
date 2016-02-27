package client.communication;

import java.util.*;

import client.base.*;
import client.clientFacade.ClientFacade;
import shared.definitions.*;
import shared.models.chatClasses.Message;
import shared.models.playerClasses.Player;
import shared.observers.GameHistoryObserver;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {
	
	private GameHistoryObserver obs;

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		ClientFacade.getInstance().game.addObserver(obs);
		initFromModel();
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		
		List<Message> messages = ClientFacade.getInstance().game.getGameLog().getMessages();
		
		List<LogEntry> logEntries = new ArrayList<LogEntry>();
		for (Message m : messages) {
			logEntries.add(new LogEntry(getColor(m.getSource()), m.getMessage()));
		}
		if (logEntries.isEmpty()) {
			logEntries.add(new LogEntry(CatanColor.WHITE, "No Log Entries"));
		}
		
		getView().setEntries(logEntries);
	}

	private CatanColor getColor(String source) {
		List<Player> players = ClientFacade.getInstance().game.getPlayers().getPlayers();
		for (Player p : players) {
			if (source.equals(p.getName())) {
				return p.getColor();
			}
		}
		// Shouldn't ever get here... But had to have all paths covered
		// Should I throw an Exception instead?
		return null;
	}

	public void update(GameState gameState) {
		initFromModel();
	}
	
}

