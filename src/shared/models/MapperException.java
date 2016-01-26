package shared.models;

@SuppressWarnings("serial")
public class MapperException extends Exception
{
	public MapperException() 
	{
		return;
	}

	public MapperException(String message) 
	{
		super(message);
	}

	public MapperException(Throwable throwable) 
	{
		super(throwable);
	}

	public MapperException(String message, Throwable throwable) 
	{
		super(message, throwable);
	}
}
