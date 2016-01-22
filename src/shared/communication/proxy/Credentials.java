package shared.communication.proxy;

public class Credentials
{

	/**
	 * The username of the user
	 */
	public String username;
	/**
	 * The password of the user
	 */
	public String password;
	
	public Credentials() 
	{}
	
	/**
	 * @param username
	 * @param password
	 */
	public Credentials(String user, String password) 
	{
		this.username = username;
		this.password = password;
	}

	
}
