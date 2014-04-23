package cl.pablo.apptest.error;

public class ErrorHandler 
{
	

	final static int NoError=0;
	final static int ConnectionError=1;
	final static int JsonProblem=2;
	final static int JsonEmpty=3;
	final static int unknownError=100;
	final static int JsonNotInterpreted=11;

	
	
	private static int LastError;
	
	
	
	
	public ErrorHandler()
	{
		
	}
	
	
	
	public static int getLastError()
	{
		return LastError;
	}
	
	
	public static void setLastError( int Error)
	{
		LastError=Error;
	}

}
