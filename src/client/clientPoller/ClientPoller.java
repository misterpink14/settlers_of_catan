package client.clientPoller;

import javax.swing.Timer;

import client.clientProxy.ProxyInterface;
import shared.models.Game;

import java.awt.event.*;

public class ClientPoller 
{
	ProxyInterface ClientProxy;
	
	Game GameModel;
	
	/**Timer for updating the Model*/
	Timer UpdateTimer;
	
	TimerActionListener listener;
	
	
	public ClientPoller (ProxyInterface clientProxy, int speed)
	{
		this.ClientProxy = clientProxy;
		UpdateTimer = new Timer(speed, this.listener);
	}
	
	
	class TimerActionListener implements ActionListener 
	{

		/**
		 * When an event happens within the timer, update the model
		 * 
		 * */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
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
