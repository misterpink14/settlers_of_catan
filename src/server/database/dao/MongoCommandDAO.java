package server.database.dao;

import java.util.List;

import server.command.ACommand;

public class MongoCommandDAO implements ICommandDAO {
	
	public String collectionName;
	public int delta;

	public MongoCommandDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCommandCount(int gameID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void createCommand(ACommand command) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ACommand> getAllCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearCommands(int gameID) {
		// TODO Auto-generated method stub
		
	}

}