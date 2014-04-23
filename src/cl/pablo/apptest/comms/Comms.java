package cl.pablo.apptest.comms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import cl.pablo.apptest.error.ErrorHandler;
import cl.pablo.apptest.helper.JsonArrayHelper;
import android.net.ParseException;
import android.util.Log;


public class Comms 
{
	
	
	// La clase de Comunicación es un Singleton
	
	private static Comms instance;  
	
	// Listado de Manejo de errores
	
	final static int NoError=0;
	final static int ConnectionError=1;
	final static int JsonProblem=2;
	final static int JsonEmpty=3;
	final static int unknownError=100;
	final static int JsonNotInterpreted=11;
	
	
	

	
	// Definicion de Time Out
	
	
	final static int TimeOut=100000;
	
	
	 private Comms()
	{
		
	}
	 
	 
	public static Comms getInstance()
	{
		if(instance==null)
		{
			instance=new Comms();
		}
		
		
		return instance;
	}
	
	
	public JSONArray GetDataFromServer(String Uri)
	{
		// Este metodo entrega un Arreglo Json cuando no sabemos cual es la cabecera inicial del objeto json
		
		JSONArray jsonArray=null;
		
		boolean isHaveConnect=false;
		
		String result = null;
		InputStream is = null;
		StringBuilder sb=null;
	    
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		
		try
		{
			// Para conectarnos a la web verificaremos que exista conexion usando el time out.
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection =TimeOut;
			HttpConnectionParams.setConnectionTimeout(httpParameters,
					timeoutConnection);
			int timeoutSocket =TimeOut ;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			HttpClient httpclient = new DefaultHttpClient(httpParameters);
		    
		    HttpResponse response;
		    
		   
		    HttpGet httpget = new HttpGet(Uri);
			
			
			response = httpclient.execute(httpget);
		    HttpEntity entity = response.getEntity();
		    is = entity.getContent();
		    
		    
		     isHaveConnect=true;
		     ErrorHandler.setLastError(NoError);
	     }
		catch(Exception e)
	     {
	         Log.e("log_tag", "Error in http connection"+e.toString());
	         ErrorHandler.setLastError(ConnectionError);
	     }
		
		
		
		if(isHaveConnect && ErrorHandler.getLastError()==NoError)
		{
					try{
					       BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
					       sb = new StringBuilder();
					       sb.append(reader.readLine() + "\n");
			
					       String line="0";
					       
					       while ((line = reader.readLine()) != null) 
					       {
					                      sb.append(line + "\n");
					                      System.out.println(line + "\n");
					                     
					       }
					       is.close();
					       
					      
					      
					    	result=sb.toString();
					      
					        JSONObject jo= new JSONObject(result);
					        jsonArray =null;
					        JSONObject jo2=jo.getJSONObject("response");
					        jsonArray=jo2.getJSONArray("venues");
					        ErrorHandler.setLastError(NoError);
					        
					        JsonArrayHelper.setJsonHelper(jsonArray);
				        }
					catch(JSONException e1)
					{
						
				    	 
				    	  ErrorHandler.setLastError(JsonEmpty);
				    	  
				     } 
					catch (ParseException e1) 
					{
							ErrorHandler.setLastError(JsonNotInterpreted);
					
					}
					catch(Exception e)
				    {
				              System.out.println("Error converting result "+e.toString());
				              ErrorHandler.setLastError(unknownError);
				              
				              
				    }
					
					
				
		
		}
		
		
		
		return jsonArray;
	}







}
