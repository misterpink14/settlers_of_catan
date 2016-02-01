package shared.models.mapClasses;

import shared.definitions.PieceType;
import shared.models.playerClasses.Player;

public class Piece 
{
	private PieceType Type; // Enumeration -> ROAD, SETTLEMENT, CITY, ROBBER
	
	private int OwnerId; // Index of the player that owns this piece

	
	
	public Piece() {}
	
	
// CONSTRUCTORS
	public Piece(PieceType type)
	{
		this.Type = type;
		this.OwnerId = -1;
	}
	
	
	public Piece(PieceType type, int ownerId)
	{
		this.Type = type;
		this.OwnerId = ownerId;
	}
	
	

// GETTERS
	public PieceType getType() 
	{
		return this.Type;
	}
	
	
	public int getOwner() 
	{
		return this.OwnerId;
	}
	
	
// SETTERS
	public void setType(PieceType type)
	{
		this.Type = type;
	}
	
	
	public void setOwner(int ownerId)
	{
		this.OwnerId = ownerId;
	}
	
	

// PUBLIC METHOD
	/**
	 * Check if a player is the owner of the piece
	 * 
	 * @param p
	 * @return
	 */
	public boolean isOwner(int ownerId)
	{
		return this.OwnerId == ownerId;
	}
	
}
