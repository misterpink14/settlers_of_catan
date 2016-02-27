package client.login;

import javax.swing.JOptionPane;

import client.base.*;
import client.clientFacade.ClientFacade;
import client.misc.*;
import shared.communication.proxy.Credentials;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		Credentials cred = new Credentials(getLoginView().getLoginUsername(), getLoginView().getLoginPassword());
		
		try {
			String result = ClientFacade.getInstance().login(cred);
			// If log in succeeded
			if(result.equals("Success")) {
				getLoginView().closeModal();
				loginAction.execute();
			}
			else {
				JOptionPane.showMessageDialog((LoginView)this.getLoginView(), "Login Failed");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog((LoginView)this.getLoginView(), "An error occured");
		}
	}

	@Override
	public void register() {
		Credentials cred = new Credentials(getLoginView().getRegisterUsername(), getLoginView().getRegisterPassword());
		String result = ClientFacade.getInstance().createPlayer(cred);
		if(result.equals("Success")) {
			try {
				String result2 = ClientFacade.getInstance().login(cred);
				// If log in succeeded
				if(result2.equals("Success")) {
					getLoginView().closeModal();
					loginAction.execute();
				}
				else {
					JOptionPane.showMessageDialog((LoginView)this.getLoginView(), "Login Failed");
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog((LoginView)this.getLoginView(), "An error occured");
			}
		}
		else {
			JOptionPane.showMessageDialog((LoginView)this.getLoginView(), "You cannot register this name and password");
		}
	}

}

