package cl.pablo.apptest.entitie;

public class Venues 
{	
	
	/* Dado que el json de llegada es bastante grande y no usaremos todos los datos
	 * vamos a parsear solo los que necesitamos en nuestra app
	 */
	private String id;
	private String name;
	private Contact contc;
	private Location loc;
	
	public Venues()
	{
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContc() {
		return contc;
	}

	public void setContc(Contact contc) {
		this.contc = contc;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}
	
	

}
