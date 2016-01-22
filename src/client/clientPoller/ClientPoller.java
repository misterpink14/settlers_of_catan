package client.clientPoller;

import client.clientProxy.ProxyInterface;
import shared.models.Game;

public class ClientPoller {
	ProxyInterface ClientProxy;
	
	Game GameModel;
	
	
	public ClientPoller (ProxyInterface clientProxy)
	{
		this.ClientProxy = clientProxy;
	}
	
	
	
	/**
	 * Calls the ClientProxy and updates the GameModel with given JSON
	 * 
	 */
	public void updateModel()
	{
		
	}
	
	
}
