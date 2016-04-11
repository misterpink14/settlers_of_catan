package server.database.dao;

import java.util.List;

import server.command.ACommand;
import shared.models.Game;

/**
 * 
 * @author Bo Pace
 *
 */
public class MongoGameDAO implements IGameDAO {

	@Override
	public Game getGame(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Game> getAllGames() {
		return null;
	}

	public int getCommandCount(int gameID) {
		return -1;
	}
	
	public void createCommand(ACommand command) {
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
