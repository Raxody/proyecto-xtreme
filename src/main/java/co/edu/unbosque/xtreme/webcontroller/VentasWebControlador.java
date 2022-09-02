package co.edu.unbosque.xtreme.webcontroller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.xtreme.controller.ClienteController;
import co.edu.unbosque.xtreme.controller.DetalleVentaController;
import co.edu.unbosque.xtreme.controller.ProductoController;
import co.edu.unbosque.xtreme.controller.UsuarioController;
import co.edu.unbosque.xtreme.controller.VentaController;
import co.edu.unbosque.xtreme.entity.Cliente;
import co.edu.unbosque.xtreme.entity.DetalleVenta;
import co.edu.unbosque.xtreme.entity.Producto;
import co.edu.unbosque.xtreme.entity.Usuario;
import co.edu.unbosque.xtreme.entity.Venta;

@Controller
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
public class VentasWebControlador {

	@Autowired
	LoginWebControlador loginWebControlador;

	@Autowired
	UsuarioController usuarioController;

	@Autowired
	VentaController ventaController;

	@Autowired
	ClienteController clienteController;

	@Autowired
	ProductoController productoController;

	@Autowired
	DetalleVentaController detalleVentaController;

	static Cliente clientePrincipal = new Cliente();
	Usuario usuario = null;
	Producto producto1;

	long totalVenta;
	long totalIva;
	long totalFinal;

	static List<Producto> productosCompra = new ArrayList<Producto>();

	static List<Integer> cantidadProductos = new ArrayList<Integer>();
	static List<Integer> listaIva = new ArrayList<Integer>();
	static List<Integer> listaTotal = new ArrayList<Integer>();

	@GetMapping("/ventas")
	public String mostrar(Model model) {
		if (loginWebControlador.getUsuario() != null) {
			model.addAttribute("usuario2", loginWebControlador.getUsuario());
			usuario = loginWebControlador.getUsuario();
			return "ventas.html";

		}

		return "redirect:/login";

	}

	public void mostrarClienteFactura(RedirectAttributes redirAttrs) {

		List<Venta> ventas = ventaController.getVentas();
		System.out.println( );
		if (ventas.isEmpty()) {
			redirAttrs.addFlashAttribute("codigoVenta",1);
			}else {
			redirAttrs.addFlashAttribute("codigoVenta", (ventas.get(ventas.size()-1).getCodigo_venta()+1));
			}
		

		redirAttrs.addFlashAttribute("cedulaCliente", clientePrincipal.getCedulaCliente());
		redirAttrs.addFlashAttribute("nombreCliente", clientePrincipal.getNombreCliente());

		if (productosCompra.size() != 0) {
			 listas(redirAttrs);
		}
	}

	@RequestMapping(value = "ventas", method = RequestMethod.POST, params = "consultarCedula")
	public String ConsultarCedula(Cliente cliente, RedirectAttributes redirAttrs) {

		List<Cliente> clientes = clienteController.getClientes();

		for (int i = 0; i < clientes.size(); i++) {

			if (clientes.get(i).getCedulaCliente().equals(cliente.getCedulaCliente())) {

				clientePrincipal.setNombreCliente(clientes.get(i).getNombreCliente());
				clientePrincipal.setCedulaCliente(cliente.getCedulaCliente());
				redirAttrs.addFlashAttribute("cedulaCliente", clientes.get(i).getCedulaCliente());
				redirAttrs.addFlashAttribute("nombreCliente", clientes.get(i).getNombreCliente());

				List<Venta> ventas = ventaController.getVentas();
				
				if (ventas.isEmpty()) {
					redirAttrs.addFlashAttribute("codigoVenta",1);
					return "redirect:/ventas";
					
				}
				redirAttrs.addFlashAttribute("codigoVenta",(ventas.get(ventas.size()-1).getCodigo_venta()+1));

				return "redirect:/ventas";

			}

		}

		return "redirect:/ventas";

	}
	
	public String listas(RedirectAttributes redirAttrs) {
		redirAttrs.addFlashAttribute("productosRegistrados", productosCompra);
		redirAttrs.addFlashAttribute("cantidadproductosRegistrados", cantidadProductos);
		redirAttrs.addFlashAttribute("ivaRegistrados", listaIva );
		redirAttrs.addFlashAttribute("totalRegistrados", listaTotal);
		return "redirect:/ventas";
	}
	
