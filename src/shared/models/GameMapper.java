package shared.models;

import com.google.gson.Gson;

import shared.models.cardClasses.CardMapper;
import shared.models.chatClasses.ChatMapper;
import shared.models.logClasses.LogMapper;
import shared.models.mapClasses.Map;
import shared.models.mapClasses.MapMapper;
import shared.models.playerClasses.PlayerMapper;
import shared.models.tradeClasses.TradeMapper;

public class GameMapper 
{
	static Gson gson = new Gson();
	
	public GameMapper() {}
	
	/**
	 * Uses json to build new classes to replace the current model
	 * 
	 * @param json json object containing information to build new classes in the model
	 * @exception mapperException if the deserialization process goes wrong
	 */
	public static void deserialize(String json) throws MapperException
	{
		_deserialize(json);
		TradeMapper.deserialize(json);
		MapMapper.deserialize(json);
		PlayerMapper.deserialize(json);
		CardMapper.deserialize(json);
		ChatMapper.deserialize(json);
		LogMapper.deserialize(json);
	}
	
	
	/**
	 * Uses json to build new Game class to replace the current model
	 * 
	 * @param json json object containing information to build new classes in the model
	 * @exception mapperException if the deserialization process goes wrong
	 */
	private static Game/*Map*/ _deserialize(String json) throws MapperException
	{
		return gson.fromJson(json, Game.class);
	}

}
