package cl.pablo.apptest.comms;

import java.util.List;

import cl.pablo.apptest.MainActivity;
import cl.pablo.apptest.entitie.EntityFactory;
import cl.pablo.apptest.entitie.Venues;
import cl.pablo.apptest.error.ErrorHandler;
import cl.pablo.apptest.helper.JsonArrayHelper;
import cl.pablo.apptest.helper.ViewsHelper;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class ComunicacionAsincrona extends AsyncTask
{
	
	// Parametros de Procesamiento
		private String Uri;
		int Llamada;
		int Task;
	// Metodo de Conexion a Datos
		final static int MethodPost=1;
		
		
		// Elementos de Ayuda desde la Vista
		ProgressDialog pd;
		
		Context c;
		
	
	private List<Venues> listVenues;

	//Que es lo que voy a hacer?
	
		
	
	// Control de Errores	        
	final static int NoError=0;
	final static int ConnectionError=1;
	final static int JsonProblem=2;
	final static int JsonEmpty=3;
	final static int unknownError=100;
	final static int JsonNotInterpreted=11;
	
	
	//metodo factory
	
	private final static int FactoryVenues=1;
	
	public ComunicacionAsincrona(String Url, ProgressDialog p,Context con)
	{
		this.Uri=Url;
		System.out.println("La url en el thread:"+Url);
		
	
		this.c=con;
		
		this.pd=p;
	
	}
	
	@Override
	protected Object doInBackground(Object... arg0) 
	{
		// TODO Auto-generated method stub
		EntityFactory ef= new EntityFactory();
		Comms cm= Comms.getInstance();
		  if(ViewsHelper.isHaveConnection(this.c))
		  {
			  JsonArrayHelper.setJsonHelper(cm.GetDataFromServer(this.Uri));
			  if(ErrorHandler.getLastError()==NoError)
			  {
				 
				  
				  		listVenues=(List<Venues>)ef.getObject(FactoryVenues,JsonArrayHelper.getJsonHelper());
				 
				  
			  }
		  }
		  else
		  {
			  ErrorHandler.setLastError(5);
		  }
		
		return null;
	}

	@Override
	protected void onPostExecute(Object result) 
	{
		// TODO Auto-generated method stub
		
		super.onPostExecute(result);
		if(ErrorHandler.getLastError()==NoError)
		{
			pd.dismiss();
			MainActivity.CallMeAfeterComms(listVenues, 0);
		}
		else
		{
			MainActivity.CallMeAfeterComms(null, 1);
		}
		
		
	}
	

	@Override
	protected void onPreExecute() 
	{
		// TODO Auto-generated method stub
		super.onPreExecute();

	    pd.setCancelable(false);
		pd.setIndeterminate(true);
			
        pd.show();
        	
        
	}
	
	
	

}
