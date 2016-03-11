package server.managers;

import java.util.ArrayList;
import java.util.List;

import shared.models.UserManager.User;

/**
 * UserManager class
 * @author Skyler
 *
 */
public class UserManager {
	
	private List<User> usersList;
	
	public UserManager() 
	{
		usersList = new ArrayList<User>();
	}
	
	/**
	 * Adds a user to the list of users
	 * @param user
	 */
	public void addUser(User user)
	{
		usersList.add(user);
	}
	
	/**
	 * Sets the list of users to be the passed in list
	 * @param usersList
	 */
	public void setUsers(List<User> usersList)
	{
		this.usersList = usersList;
	}

	/**
	 * Returns the list of users
	 * @return List<User>
	 */
	public List<User> getUsers()
	{
		return usersList;
	}
}
