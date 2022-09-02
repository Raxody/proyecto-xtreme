package co.edu.unbosque.xtreme.entity;


public class Reporte {
	
	private String nombre;
	
	private long cedula;
	
	private String total;

	public Reporte(String nombre, long cedula, String total) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.total = total;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getCedula() {
		return cedula;
	}
	
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}



	
}
