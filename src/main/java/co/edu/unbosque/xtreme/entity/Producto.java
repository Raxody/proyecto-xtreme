package co.edu.unbosque.xtreme.entity;

import javax.persistence.*;

@Entity
public class Producto {
	
	@Id
	private Long codigo_producto;
	
	private String nombre_producto;
	
	@ManyToOne
	@JoinColumn(name = "nit_proveedor")
	private Proveedor proveedor;

	
	private Double precio_compra;
	
	private Double iva_compra;
	
	private Double precio_venta;

	public Producto() {
		
	}
	
	



	public Producto(Long codigo_producto) {
		super();
		this.codigo_producto = codigo_producto;
	}





	public Producto(Long codigo_producto, String nombre_producto, Proveedor proveedor, Double precio_compra,
			Double iva_compra, Double precio_venta) {
		super();
		this.codigo_producto = codigo_producto;
		this.nombre_producto = nombre_producto;
	    this.proveedor = proveedor;
		this.precio_compra = precio_compra;
		this.iva_compra = iva_compra;
		this.precio_venta = precio_venta;
	}





	public Long getCodigo_producto() {
		return codigo_producto;
	}





	public void setCodigo_producto(Long codigo_producto) {
		this.codigo_producto = codigo_producto;
	}





	public String getNombre_producto() {
		return nombre_producto;
	}





	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}





	public Proveedor getProveedor() {
		return proveedor;
	}





	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}





	public Double getPrecio_compra() {
		return precio_compra;
	}





	public void setPrecio_compra(Double precio_compra) {
		this.precio_compra = precio_compra;
	}





	public Double getIva_compra() {
		return iva_compra;
	}





	public void setIva_compra(Double iva_compra) {
		this.iva_compra = iva_compra;
	}





	public Double getPrecio_venta() {
		return precio_venta;
	}





	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
	}





	
}
