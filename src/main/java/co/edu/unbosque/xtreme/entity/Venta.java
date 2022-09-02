package co.edu.unbosque.xtreme.entity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Venta {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long codigo_venta;

	@ManyToOne 
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cedula_cliente")
	private Cliente cliente;

	@ManyToOne 
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cedula_usuario")
	private Usuario usuario;

	private Double iva_venta;

	private Double total_venta;

	private Double valor_venta;

	public Venta() {

	}	
	
     

	public Venta(Long codigo_venta) {
		super();
		this.codigo_venta = codigo_venta;
	}



	public Venta(Cliente cliente, Usuario usuario, Double iva_venta, Double total_venta, Double valor_venta) {
		super();
		this.cliente = new Cliente(cliente.getCedulaCliente());
		this.usuario = usuario;
		this.iva_venta = iva_venta;
		this.total_venta = total_venta;
		this.valor_venta = valor_venta;
	}

	public Long getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(Long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getIva_venta() {
		return iva_venta;
	}

	public void setIva_venta(Double iva_venta) {
		this.iva_venta = iva_venta;
	}

	public Double getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(Double total_venta) {
		this.total_venta = total_venta;
	}

	public Double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(Double valor_venta) {
		this.valor_venta = valor_venta;
	}

}
