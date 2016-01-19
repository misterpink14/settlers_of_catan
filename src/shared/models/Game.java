package shared.models;

import java.util.ArrayList;
/**
 * 
 * @author Stephen Snyder
 *
 */
public class Game {
	/**The map contains all information having to do with the board.*/
	Map map = new Map();
	/**The bank contains all resource cards that do not belong to a player.*/
	Bank bank = new Bank();
	/**The cardDeck contains all the development cards not belonging to a player.*/
	CardDeck cardDeck = new CardDeck();
	/**The players array contains four players that will be used by four clients.*/
	ArrayList<Player> players = new ArrayList<Player>();
	Player one = new Player();
	Player two = new Player();
	Player three = new Player();
	Player four = new Player();
	
	/**
	 * The Game class is a master class that contains all information having to do with a specific game
	 * of settlers of catan.
	 */
	public Game() {
		this.players.add(one);
		this.players.add(two);
		this.players.add(three);
		this.players.add(four);
	}

}
