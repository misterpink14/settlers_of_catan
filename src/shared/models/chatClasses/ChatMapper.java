package shared.models.chatClasses;

import shared.models.MapperException;

public class ChatMapper 
{

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
		
	}

}
