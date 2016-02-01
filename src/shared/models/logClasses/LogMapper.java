package shared.models.logClasses;

import com.google.gson.Gson;

import shared.models.MapperException;

public class LogMapper 
{
	static Gson gson = new Gson();
	
	/**
	 * Replaces log classes using json input
	 */
	public LogMapper() {}
	
	/**
	 * Uses json to build new classes to replace the current model
	 * @param json json object containing information to build new classes in the model
	 * @exception mapperException if the deserialization process goes wrong
	 * @throws MapperException
	 */
	public static void deserialize(String json) throws MapperException
	{
		_deserialize(json);
	}
	
	private static GameLog _deserialize(String json) throws MapperException
	{
		return gson.fromJson(json, GameLog.class);
	}

}
