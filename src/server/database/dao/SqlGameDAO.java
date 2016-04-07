package server.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import server.database.DatabaseException;
import server.database.DatabaseRepresentation;
import shared.models.Game;
import shared.serializerJSON.Deserializer;

/**
 * 
 * @author Bo Pace
 *
 */
public class SqlGameDAO implements IGameDAO {

	DatabaseRepresentation db;
	
	public SqlGameDAO(DatabaseRepresentation db) {
		this.db = db;
	}

	@Override
	public Game getGame(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	public List<Game> getAllGames() throws DatabaseException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Game> games = new ArrayList<Game>();
		try {
			String query = "SELECT gameID, title, gameJSON FROM Games";
			stmt = db.getConnection().prepareStatement(query);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				int gameID = rs.getInt(1);
				String title = rs.getString(2);
				String gameJSON = rs.getString(3);
				Game g = new Game();
				g.setId(gameID);
				g.setTitle(title);
				JsonObject jsonObj = new JsonParser().parse(gameJSON).getAsJsonObject();
				Deserializer.getInstance().deserialize(g, jsonObj);
				games.add(g);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Could not get all games", e);
		} finally {
			DatabaseRepresentation.safeClose(rs);
			DatabaseRepresentation.safeClose(stmt);
		}
		return games;
	}

	@Override
	public void clear() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			String clearGames = "DROP TABLE IF EXISTS Games;" +
					"CREATE TABLE Games (" +
					"gameID INTEGER PRIMARY KEY , " +
					"Title TEXT, " +
					"gameJSON TEXT NOT NULL,  " +
					"commandsToSave INTEGER);";
			stmt = db.getConnection().prepareStatement(clearGames);
			
			if (stmt.executeUpdate() != 1) {
				throw new DatabaseException("Could not clear games");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Could not clear games", e);
		} finally {
			DatabaseRepresentation.safeClose(stmt);
		}
	}
}
