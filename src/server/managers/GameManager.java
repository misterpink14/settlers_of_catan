package server.managers;

import java.util.ArrayList;
import java.util.List;

import shared.models.Game;

/**
 * GameManager Class
 * @author Skyler
 *
 */
public class GameManager {

	
	private List<Game> gamesList;
	
	public GameManager() 
	{
		this.gamesList = new ArrayList<Game>();
	}
	
	/**
	 * Adds a game to the list of games
	 * @param game
	 */
	public void addGame(Game game)
	{
		gamesList.add(game);
	}
	
	/**
	 * Sets the gamesList to the passed in gamesList
	 * @param gamesList
	 */
	public void setGames(List<Game> gamesList)
	{
		this.gamesList = gamesList;
	}

	/**
	 * Returns the list of games
	 * @return List<Game>
	 */
	public List<Game> getGames()
	{
		return gamesList;
	}
}
