package server.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.command.ACommand;
import server.database.DatabaseException;
import server.database.DatabaseRepresentation;
import shared.serializerJSON.Deserializer;
import shared.serializerJSON.Serializer;

public class SqlCommandDAO implements ICommandDAO {
	
	private int delta;
	private DatabaseRepresentation db;
	
	public SqlCommandDAO(int delta) {
		this.delta = delta;
	}
	
	/**
	 * Returns the delta value
	 * @return delta
	 */
	public int getDelta() {
		return delta;
	}
	
	/**
	 * Sets the delta value
	 * @param delta
	 */
	public void setDelta(int delta) {
		this.delta = delta;
	}

	@Override
	public int getCommandCount(int gameID) throws DatabaseException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT COUNT(*) FROM Commands WHERE gameID = ?";
			stmt = db.getConnection().prepareStatement(query);
			stmt.setInt(1, gameID);
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new DatabaseException("Unable to retrieve command count");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Unable to retrieve command count", e);
		} finally {
			DatabaseRepresentation.safeClose(rs);
			DatabaseRepresentation.safeClose(stmt);
		}
	}

	@Override
	public void createCommand(ACommand command) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			String query = "INSERT INTO Commands (commandJSON, gameID) VALUES (?, ?)";
			String commandJSON = Serializer.getInstance().serializeCommand(command);
			stmt = db.getConnection().prepareStatement(query);
			stmt.setString(1, commandJSON);
			stmt.setInt(2, command.getGameID());
			
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not create command");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Could not create command");
		} finally {
			DatabaseRepresentation.safeClose(stmt);
		}
	}

	@Override
	public void clear() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			String clearCommands = "DELETE FROM Commands";
			stmt = db.getConnection().prepareStatement(clearCommands);
			
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not clear commands");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Could not clear commands", e);
		} finally {
			DatabaseRepresentation.safeClose(stmt);
		}
	}

	@Override
	public List<ACommand> getAllCommands() throws DatabaseException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ACommand> commands = new ArrayList<ACommand>();
		try {
			String query = "SELECT commandID, commandJSON, gameID FROM Commands";
			stmt = db.getConnection().prepareStatement(query);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				int commandID = rs.getInt(1);
				String commandJSON = rs.getString(2);
				int gameID = rs.getInt(3);
				ACommand command = null; // Need to figure out how I'm creating new commands...
				JsonObject jsonObj = new JsonParser().parse(commandJSON).getAsJsonObject();
				//TODO Uncomment the following line when a method is written to implement it.
				//Deserializer.getInstance().deserialize(command, commandJSON);
				commands.add(command);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Could not get all commands", e);
		} finally {
			DatabaseRepresentation.safeClose(rs);
			DatabaseRepresentation.safeClose(stmt);
		}
		return commands;
	}

	@Override
	public void clearCommands(int gameID) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			String clearCommands = "DELETE FROM Commands WHERE gameID = ?";
			stmt = db.getConnection().prepareStatement(clearCommands);
			
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not delete commands from game");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Could not delete commands from game");
		} finally {
			DatabaseRepresentation.safeClose(stmt);
		}
	}

}
