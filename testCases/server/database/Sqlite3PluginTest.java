package server.database;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.database.dao.IGameDAO;
import server.database.dao.IUserDAO;
import server.managers.User;
import shared.communication.proxy.Credentials;
import shared.models.Game;

public class Sqlite3PluginTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
//		IPersistencePlugin plugin = new Sqlite3Plugin(5);
//		(new Sqlite3Plugin(5)).clear();
//		plugin.endTransaction();
	}

	@Test
	public void testTransaction() {
		
		IPersistencePlugin plugin = new Sqlite3Plugin(5);
		plugin.startTransaction();
		IUserDAO user = plugin.getUserDAO();
		try {
			user.createUser(new User(new Credentials("Sam","sam")));
			assertTrue(user.getAllUsers().size() > 0);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail("should not throw an exception");
		}
		plugin.endTransaction();
		plugin.clear();
	}
	
	 
	@Test
	public void testClear() {
		
		IPersistencePlugin plugin = new Sqlite3Plugin(5);
		plugin.startTransaction();
		IUserDAO user = plugin.getUserDAO();
		try {
			User u = new User(new Credentials("Samm","samm"),7);
			user.createUser(u);
			assertTrue(user.getAllUsers().size() > 0);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail("should not throw an exception");
		}
		plugin.endTransaction();
		
		plugin.clear();
		plugin.startTransaction();
		try {
			assertTrue(user.getAllUsers().size() == 0);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail("should not throw an exception");
		}
		plugin.endTransaction();
	}

}
