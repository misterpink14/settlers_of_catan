package shared.models.mapClasses;

import shared.models.MapperException;

public class MapMapper 
{
	public MapMapper() { }
	
	/**
	 * Uses json to build new Map classes to replace the current model
	 * 
	 * @param json json object containing information to build new classes in the model
	 * @exception MapperException if the deserialization process goes wrong
	 */
	public static Map deserialize(String json) throws MapperException
	{
		return new Map();
	}


}
