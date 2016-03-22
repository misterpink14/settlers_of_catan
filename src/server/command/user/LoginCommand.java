package server.command.user;

import java.net.URLEncoder;

import com.google.gson.JsonObject;

import server.ServerException;
import server.command.ACommand;
import server.facade.IServerFacade;

/**
 * Command for user login.
 * 	Server end-point: /user/login POST
 * 
 * @author benthompson
 */
public class LoginCommand extends ACommand {

	public LoginCommand(String userJson, IServerFacade facade) {
		super(userJson, facade);
	}

	@Override
	public void execute() throws ServerException {
		System.out.println("execute login");
		this.response = this.getFacade().login(this.getCredentials());
	}

	@Override
	public String getCookie() {
		String cookie = "catan.user=";
		
		JsonObject cookieJson = new JsonObject();
		cookieJson.addProperty("name", this.getCredentials().username);
		cookieJson.addProperty("password", this.getCredentials().password);
		cookieJson.addProperty("playerID", 0);
		
		@SuppressWarnings("deprecation")
		String encodedCookie = URLEncoder.encode(cookieJson.toString());
		
		cookie += encodedCookie + ";Path=/;";
		
		return cookie;
	}
}
