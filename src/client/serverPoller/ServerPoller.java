package client.serverPoller;

import javax.swing.Timer;

import client.serverProxy.ProxyInterface;
import shared.models.Game;

import java.awt.event.*;

public class ServerPoller 
{
	ProxyInterface ServerProxy;
	
	Game GameModel;
	
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
		 * Calls the ClientProxy and updates the GameModel with given JSON
		 * 
		 */
		private void updateModel()
		{
			
		}
		
	}
	
	
}
