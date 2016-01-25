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
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}
}
