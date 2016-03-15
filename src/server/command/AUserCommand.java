package server.command;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AUserCommand {
	protected String username;
	protected String password;

	public AUserCommand(String json) {
		this.jsonDecode(json);
	}
	
	private void jsonDecode(String json) {
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		this.username = jsonObject.get("username").getAsString();
		this.password = jsonObject.get("password").getAsString();
	}

}
