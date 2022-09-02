package co.edu.unbosque.xtreme.entity;

import javax.persistence.*;

@Entity
public class Proveedor {
	@Id
	private Long nitProveedor;
	private String ciudadProveedor;
	private String direccionProveedor;
	private String nombreProveedor;
	private String telefonoProveedor;
	

	public Proveedor(Long nitProveedor, String ciudadProveedor, String direccionProveedor, String nombreProveedor,
			String telefonoProveedor) {
		super();
		this.nitProveedor = nitProveedor;
		this.ciudadProveedor = ciudadProveedor;
		this.direccionProveedor = direccionProveedor;
		this.nombreProveedor = nombreProveedor;
		this.telefonoProveedor = telefonoProveedor;
	}

	public Proveedor() {
		super();
	}
	

	

	public Proveedor(Long nitProveedor) {
		super();
		this.nitProveedor = nitProveedor;
	}

	public Long getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(Long nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public String getCiudadProveedor() {
		return ciudadProveedor;
	}

	public void setCiudadProveedor(String ciudadProveedor) {
		this.ciudadProveedor = ciudadProveedor;
	}

	public String getDireccionProveedor() {
		return direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	@Override
	public String toString() {
		return "Proveedor [nitProveedor=" + nitProveedor + ", ciudadProveedor=" + ciudadProveedor
				+ ", direccionProveedor=" + direccionProveedor + ", nombreProveedor=" + nombreProveedor
				+ ", telefonoProveedor=" + telefonoProveedor + "]";
	}
	
	
}
