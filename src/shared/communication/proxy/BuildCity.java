package shared.communication.proxy;

import shared.locations.VertexLocation;

public class BuildCity
{

	/**
	 * The index of the player placing the city
	 */
	public int playerIndex;
	
	public VertexLocation vertexLocation;
	
	
	public BuildCity() 
	{}
	
	public BuildCity(int playerIndex, VertexLocation vertexLocation) {
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
	}


	
}
