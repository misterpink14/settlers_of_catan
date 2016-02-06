package shared.communication.proxy;

import shared.locations.VertexLocation;

public class BuildSettlement
{

	/**
	 * The index of the player building the settlement
	 */
	public int playerIndex;
	
	/**
	 * Whether or not this placement is free
	 */
	public Boolean free;
	
	public VertexLocation vertexLocation;
	
	public BuildSettlement() 
	{}


	
}
