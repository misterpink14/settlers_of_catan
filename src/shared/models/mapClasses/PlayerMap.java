package shared.models.mapClasses;

import java.util.ArrayList;
import java.util.HashMap;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class PlayerMap 
{
	HashMap<Integer, PlayerPieces> PlayerPieces = new HashMap<Integer, PlayerPieces>();
	
	
	
// CONSTRUCTOR
	public PlayerMap() {}


	
// Public METHODS
	public void addSettlement(VertexLocation loc, int playerIndex)
	{
		this.PlayerPieces.get(playerIndex).addSettlement(loc);
	}
	
	
	public void addCity(VertexLocation loc, int playerIndex)
	{
		this.PlayerPieces.get(playerIndex).addCity(loc);
	}
	
	
	public void addRoad(EdgeLocation loc, int playerIndex)
	{
		this.PlayerPieces.get(playerIndex).addRoad(loc);
	}
	
	
	
}
