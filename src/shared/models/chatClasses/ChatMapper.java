package shared.models.chatClasses;

import com.google.gson.Gson;

import shared.models.MapperException;

public class ChatMapper 
{
	static Gson gson = new Gson();
	
	/**
	 * Replaces chat classes using json input
	 */
	public ChatMapper() {}
	
	/**
	 * Uses json to build new classes to replace the current model
	 * @param json json object containing information to build new classes in the model
	 * @exception mapperException if the deserialization process goes wrong.
	 * @throws MapperException
	 */
	public static void deserialize(String json) throws MapperException
	{
		_deserialize(json);
	}

	private static GameChat _deserialize(String json) throws MapperException
	{
		return gson.fromJson(json, GameChat.class);
	}
}
