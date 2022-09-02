package co.edu.unbosque.xtreme.webcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.xtreme.controller.ClienteController;
import co.edu.unbosque.xtreme.entity.Cliente;

@Controller
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
public class ClientesWebControlador {
	
	
	@Autowired
	LoginWebControlador loginWebControlador;
	
	@Autowired
	private ClienteController clienteController;

	
	

	@GetMapping("/clientes")
	public String mostrar(Model model) {		
		if(loginWebControlador.getUsuario() != null) {			
			
			model.addAttribute("usuario2", loginWebControlador.getUsuario());	
			return "clientes.html";
			
			}			
			return "redirect:/login";
		
		
	}

	@RequestMapping(value = "clientes", method = RequestMethod.POST, params = "consultar")

	public String Consultar(Cliente cliente, Model model, RedirectAttributes redirAttrs) {

		List<Cliente> clientes = clienteController.getClientes();

		boolean validador = false;
		for (int i = 0; i < clientes.size(); i++) {

			if (clientes.get(i).getCedulaCliente().equals(cliente.getCedulaCliente()) ) {
				datosEncontrados(clientes, redirAttrs, i);
				return "redirect:/clientes";
			}

		}

		if (validador == false) {
			redirAttrs.addFlashAttribute("mensaje", "Usuario no existe");
		}

		return "redirect:/clientes";

	}

	public void datosEncontrados(List<Cliente> clientes, RedirectAttributes redirAttrs, int i) {
		redirAttrs.addFlashAttribute("cedulaCliente", clientes.get(i).getCedulaCliente());
		redirAttrs.addFlashAttribute("cedulaCliente2", clientes.get(i).getCedulaCliente());
		redirAttrs.addFlashAttribute("nombreCliente", clientes.get(i).getNombreCliente());
		redirAttrs.addFlashAttribute("direccionCliente", clientes.get(i).getDireccionCliente());
		redirAttrs.addFlashAttribute("emailCliente", clientes.get(i).getEmailCliente());
		redirAttrs.addFlashAttribute("telefonoCliente", clientes.get(i).getTelefonoCliente());
	
	}

	@RequestMapping(value = "clientes", method = RequestMethod.POST, params = "noBorrar")

	public String opcion() {

		return "redirect:/clientes";
	}

	@RequestMapping(value = "clientes", method = RequestMethod.POST, params = "siBorrar")

	public String borrar(Cliente cliente, Model model, RedirectAttributes redirAttrs) {
		if (cliente.getCedulaCliente() == null) {
			redirAttrs.addFlashAttribute("mensajeBorrar", "Cliente no encontrado");
		} else {
			List<Cliente> clientes = clienteController.getClientes();
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getCedulaCliente().equals(cliente.getCedulaCliente())) {

						clienteController.eliminar(clientes.get(i).getCedulaCliente());
						redirAttrs.addFlashAttribute("mensajeBorrar2", "Usuario eliminado");
						return "redirect:/clientes";
					}
				}
			redirAttrs.addFlashAttribute("mensajeBorrar", "cliente no encontrado");
			}
		return "redirect:/clientes";
	}

	@RequestMapping(value = "clientes", method = RequestMethod.POST, params = "Limpiar")

	public String vaciar() {

		return "redirect:/clientes";

	}

	public void datos(Cliente cliente, RedirectAttributes redirAttrs) {
		redirAttrs.addFlashAttribute("cedulaCliente", cliente.getCedulaCliente());
		redirAttrs.addFlashAttribute("direccionCliente", cliente.getDireccionCliente());
		redirAttrs.addFlashAttribute("emailCliente", cliente.getEmailCliente());
		redirAttrs.addFlashAttribute("nombreCliente", cliente.getNombreCliente());
		redirAttrs.addFlashAttribute("telefonoCliente", cliente.getTelefonoCliente());
	}

	@RequestMapping(value = "clientes", method = RequestMethod.POST, params = "actualizar")

	public String Actualizar(Cliente cliente, Model model, RedirectAttributes redirAttrs) {
		if (cliente.getCedulaCliente() == null || cliente.getDireccionCliente().equals("") || cliente.getEmailCliente().equals("")
				|| cliente.getNombreCliente().equals("") || cliente.getTelefonoCliente().equals("")) {
			redirAttrs.addFlashAttribute("mensajeActualizar3", "Faltaron datos, completelos. ");
			datos(cliente, redirAttrs);

		} else {
			List<Cliente> clientes = clienteController.getClientes();

			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getCedulaCliente().equals(cliente.getCedulaCliente())) {
					clienteController.actualizar(cliente);
					redirAttrs.addFlashAttribute("cedulaCliente2", cliente.getCedulaCliente());
					
					datosEncontrados(clientes, redirAttrs, i);
					redirAttrs.addFlashAttribute("mensajeActualizar", "Usuario Actualizado");
					return "redirect:/clientes";
				}
			}

			redirAttrs.addFlashAttribute("mensajeActualizar2", "Usuario no encontrado");
		}

		return "redirect:/clientes";

	}

	@RequestMapping(value = "clientes", method = RequestMethod.POST, params = "crear")

	public String Crear(Cliente cliente, Model model, RedirectAttributes redirAttrs) {

		if (cliente.getCedulaCliente() == null || cliente.getDireccionCliente().equals("") || cliente.getEmailCliente().equals("")
				|| cliente.getNombreCliente().equals("") || cliente.getTelefonoCliente().equals("")) {
			redirAttrs.addFlashAttribute("mensajeCrear3", "Faltaron datos, completelos. ");
			datos(cliente, redirAttrs);

		}else {
			List<Cliente> usuarios = clienteController.getClientes();
			boolean validador = false;
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getCedulaCliente().equals(cliente.getCedulaCliente())) {
					validador = true;
				}
			}

			if (validador == false) {

				clienteController.crear(cliente);
				redirAttrs.addFlashAttribute("mensajeCrear", "Usuario creado");
				redirAttrs.addFlashAttribute("cedulaCliente2", cliente.getCedulaCliente());
				datos(cliente, redirAttrs);
			} else {
				redirAttrs.addFlashAttribute("mensajeCrear2", "Esta cedula ya existe");
			}
		}

		return "redirect:/clientes";

	}

}
