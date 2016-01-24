package shared.models.mapClasses;

import shared.definitions.PieceType;
import shared.models.playerClasses.Player;

public class Piece 
{
	private PieceType Type;
	
	private String Owner;
	
	
	/**
	 * Get the Piece type
	 * 
	 * @return
	 */
	public PieceType getType() 
	{
		return this.Type;
	}
	
	
	/**
	 * Check if a player is the owner of the piece
	 * 
	 * @param p
	 * @return
	 */
	public boolean isOwner(String player)
	{
		return this.Owner.equals(player);
	}
	
}