	@RequestMapping(value = "ventas", method = RequestMethod.POST, params = "CancelarEspecifico")
	public String EliminarEspecifico(Producto producto, RedirectAttributes redirAttrs) {
		System.out.println(productosCompra.size());
		for(int i = 0; i <cantidadProductos.size();i++ ) {
			if(productosCompra.get(i).getCodigo_producto().equals(producto.getCodigo_producto())) {
				productosCompra.remove(i);
				cantidadProductos.remove(i);
				listaIva.remove(i);
				listaTotal.remove(i);
			    listas(redirAttrs);
				llenarInput(redirAttrs);
				
				return "redirect:/ventas";
			}
		}
		
		redirAttrs.addFlashAttribute("mensaje", "El producto no existe en la factura");
		llenarInput(redirAttrs);
		listas(redirAttrs);
		return "redirect:/ventas";

	}
			
	@RequestMapping(value = "ventas", method = RequestMethod.POST, params = "consultarProducto1")
	public String ConsultarProducto1(Producto producto, RedirectAttributes redirAttrs) {

		List<Producto> productos = productoController.listarProductos();
		boolean validador = false;
		for (int i = 0; i < productos.size(); i++) {

			if (productos.get(i).getCodigo_producto().equals(producto.getCodigo_producto())) {

				redirAttrs.addFlashAttribute("codigoProducto", productos.get(i).getCodigo_producto());
				redirAttrs.addFlashAttribute("nombreProducto", productos.get(i).getNombre_producto());

				mostrarClienteFactura(redirAttrs);

				validador = true;

			}
		}
		llenarInput(redirAttrs);
		listas(redirAttrs);
		mostrarClienteFactura(redirAttrs);
		if (!validador) {
			redirAttrs.addFlashAttribute("mensaje", "Producto no existe");		
		}

		return "redirect:/ventas";

	}

	@RequestMapping(value = "ventas", method = RequestMethod.POST, params = "noBorrar")

	public String opcion(RedirectAttributes redirAttrs) {
		listas(redirAttrs);
		mostrarClienteFactura(redirAttrs);
		return "redirect:/ventas";
	}

	@RequestMapping(value = "ventas", method = RequestMethod.POST, params = "siBorrar")
	public String CancelarTodo() {
		cantidadProductos.clear();
		productosCompra.clear();
		listaIva.clear();
		listaTotal.clear();		
		clientePrincipal.setCedulaCliente(null);
		clientePrincipal.setNombreCliente("");
		
		return "redirect:/ventas";

	}

	public void llenarInput(RedirectAttributes redirAttrs) {
		DecimalFormat miles = new DecimalFormat("$###,###,###.##");
		
		Double acumuladorIVA = 0.0;
		Double acumuladorTotal = 0.0;
		Double acumuladorSubTotal = 0.0;
		Double acumuladorTotalProducto = 0.0;

		for (int i = 0; i < cantidadProductos.size(); i++) {
			acumuladorSubTotal += cantidadProductos.get(i) * productosCompra.get(i).getPrecio_compra();
			acumuladorTotalProducto = cantidadProductos.get(i) * productosCompra.get(i).getPrecio_compra();

		}

		acumuladorIVA = (acumuladorSubTotal * 19) / 100;
		acumuladorTotal = acumuladorIVA + acumuladorSubTotal;

		redirAttrs.addFlashAttribute("valorTotal1", String.valueOf(miles.format(acumuladorTotalProducto)));

		redirAttrs.addFlashAttribute("total_venta", String.valueOf(miles.format(acumuladorSubTotal)));
		redirAttrs.addFlashAttribute("totalIva", String.valueOf(miles.format(acumuladorIVA)));
		redirAttrs.addFlashAttribute("totalFinal", String.valueOf(miles.format(acumuladorTotal)));

	}

