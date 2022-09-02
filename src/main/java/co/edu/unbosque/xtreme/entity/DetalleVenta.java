package co.edu.unbosque.xtreme.entity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_detalle_venta;

	private Integer cantidad_producto;

	@ManyToOne 
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "codigo_producto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "codigo_venta")
	private Venta venta;

	private Double valor_total;

	private Double valor_venta;

	private Double valor_iva;

	public DetalleVenta() {

	}

	public DetalleVenta(Long codigo_detalle_venta) {
		super();
		this.codigo_detalle_venta = codigo_detalle_venta;
	}

	public DetalleVenta(Integer cantidad_producto, Producto producto, Venta venta, Double valor_total,
			Double valor_venta, Double valor_iva) {
		super();
		this.cantidad_producto = cantidad_producto;
		this.producto = producto;
		this.venta = venta;
		this.valor_total = valor_total;
		this.valor_venta = valor_venta;
		this.valor_iva = valor_iva;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getCodigo_detalle_venta() {
		return codigo_detalle_venta;
	}

	public void setCodigo_detalle_venta(Long codigo_detalle_venta) {
		this.codigo_detalle_venta = codigo_detalle_venta;
	}

	public Integer getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto(Integer cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(Double valor_venta) {
		this.valor_venta = valor_venta;
	}

	public Double getValor_iva() {
		return valor_iva;
	}

	public void setValor_iva(Double valor_iva) {
		this.valor_iva = valor_iva;
	}

}
