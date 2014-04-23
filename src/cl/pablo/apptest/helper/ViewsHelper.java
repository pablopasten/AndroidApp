package cl.pablo.apptest.helper;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ViewsHelper 
{
	
	

	final static int NoError=0;
	final static int ConnectionError=1;
	final static int JsonProblem=2;
	final static int JsonEmpty=3;
	final static int unknownError=100;
	final static int JsonNotInterpreted=11;
	
	private static String defStatus;
	
	public ViewsHelper()
	{
		
	}
	
	
	
	public static void setStatusComms(int Status)
	{
		
		switch(Status)
		{
			case ConnectionError:
			{
				defStatus="Error de Conexion al servidor";
				
			}
			break;
			case JsonProblem:
			{
				defStatus="Se ha producido un error al Obtener los datos";
				
			}
			break;
			case JsonEmpty:
			{
				defStatus="No se han Obtenido Datos para tu solicitud";
			}
			break;
			case unknownError:
			{
				defStatus="Ups! ha ocurrido un error";
			}
			break;
			case NoError:
			{
				defStatus="TODO OK";
			}
			break;

		}
		
		
		
		
	}
	
	
	public static String getStatusComms()
	{
		return defStatus;
	}


	public static boolean isHaveConnection(Context c) 
	{
		boolean Status=false;
	    ConnectivityManager cm =(ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) 
	    {
	       Status=true;
	    }
	    return Status;
	}
	

}
