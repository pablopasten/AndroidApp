package cl.pablo.apptest.views;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cl.pablo.apptest.R;
import cl.pablo.apptest.entitie.Venues;
import android.content.Context;
import android.widget.SimpleAdapter;

public class ViewMain {
	
	Context c;
	List<Venues> listado;
	
	public ViewMain(Context con)
	{
		this.c=con;
	}
	
	
	public SimpleAdapter toListView(List<Venues> l)
	{
		listado=l;

		
		SimpleAdapter sa=null;
		
		 List<HashMap<String, Object>> Values = new ArrayList<HashMap<String, Object>>();
     	 HashMap<String,Object> value;
     	 int [] controles = new int []{R.id.txtNombre,R.id.txtDireccion};
     	 String [] keys = new String [] {"sKeyNombre","sKeyDireccion"};
	 
	 
     	 	
     	 	
		for(int cont=0; cont<l.size();cont++)
		{
			value = new HashMap<String,Object>();
			
			
			
			value.put("sKeyNombre",l.get(cont).getName());
			
			
			value.put("sKeyDireccion",l.get(cont).getLoc().getAddress());
	
			Values.add(value);
		}
		
		 sa=new SimpleAdapter(c, Values, R.layout.controleslistado, keys, controles);
		  
		  return sa;
	}
	
	
	public Venues getVenue(int Position)
	{
		return listado.get(Position);
	}

}
