package shared.models.mapClasses;

import shared.definitions.PieceType;
import shared.models.playerClasses.Player;

public class Piece 
{
	private PieceType Type; // Enumeration -> ROAD, SETTLEMENT, CITY, ROBBER
	
	private int OwnerIndex; // Index of the player that owns this piece

	
	
	public Piece() {}
	
	
// CONSTRUCTORS
	public Piece(PieceType type)
	{
		this.Type = type;
	}
	
	
	public Piece(PieceType type, int ownerIndex)
	{
		this.Type = type;
		this.OwnerIndex = ownerIndex;
	}
	
	

// GETTERS
	public PieceType getType() 
	{
		return this.Type;
	}
	
	
	public int getOwner() 
	{
		return this.OwnerIndex;
	}
	
	
// SETTERS
	public void setType(PieceType type)
	{
		this.Type = type;
	}
	
	
	public void setOwner(int ownerIndex)
	{
		this.OwnerIndex = ownerIndex;
	}
	
	

// PUBLIC METHOD
	/**
	 * Check if a player is the owner of the piece
	 * 
	 * @param p
	 * @return
	 */
	public boolean isOwner(int ownerIndex)
	{
		return this.OwnerIndex == ownerIndex;
	}
	
}
