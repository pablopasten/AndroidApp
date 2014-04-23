package cl.pablo.apptest.entitie;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.pablo.apptest.helper.ViewsHelper;




public class EntityFactory 
{
	
	
	// Control de Errores	        
	final static int NoError=0;
	final static int ConnectionError=1;
	final static int JsonProblem=2;
	final static int JsonEmpty=3;
	final static int unknownError=100;
	final static int JsonNotInterpreted=11;
	
	/**********************************************
	 * Metodos Factoria
	 **********************************************/
	
	private final static int FactoryVenues=1;
	
	
	
	public EntityFactory()
	{
		
	}
	
	
	
	public  List<?> getObject(int type,JSONArray jsonArray )
	{
		JSONObject json_data=null;
		List<?> listado=null;
		
		
		switch(type)
		{
			case FactoryVenues:
			{
				List<Venues> lista= new ArrayList<Venues>();
				
				for(int cont=0;cont<jsonArray.length();cont++)
				{
					 try 
			    	  {
							json_data = jsonArray.getJSONObject(cont);
							
							 Venues v= new Venues();
							
							 v.setId(json_data.getString("id"));
							 v.setName(json_data.getString("name"));
							 
							 
							 // parseamos el objecto contact que llega en el json
							 Contact con= new Contact();
							 
							 JSONObject jcontact=jsonArray.getJSONObject(cont).getJSONObject("contact");
							 
							 con.setPhone(jcontact.getString("phone"));
							 con.setFormattedPhone(jcontact.getString("formattedPhone"));
							 //con.setFacebook(jcontact.getString("facebook"));
							 //con.setTwitter(jcontact.getString("twitter"));
							 
							 v.setContc(con);
							 
							 // parseamos el objecto location que llega en el json
							 
							 JSONObject jlocation = jsonArray.getJSONObject(cont).getJSONObject("location");
							 
							 Location lo= new Location();
							 
							 lo.setAddress(jlocation.getString("address"));
							 lo.setCrossStreet(jlocation.getString("crossStreet"));
							 lo.setDistance(jlocation.getInt("distance"));
							 lo.setCountry(jlocation.getString("country"));
							 lo.setCity(jlocation.getString("city"));
							 lo.setLat(jlocation.getDouble("lat"));
							 lo.setLng(jlocation.getDouble("lng"));
							 lo.setState(jlocation.getString("state"));
							 lo.setCc(jlocation.getString("cc"));
							 
							 v.setLoc(lo);
							 lista.add(v);
							
							 ViewsHelper.setStatusComms(NoError); 
							
			    	  } 
			    	  catch (JSONException e) 
			    	  {
							// TODO Auto-generated catch block
			    		  ViewsHelper.setStatusComms(JsonProblem);
							e.printStackTrace();
			    	  }
				}
				
				listado=lista;
			}
			break;
			
			
				
		}
		return listado;
	}
}
