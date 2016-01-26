package shared.models.tradeClasses;

import shared.models.MapperException;

public class TradeMapper 
{
	public TradeMapper() {}
	
	/**
	 * Uses json to build new Trade classes to replace the current model
	 * 
	 * @param json json object containing information to build new classes in the model
	 * @exception MapperException if the deserialization process goes wrong
	 */
	public static Trade deserialize(String json) throws MapperException
	{
		return new Trade();
	}

}
