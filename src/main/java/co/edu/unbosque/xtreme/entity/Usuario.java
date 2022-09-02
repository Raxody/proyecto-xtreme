package co.edu.unbosque.xtreme.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id	
	private Long cedulaUsuario;
	private String emailUsuario; 	
	private String nombreUsuario;
	private String passwordUsuario;
	private String usr;
	
	public Usuario() {
		
	}
	

	public Usuario(Long cedulaUsuario) {
		super();
		this.cedulaUsuario = cedulaUsuario;
	}


	public Usuario(Long cedulaUsuario, String emailUsuario, String nombreUsuario, String passwordUsuario, String usr) {
		super();
		this.cedulaUsuario = cedulaUsuario;
		this.emailUsuario = emailUsuario;
		this.nombreUsuario = nombreUsuario;
		this.passwordUsuario = passwordUsuario;
		this.usr = usr;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public Long getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(Long cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}




	
	
	
}