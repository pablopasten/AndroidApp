package cl.pablo.apptest;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import cl.pablo.apptest.comms.ComunicacionAsincrona;
import cl.pablo.apptest.entitie.Venues;
import cl.pablo.apptest.views.ViewMain;

public class MainActivity extends Activity {
	
	
	/*
	 *  Para que la aplicacion se vea bien en todos los tamaños de pantalla
	 *  utilizaré linear layout anidados en un unico layout, que a diferencia de
	 *  Relative layout, ya que el primero usa posiciones estrictas en la pantalla
	 */
	
	ProgressDialog pd;
	static ListView lv;
	static Context c;
	static ViewMain vm;
	// Sabemos que la url esta predefinida asi que la usaremos de manera fija.
	private static final String Url="https://api.foursquare.com/v2/venues/search?client_id=NERLG4IFIOWZLN1ZFDWJLARWKYD3TYEBJ4UERR5WDGQWILJW&client_secret=PGVTJJZ5UEDOK3G1MTFAKTUP32ZA5JD1FQELVE12XT3TVG5U&v=20130815&ll=40.7,-74&query=beer";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.c=this;
		vm= new ViewMain(c);
		lv=(ListView) findViewById(R.id.lvVenues);
		
		this.lv.setOnItemClickListener(new OnItemClickListener()
        {

            

            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {

            	Venues vn= vm.getVenue(position);
            		
           		
            	Intent a= new Intent(MainActivity.this, MapView.class);
            	
            	
            	a.putExtra("latitud",vn.getLoc().getLat());
            	a.putExtra("longitud", vn.getLoc().getLng());
            	a.putExtra("direccion", vn.getLoc().getAddress());
            	
            	startActivity(a);
            	
            	
            }
         });
		
		pd= new ProgressDialog(this);
		
		pd.setMessage("Espera unos segundos estamos buscando la info");
		ComunicacionAsincrona ca= new ComunicacionAsincrona(Url, pd,this);
		ca.execute("");
		
	}

	public static void CallMeAfeterComms(List<Venues> listado, int estado)
	{
		if(estado==0)
		{
			
			
			lv.setAdapter(vm.toListView(listado));
		}
		else
		{
			//ha ocurrido un error
			Toast toast = Toast.makeText(c,"Ocurrio un error y no pudimos obtener datos", Toast.LENGTH_LONG);
	 		toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
	 		toast.show();
		}
	}

}
