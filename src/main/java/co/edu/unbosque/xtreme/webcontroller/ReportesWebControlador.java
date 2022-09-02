package co.edu.unbosque.xtreme.webcontroller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.xtreme.controller.ClienteController;
import co.edu.unbosque.xtreme.controller.UsuarioController;
import co.edu.unbosque.xtreme.controller.VentaController;
import co.edu.unbosque.xtreme.entity.Cliente;
import co.edu.unbosque.xtreme.entity.Reporte;
import co.edu.unbosque.xtreme.entity.Usuario;
import co.edu.unbosque.xtreme.entity.Venta;

@Controller
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
public class ReportesWebControlador {
	
	@Autowired
	private LoginWebControlador loginWebControlador;
	
	@Autowired
	private  UsuarioController usuarioController;
	
	@Autowired
	private ClienteController clienteController;

	
	@Autowired
	private VentaController ventaController;

	private Long sumaTotal;
			
	@GetMapping ("/reportes")
	public String mostrar(Model model) {
		if(loginWebControlador.getUsuario() != null) {
			
			
			model.addAttribute("usuario2", loginWebControlador.getUsuario());	
			return "reportes.html";
			
			}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "reportes", method = RequestMethod.POST, params = "listaUsuarios")
	public String listaUsuarios(RedirectAttributes redirAttrs){
		
		List <Usuario> usuarios = usuarioController.getUsuarios();
		
		redirAttrs.addFlashAttribute("Usuarios", usuarios);
		return "redirect:/reportes";
		
	}
	
	@RequestMapping(value = "reportes", method = RequestMethod.POST, params = "listaClientes")
	public String listaClientes(RedirectAttributes redirAttrs){
		
		List <Cliente> clientes = clienteController.getClientes();
		
		redirAttrs.addFlashAttribute("Clientes", clientes);
		return "redirect:/reportes";
		
	}
	
	@RequestMapping(value = "reportes", method = RequestMethod.POST, params = "VentasClientes")
	public String VentasClientes(RedirectAttributes redirAttrs){
		DecimalFormat miles = new DecimalFormat("$###,###,###.##");
				
		List <Cliente> clientes = clienteController.getClientes();
		
		List <Venta> ventas = ventaController.getVentas();
	
		
		List<Reporte> reportes = new ArrayList <Reporte>();
	
		

		
	for (int i= 0; i<clientes.size();i++) {
		Double suma =  0.0 ;
			
			for (int j= 0; j<ventas.size();j++) {
				
			if(clientes.get(i).equals(ventas.get(j).getCliente())) {
				
				suma += ventas.get(j).getTotal_venta();
				
				
				
			}	
			}
			
		
			reportes.add(new Reporte(clientes.get(i).getNombreCliente(),clientes.get(i).getCedulaCliente(),String.valueOf( miles.format(suma))));
			
	
		
			
		}
		
		Double sumaTotal = 0.0;
		for(int i= 0; i< ventas.size();i++) {
			
			
			sumaTotal += ventas.get(i).getTotal_venta() ;
			
			
			
			
		}
			
		System.out.println(sumaTotal);
		redirAttrs.addFlashAttribute("listaVenta", reportes);
		
		redirAttrs.addFlashAttribute("sumaTotal", String.valueOf(miles.format(sumaTotal)));
		
		
		return "redirect:/reportes";
		
	}

}
