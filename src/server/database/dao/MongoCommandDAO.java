package server.database.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import server.command.ACommand;
import shared.models.Game;
import shared.serializerJSON.Deserializer;
import shared.serializerJSON.Serializer;

public class MongoCommandDAO implements ICommandDAO {
	
	public String collectionName;
	public int delta;

	private MongoDatabase db;
	
	public MongoCommandDAO() {
		MongoClient mongoClient = new MongoClient();
		db = mongoClient.getDatabase("commandsTest");
		MongoCollection<Document> col = db.getCollection("commands");
		System.out.println(col.count());
	}

	@Override
	public int getCommandCount(int gameID) {
		MongoCollection<Document> col = db.getCollection("commands");
		FindIterable<Document> iterable = col.find(new Document().append("gameID", gameID));

		final int[] commandCount = {0};
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	int commandID = document.getInteger("commandID");
		    	if (commandCount[0] < commandID) {
		    		commandCount[0] = commandID;
		    	}
		    	
		    }
		});
		return commandCount[0];
	}

	@Override
	public void createCommand(ACommand command) {
		MongoCollection<Document> col = db.getCollection("commands");
		int commandID = this.getCommandCount(command.getGameID()) + 1;
		col.insertOne(new Document().append("gameID", command.getGameID()).append("commandID", commandID));
	}

	@Override
	public List<ACommand> getAllCommands() {
		MongoCollection<Document> col = db.getCollection("commands");
		FindIterable<Document> iterable = col.find();
		
		final ArrayList<ACommand> commands = new ArrayList<ACommand>();
		
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	ACommand command = null;
		    	//Deserialize stuff
		    	
		    }
		});
		
		return commands;
	}

	@Override
	public void clear() {
		db.getCollection("commands").drop();

	}

	@Override
	public void clearCommands(int gameID) {
		db.getCollection("commands").deleteMany(new Document().append("gameID", gameID));
		
	}

}
