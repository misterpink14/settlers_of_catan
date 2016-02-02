package shared.locations;

public class RobberLocation 
{
	private HexLocation hexLoc;
	
	
	
	public RobberLocation(HexLocation loc)
	{
		setHexLoc(hexLoc);
	}
	
	
	public HexLocation getHexLoc()
	{
		return hexLoc;
	}
	
	
	
	/**
	 * Can do method for placing robber. Robber cannot be placed at the same location.
	 * 
	 * @param loc
	 * @return
	 */
	public boolean canPlaceRobber(HexLocation loc)
	{
		if (loc.equals(hexLoc))
		{
			return false;
		}
		return true;
	}
	
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}

}
