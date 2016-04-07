package server.database.dao;

import java.util.List;

import server.command.ACommand;
import server.database.DatabaseException;

public class SqlCommandDAO implements ICommandDAO {
	
	public int numCommandsToSave;
	
	public SqlCommandDAO(int numCommandsToSave) {
		this.numCommandsToSave = numCommandsToSave;
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
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ACommand> getAllCommands() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearCommands(int gameID) {
		// TODO Auto-generated method stub
		
	}

}
