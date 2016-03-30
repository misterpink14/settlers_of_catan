package client.serverProxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.communication.proxy.*;

public class RealProxyTest2 {

	RealProxy realProxy = null;
	
	@Before
	public void setUp() throws Exception {
		this.realProxy = new RealProxy("http://localhost:8081");
		
		CreateGameRequestParams createParams = new CreateGameRequestParams("test", true, true, true);
		this.realProxy.createGame(createParams);
		
		JoinGameRequestParams params = new JoinGameRequestParams();
		params.id = 1;
		
		Credentials credentials = new Credentials();
		try {
			credentials.username = "personOne";
			credentials.password = "personOne";
			assertEquals(this.realProxy.registerUser(credentials), "Success");
			assertEquals(this.realProxy.loginUser(credentials), "Success");
			params.color = "blue";
			this.realProxy.joinGame(params);
			
			credentials.username = "personTwo";
			credentials.password = "personTwo";
			assertEquals(this.realProxy.registerUser(credentials), "Success");
			assertEquals(this.realProxy.loginUser(credentials), "Success");
			params.color = "red";
			this.realProxy.joinGame(params);
			
			credentials.username = "personThree";
			credentials.password = "personThree";
			assertEquals(this.realProxy.registerUser(credentials), "Success");
			assertEquals(this.realProxy.loginUser(credentials), "Success");
			params.color = "green";
			this.realProxy.joinGame(params);
			
			credentials.username = "personFour";
			credentials.password = "personFour";
			assertEquals(this.realProxy.registerUser(credentials), "Success");
			assertEquals(this.realProxy.loginUser(credentials), "Success");
			params.color = "puce";
			this.realProxy.joinGame(params);
			
			//sets up four users
			
		}
		catch (Exception e) {
			//Setup is only needed for the initial setup of the server
			credentials.username = "personOne";
			credentials.password = "personOne";
			assertEquals(this.realProxy.loginUser(credentials), "Success");
			params.color = "blue";
			this.realProxy.joinGame(params);
			//fail("Exception");
		}
		
	}

	@Test
	public void testLoginUser() {
		try {
			String response = realProxy.loginUser(new Credentials("personOne", "personOne"));
			Boolean success = response.equals("Success");
			assertTrue(success);
			
			response = realProxy.loginUser(new Credentials("personOnes", "personOnes"));
			Boolean failure = response.equals("Success");
			assertFalse(failure);

		} catch (Exception e) {
			fail("shouldn't get an exception");
		}
	}
	
	@Test
	public void testRegisterUser() {
		try {
			String response = realProxy.registerUser(new Credentials("personFive", "personFive"));
			Boolean success = response.equals("Success");
			assertTrue(success);
			
			response = realProxy.loginUser(new Credentials("personFive", "personFive"));
			success = response.equals("Success");
			assertTrue(success);

		} catch (Exception e) {
			fail("shouldn't get an exception");
		}
	}
	
	@Test
	public void testListGames() {
		try {
			String response = realProxy.getGamesList();
			Boolean success = response.length() > 0;
			assertTrue(success);

		} catch (Exception e) {
			fail("shouldn't get an exception");
		}
	}
	
	@Test
	public void testCreateGame() {
		try {
			String response = realProxy.createGame(new CreateGameRequestParams("testCreate", true, true, true));
			Boolean success = response.length() > 0;
			assertTrue(success);

		} catch (Exception e) {
			fail("shouldn't get an exception");
		}
	}
	
	@Test
	public void testGetGameModel() {
		try {
			
			String response = realProxy.getGameModel(-1);
			Boolean success = response.length() > 0;
			assertTrue(success);

		} catch (Exception e) {
			fail("shouldn't get an exception");
		}
	}
	
	@Test
	public void testBuildRoad() {
		try {
			String response = realProxy.buildRoad(new BuildRoad(0, true, 0, 0, "NW"));
			Boolean success = response.length() > 0;
			assertTrue(success);

		} catch (Exception e) {
			fail("shouldn't get an exception");
		}
	}
	
	@Test
	public void testBuildSettlement() {
		try {
			String response = realProxy.buildSettlement(new BuildSettlement(0, true, 0, 0, "NW"));
			Boolean success = response.length() > 0;
			assertTrue(success);
			
		} catch (Exception e) {
			fail("shouldn't get an exception");
		}
	}
}
