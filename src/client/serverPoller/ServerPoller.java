package client.serverPoller;

import javax.swing.Timer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import client.clientFacade.ClientFacade;
import client.serverProxy.ProxyInterface;
import shared.models.Game;
import shared.serializerJSON.Deserializer;

import java.awt.event.*;

public class ServerPoller 
{
	ProxyInterface ServerProxy;
	
	Deserializer deserializer = new Deserializer();
	Game GameModel;
	int versionID;
	
	/**Timer for updating the Model*/
	Timer UpdateTimer;
	
	TimerActionListener listener;
	
	
	/**
	 * Constructor. Pass the Proxy to the Poller for updating the Model. Pass the speed of
	 * 	the timer for refreshing
	 * 
	 * @param ClientFacade
	 * @param speed
	 */
	public ServerPoller (ClientFacade facade, int speed)
	{
		this.GameModel = facade.game;
		this.ServerProxy = facade.proxy;
		listener = new TimerActionListener();
		versionID = GameModel.versionID();
		UpdateTimer = new Timer(speed, this.listener);
		UpdateTimer.start();
	}
	
	//this is for testing
	public void updateModelTester(String gameModelString)
	{
		JsonParser parser = new JsonParser();
		JsonObject gameModelJson = parser.parse(gameModelString).getAsJsonObject();
		GameModel = deserializer.deserialize(gameModelJson);
	}
	
	class TimerActionListener implements ActionListener 
	{
		
		/**
		 * When an event happens within the timer, update the model
		 * 
		 * */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			updateModel();
			UpdateTimer.restart();
		}	
		
		/**
		 * Calls the ClientProxy and updates the GameModel with given JSON
		 * Calls the ServerProxy and updates the GameModel with given JSON
		 */
		private void updateModel()
		{
			// Use the game model's version ID to get the most
			// recent model from the server.
			String gameModelString;
			gameModelString = ServerProxy.getGameModel(GameModel.versionID());
			JsonParser parser = new JsonParser();
			JsonObject gameModelJson = parser.parse(gameModelString).getAsJsonObject();
			GameModel = deserializer.deserialize(gameModelJson);
		}
	}	
	
}
