package shared.models.mapClasses;

@SuppressWarnings("serial")
public class InvalidHexTypeException extends Exception 
{
	public InvalidHexTypeException() {return;}

	public InvalidHexTypeException(String message) { super(message); }

	public InvalidHexTypeException(Throwable throwable) { super(throwable); }

	public InvalidHexTypeException(String message, Throwable throwable) 
	{
		super(message, throwable);
	}
}
