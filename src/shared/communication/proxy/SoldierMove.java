package shared.communication.proxy;
import shared.locations;

public class SoldierMove
{

	/**
	 * The index of the player moving the soldier
	 */
	public int playerIndex;
	
	/**
	 * The index of the victim of the soldier
	 */
	public int victimIndex;
	
	/**
	 * The new hex location of the robber
	 */
	public HexLocation newLocation;
	
	public SoldierMove() 
	{}


	
}
