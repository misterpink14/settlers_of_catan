package server.database.dao;

import server.command.ACommand;

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
	public String getAllCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
