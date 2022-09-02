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
import co.edu.unbosque.xtreme.controller.ProveedorController;
import co.edu.unbosque.xtreme.entity.Proveedor;

@Controller
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
public class ProveedoresWebControlador {
	
	@Autowired
	private ProveedorController proveedorController;
	
	@Autowired
	LoginWebControlador loginWebControlador;
	
	

	@GetMapping("/proveedores")
	public String mostrar(Model model) {
		
		if(loginWebControlador.getUsuario() != null) {
			
			
			model.addAttribute("usuario2", loginWebControlador.getUsuario());	
			return "proveedores.html";			
			}
			
			return "redirect:/login";
		
	}

	@RequestMapping(value = "proveedores", method = RequestMethod.POST, params = "consultarP")

	public String Consultar(Proveedor proveedor, Model model, RedirectAttributes redirAttrs) {

		List<Proveedor> proveedores = proveedorController.getProveedores();

		boolean validador = false;
		for (int i = 0; i < proveedores.size(); i++) {

			if (proveedores.get(i).getNitProveedor().equals(proveedor.getNitProveedor()) ) {
				datosEncontrados(proveedores, redirAttrs, i);
				return "redirect:/proveedores";
			}

		}

		if (validador == false) {
			redirAttrs.addFlashAttribute("mensaje", "Proveedor no existe");
		}

		return "redirect:/proveedores";

	}

	public void datosEncontrados(List<Proveedor> proveedor, RedirectAttributes redirAttrs, int i) {
		redirAttrs.addFlashAttribute("nitProveedor", proveedor.get(i).getNitProveedor());
		redirAttrs.addFlashAttribute("nitProveedor2", proveedor.get(i).getNitProveedor());
		redirAttrs.addFlashAttribute("nombreProveedor", proveedor.get(i).getNombreProveedor());
		redirAttrs.addFlashAttribute("direccionProveedor", proveedor.get(i).getDireccionProveedor());
		redirAttrs.addFlashAttribute("ciudadProveedor", proveedor.get(i).getCiudadProveedor());
		redirAttrs.addFlashAttribute("telefonoProveedor", proveedor.get(i).getTelefonoProveedor());
	
	}

	@RequestMapping(value = "proveedores", method = RequestMethod.POST, params = "noBorrarP")

	public String opcion() {

		return "redirect:/proveedores";
	}

	@RequestMapping(value = "proveedores", method = RequestMethod.POST, params = "siBorrarP")

	public String borrar(Proveedor proveedor, Model model, RedirectAttributes redirAttrs) {
		if (proveedor.getNitProveedor() == null) {
			redirAttrs.addFlashAttribute("mensajeBorrar", "Proveedor no encontrado");
		} else {
			List<Proveedor> proveedores = proveedorController.getProveedores();
			for (int i = 0; i < proveedores.size(); i++) {
				if (proveedores.get(i).getNitProveedor().equals(proveedor.getNitProveedor())) {

						proveedorController.eliminar(proveedores.get(i).getNitProveedor());
						redirAttrs.addFlashAttribute("mensajeBorrar2", "Proveedor eliminado");
						return "redirect:/proveedores";
					}
				}
			redirAttrs.addFlashAttribute("mensajeBorrar", "Proveedor no encontrado");
			}
		return "redirect:/proveedores";
	}

	@RequestMapping(value = "proveedores", method = RequestMethod.POST, params = "LimpiarP")

	public String vaciar() {

		return "redirect:/proveedores";

	}

	public void datos(Proveedor proveedor, RedirectAttributes redirAttrs) {
		redirAttrs.addFlashAttribute("nitProveedor", proveedor.getNitProveedor());
		redirAttrs.addFlashAttribute("ciudadProveedor", proveedor.getCiudadProveedor());
		redirAttrs.addFlashAttribute("direccionProveedor", proveedor.getDireccionProveedor());
		redirAttrs.addFlashAttribute("nombreProveedor", proveedor.getNombreProveedor());
		redirAttrs.addFlashAttribute("telefonoProveedor", proveedor.getTelefonoProveedor());
	}

	@RequestMapping(value = "proveedores", method = RequestMethod.POST, params = "actualizarP")

	public String Actualizar(Proveedor proveedor, Model model, RedirectAttributes redirAttrs) {
		if (proveedor.getNitProveedor() == null || proveedor.getCiudadProveedor().equals("") || proveedor.getDireccionProveedor().equals("")
				|| proveedor.getNombreProveedor().equals("") || proveedor.getTelefonoProveedor().equals("")) {
			redirAttrs.addFlashAttribute("mensajeActualizar3", "Faltaron datos, completelos. ");
			datos(proveedor, redirAttrs);

		} else {
			List<Proveedor> proveedores = proveedorController.getProveedores();

			for (int i = 0; i < proveedores.size(); i++) {
				if (proveedores.get(i).getNitProveedor().equals(proveedor.getNitProveedor())) {
					proveedorController.actualizar(proveedor);
					redirAttrs.addFlashAttribute("nitProveedor2", proveedor.getNitProveedor());					
					datosEncontrados(proveedores, redirAttrs, i);
					redirAttrs.addFlashAttribute("mensajeActualizar", "proveedor actualizado");
					return "redirect:/proveedores";
				}
			}

			redirAttrs.addFlashAttribute("mensajeActualizar2", "proveedor no encontrado");
		}

		return "redirect:/proveedores";

	}

	@RequestMapping(value = "proveedores", method = RequestMethod.POST, params = "crearP")

	public String Crear(Proveedor proveedor, Model model, RedirectAttributes redirAttrs) {

		if (proveedor.getNitProveedor() == null || proveedor.getCiudadProveedor().equals("") || proveedor.getDireccionProveedor().equals("")
				|| proveedor.getNombreProveedor().equals("") || proveedor.getTelefonoProveedor().equals("")) {
			redirAttrs.addFlashAttribute("mensajeCrear3", "Faltaron datos, completelos. ");
			datos(proveedor, redirAttrs);

		}else {
			List<Proveedor> proveedores = proveedorController.getProveedores();
			boolean validador = false;
			for (int i = 0; i < proveedores.size(); i++) {
				if (proveedores.get(i).getNitProveedor().equals(proveedor.getNitProveedor())) {
					validador = true;
				}
			}

			if (validador == false) {

				proveedorController.crear(proveedor);
				redirAttrs.addFlashAttribute("mensajeCrear", "Usuario creado");
				redirAttrs.addFlashAttribute("nitProveedor2", proveedor.getNitProveedor());	
				datos(proveedor, redirAttrs);
			} else {
				redirAttrs.addFlashAttribute("mensajeCrear2", "Esta cedula ya existe");
			}
		}

		return "redirect:/proveedores";

	}

}