	@RequestMapping(value = "ventas", method = RequestMethod.POST, params = "calcularVenta")
	public String calcularVenta(Producto producto,
			@RequestParam(value = "cantidad_producto1", defaultValue = "0") Integer cantidad,

			RedirectAttributes redirAttrs) {

		mostrarClienteFactura(redirAttrs);

		if (producto.getCodigo_producto() == null) {

			redirAttrs.addFlashAttribute("mensaje", "Ingrese un producto");

		} else if (cantidad == 0) {

			redirAttrs.addFlashAttribute("mensaje", "Ingrese una cantidad");

		} else if (cantidad < 0) {

			redirAttrs.addFlashAttribute("mensaje", "No se aceptan valores negativos");

		} else {
			List<Producto> productos = productoController.listarProductos();
			boolean validador = false;
			for (int i = 0; i < productos.size(); i++) {

				if (productos.get(i).getCodigo_producto().equals(producto.getCodigo_producto())) {
					producto1 = new Producto(productos.get(i).getCodigo_producto(),
							productos.get(i).getNombre_producto(), productos.get(i).getProveedor(),
							productos.get(i).getPrecio_compra(), productos.get(i).getIva_compra(),
							productos.get(i).getPrecio_venta());
					for (int j = 0; j < productosCompra.size(); j++) {
						if (productosCompra.get(j).getCodigo_producto().equals(producto1.getCodigo_producto())) {
							validador = true;
							redirAttrs.addFlashAttribute("mensaje",
									"Ya agrego este producto, puede eliminarlo para volverlo a agregar");
						}
					}
					
					if (validador == false) {
						productosCompra.add(producto1);
						cantidadProductos.add(cantidad);	
						Double iva = producto1.getIva_compra();
						listaIva.add(iva.intValue() * cantidad);
						Double total = producto1.getPrecio_venta();
						listaTotal.add(total.intValue() * cantidad );
					}

					listas(redirAttrs);

				}
			}

		}

		llenarInput(redirAttrs);
		return "redirect:/ventas";
	}

	@RequestMapping(value = "ventas", method = RequestMethod.POST, params = "confirmarVenta")
	public String confirmarVenta(RedirectAttributes redirAttrs) {

		if (clientePrincipal == null || usuario == null) {
			llenarInput(redirAttrs);
			mostrarClienteFactura(redirAttrs);
			redirAttrs.addFlashAttribute("mensaje", "Agrege el cliente");

		} else if (cantidadProductos.size() == 0) {
			mostrarClienteFactura(redirAttrs);
			redirAttrs.addFlashAttribute("mensaje", "Agrege productos");

		} else {
			long acumuladorIVA = 0;
			long acumuladorTotal = 0;
			long acumuladorSubTotal = 0;

			List<Double> acumuladorIVAProducto = new ArrayList<Double>();
			List<Double> acumuladorTotalProducto = new ArrayList<Double>();
			List<Double> acumuladorSubTotalProducto = new ArrayList<Double>();

			for (int i = 0; i < cantidadProductos.size(); i++) {
				acumuladorSubTotal += cantidadProductos.get(i) * productosCompra.get(i).getPrecio_compra();

				acumuladorSubTotalProducto.add(cantidadProductos.get(i) * productosCompra.get(i).getPrecio_compra());

				acumuladorIVAProducto.add(acumuladorSubTotalProducto.get(i) * 0.19);

				acumuladorTotalProducto.add(acumuladorSubTotalProducto.get(i) + acumuladorIVAProducto.get(i));
			}

			acumuladorIVA = (acumuladorSubTotal * 19) / 100;
			acumuladorTotal = acumuladorIVA + acumuladorSubTotal;

			List<Usuario> usuarios = usuarioController.getUsuarios();

			for (int i = 0; i < usuarios.size(); i++) {

				if (loginWebControlador.getUsuario().getUsr().equals(usuarios.get(i).getUsr())) {

					usuario = usuarios.get(i);
				}

			}

			Venta venta = new Venta(clientePrincipal, usuario, (double) acumuladorIVA, (double) acumuladorSubTotal,
					(double) acumuladorTotal);
			ventaController.crear(venta);

			for (int i = 0; i < cantidadProductos.size(); i++) {
				acumuladorSubTotal += cantidadProductos.get(i) * productosCompra.get(i).getPrecio_compra();

				detalleVentaController.guardarDetalleVenta(new DetalleVenta(cantidadProductos.get(i),
						productosCompra.get(i), venta, acumuladorTotalProducto.get(i),
						acumuladorSubTotalProducto.get(i), acumuladorIVAProducto.get(i)));

			}

			List<Venta> ventas = ventaController.getVentas();

			redirAttrs.addFlashAttribute("mensajeExito", "Venta Creada No. " + (ventas.get(ventas.size()-1).getCodigo_venta()));

			CancelarTodo();

		}

		return "redirect:/ventas";
	}

}
