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
	
	
	public ServerPoller (ProxyInterface clientProxy, int speed)
	{
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
