package co.edu.unbosque.xtreme.entity;

import javax.persistence.*;

@Entity
public class Cliente {
	@Id
	private Long cedulaCliente;
	private String direccionCliente;
	private String emailCliente;
	private String nombreCliente;
	private String telefonoCliente;
	

	public Cliente(Long cedulaCliente, String direccionCliente, String emailCliente, String nombreCliente,
			String telefonoCliente) {
		super();
		this.cedulaCliente = cedulaCliente;
		this.direccionCliente = direccionCliente;
		this.emailCliente = emailCliente;
		this.nombreCliente = nombreCliente;
		this.telefonoCliente = telefonoCliente;
	}
	
	




	public Cliente() {
		super();
	}
	
	
	
	

	public Cliente(Long cedulaCliente) {
		super();
		this.cedulaCliente = cedulaCliente;
	}



	public Long getCedulaCliente() {
		return cedulaCliente;
	}



	public void setCedulaCliente(Long cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}



	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	
	
	
	
	

}
