package cl.pablo.apptest;

import java.io.IOException;
import java.util.List;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

public class MapView extends Activity
{
	
	private GoogleMap map;
	
	 protected void onCreate(Bundle savedInstanceState) {
	    	
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.mapview);
	        
	        
	        //Dado que la direccion de posicion nos llega, no utilizaremos GPS
	        
	        double Latitud=0.0;
	        double Longitud=0.0;
	        String Direccion="";
	        
	        Bundle extras = getIntent().getExtras(); 
	        String userName;

	        if (extras != null) {
	            Direccion = extras.getString("direccion");
	            Latitud=extras.getDouble("latitud");
	            Longitud=extras.getDouble("longitud");
	            // and get whatever type user account id is
	        }
	       
	        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	            .getMap();
	        map.setMyLocationEnabled(true);
	   	    
		    LatLng MyPosition = new LatLng(Latitud,Longitud);
			       
			Geocoder geoCoder = new Geocoder(this);

			List<Address> matches;
				try 
				{
					matches = geoCoder.getFromLocation(Latitud, Longitud, 1);
					 Address bestMatch = (matches.isEmpty() ? null : matches.get(0));
					 
					 Marker kiel = map.addMarker(new MarkerOptions()
				        .position(MyPosition)
				        .title("Place")
				        .snippet(Direccion)
				        .icon(BitmapDescriptorFactory
				            .fromResource(R.drawable.ic_launcher)));
					 
					// Toast.makeText(this, bestMatch.getFeatureName().toString()+" "+bestMatch.getAddressLine(0), Toast.LENGTH_LONG).show();
					
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        

		        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MyPosition, 15));

		        // Zoom in, animating the camera.	
		        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
			

	        }
	    

}
