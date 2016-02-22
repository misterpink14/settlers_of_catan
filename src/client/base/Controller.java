package client.base;

import client.clientFacade.ClientFacade;

/**
 * Base class for controllers
 */
public abstract class Controller implements IController
{
	
	private IView view;
	private ClientFacade clientFacade;
	
	protected Controller(IView view)
	{
		setView(view);
	}
	
	private void setView(IView view)
	{
		this.view = view;
	}
	
	@Override
	public IView getView()
	{
		return this.view;
	}
	
	public void setClientFacade(ClientFacade clientFacade) 
	{
		this.clientFacade = clientFacade;
	}
	
	public ClientFacade getClientFacade() 
	{
		return this.clientFacade;
	}
}

