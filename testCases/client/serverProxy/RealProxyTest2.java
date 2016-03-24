package client.serverProxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import shared.communication.proxy.CreateGameRequestParams;
import shared.communication.proxy.Credentials;
import shared.communication.proxy.JoinGameRequestParams;

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
			
		}
		catch (Exception e) {
			fail("Exception");
		}
		
		
		
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testLoginUser() {
		assertEquals(1,1);
	}
}