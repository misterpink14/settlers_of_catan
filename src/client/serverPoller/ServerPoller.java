package client.serverPoller;

import javax.swing.Timer;

import client.serverProxy.ProxyInterface;
import shared.models.Game;

import java.awt.event.*;

public class ServerPoller 
{
	ProxyInterface ServerProxy;
	
	Game GameModel;
	int versionID;
	
	/**Timer for updating the Model*/
	Timer UpdateTimer;
	
	TimerActionListener listener;
	
	
	/**
	 * Constructor. Pass the Proxy to the Poller for updating the Model. Pass the speed of
	 * 	the timer for refreshing
	 * 
	 * @param clientProxy
	 * @param speed
	 */
	public ServerPoller (ProxyInterface clientProxy, Game gameModel, int speed)
	{
		this.GameModel = gameModel;
		this.ServerProxy = clientProxy;
		versionID = gameModel.versionID();
		UpdateTimer = new Timer(speed, this.listener);
		UpdateTimer.start();
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
			this.updateModel();
			UpdateTimer.restart();
		}

		/**
		 * Calls the ServerProxy and updates the GameModel with given JSON
		 * 
		 */
		private void updateModel()
		{
			// Use the game model's version ID to get the most
			// recent model from the server.
			String updatedModelJSON = ServerProxy.getGameModel(versionID);
		}
		
	}
	
	
}
