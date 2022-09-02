package co.edu.unbosque.xtreme.webcontroller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.xtreme.controller.UsuarioController;
import co.edu.unbosque.xtreme.entity.Usuario;


@Controller
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
public class UsuariosWebControlador {
	@Autowired
	private UsuarioController usuarioController;
	
	@Autowired
	LoginWebControlador loginWebControlador;
	
	@GetMapping("/usuarios")
	public String mostrar(Model model) {
		if(loginWebControlador.getUsuario() != null) {
			
		
		model.addAttribute("usuario2", loginWebControlador.getUsuario());	
		return "usuarios.html";
		
		}
		
		return "redirect:/login";
	}

	@RequestMapping(value = "usuarios", method = RequestMethod.POST, params = "consultar")

	public String Consultar(Usuario usuario, Model model, RedirectAttributes redirAttrs) {

		List<Usuario> usuarios = usuarioController.getUsuarios();

		boolean validador = false;
		for (int i = 0; i < usuarios.size(); i++) {

			if (usuarios.get(i).getCedulaUsuario().equals(usuario.getCedulaUsuario())) {
				datosEncontrados(usuarios, redirAttrs, i);
				return "redirect:/usuarios";
			}

		}

		if (validador == false) {
			redirAttrs.addFlashAttribute("mensaje", "Usuario no existe");
		}

		return "redirect:/usuarios";

	}

	public void datosEncontrados(List<Usuario> usuarios, RedirectAttributes redirAttrs, int i) {
		redirAttrs.addFlashAttribute("cedulaUsuario", usuarios.get(i).getCedulaUsuario());
		redirAttrs.addFlashAttribute("nombreUsuario", usuarios.get(i).getNombreUsuario());
		redirAttrs.addFlashAttribute("passwordUsuario", usuarios.get(i).getPasswordUsuario());
		redirAttrs.addFlashAttribute("usr", usuarios.get(i).getUsr());
		redirAttrs.addFlashAttribute("emailUsuario", usuarios.get(i).getEmailUsuario());
		redirAttrs.addFlashAttribute("idcedula2", usuarios.get(i).getCedulaUsuario());
	}

	@RequestMapping(value = "usuarios", method = RequestMethod.POST, params = "noBorrar")

	public String opcion() {

		return "redirect:/usuarios";
	}

	@RequestMapping(value = "usuarios", method = RequestMethod.POST, params = "siBorrar")

	public String borrar(Usuario usuario, Model model, RedirectAttributes redirAttrs) {
		if (usuario.getCedulaUsuario() == null) {
			redirAttrs.addFlashAttribute("mensajeBorrar", "Usuario no encontrado");
		} else {

			List<Usuario> usuarios = usuarioController.getUsuarios();
			for (int i = 0; i < usuarios.size(); i++) {

				if (usuario.getCedulaUsuario() == 123456) {
					redirAttrs.addFlashAttribute("mensajeBorrar", "No se puede borrar Admin principal");
					return "redirect:/usuarios";
				} else {

					if (usuarios.get(i).getCedulaUsuario().equals(usuario.getCedulaUsuario())) {

						usuarioController.eliminar(usuarios.get(i).getCedulaUsuario());
						redirAttrs.addFlashAttribute("mensajeBorrar2", "Usuario eliminado");
						return "redirect:/usuarios";
					}
				}
			}

			redirAttrs.addFlashAttribute("mensajeBorrar", "Usuario no encontrado");

		}

		return "redirect:/usuarios";

	}

	@RequestMapping(value = "usuarios", method = RequestMethod.POST, params = "Limpiar")

	public String vaciar() {

		return "redirect:/usuarios";

	}

	public void datos(Usuario usuario, RedirectAttributes redirAttrs) {
		redirAttrs.addFlashAttribute("cedulaUsuario", usuario.getCedulaUsuario());
		redirAttrs.addFlashAttribute("nombreUsuario", usuario.getNombreUsuario());
		redirAttrs.addFlashAttribute("passwordUsuario", usuario.getPasswordUsuario());
		redirAttrs.addFlashAttribute("usr", usuario.getUsr());
		redirAttrs.addFlashAttribute("emailUsuario", usuario.getEmailUsuario());
	}

	@RequestMapping(value = "usuarios", method = RequestMethod.POST, params = "actualizar")

	public String Actualizar(Usuario usuario, Model model, RedirectAttributes redirAttrs) {
		if (usuario.getCedulaUsuario() == null || usuario.getEmailUsuario().equals("") || usuario.getNombreUsuario().equals("")
				|| usuario.getPasswordUsuario().equals("") || usuario.getUsr().equals("")) {
			redirAttrs.addFlashAttribute("mensajeActualizar3", "Faltaron datos, completelos. ");
			datos(usuario, redirAttrs);

		} else {
			List<Usuario> usuarios = usuarioController.getUsuarios();

			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getCedulaUsuario().equals(usuario.getCedulaUsuario())) {
					usuarioController.actualizar(usuario);

					redirAttrs.addFlashAttribute("idcedula2", usuario.getCedulaUsuario());
					datosEncontrados(usuarios, redirAttrs, i);
					redirAttrs.addFlashAttribute("mensajeActualizar", "Usuario Actualizado");
					return "redirect:/usuarios";
				}
			}

			redirAttrs.addFlashAttribute("mensajeActualizar2", "Usuario no encontrado");
		}

		return "redirect:/usuarios";

	}

	@RequestMapping(value = "usuarios", method = RequestMethod.POST, params = "crear")

	public String Crear(Usuario usuario, Model model, RedirectAttributes redirAttrs) {

		if (usuario.getCedulaUsuario() == null || usuario.getEmailUsuario().equals("") || usuario.getNombreUsuario().equals("")
				|| usuario.getPasswordUsuario().equals("") || usuario.getUsr().equals("")) {
			redirAttrs.addFlashAttribute("mensajeCrear3", "Faltaron datos, completelos. ");
			datos(usuario, redirAttrs);
		} else {
			List<Usuario> usuarios = usuarioController.getUsuarios();
			boolean validador = false;
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getCedulaUsuario().equals(usuario.getCedulaUsuario())) {
					validador = true;
				}
			}

			if (validador == false) {

				usuarioController.crear(usuario);
				redirAttrs.addFlashAttribute("mensajeCrear", "Usuario creado");
				redirAttrs.addFlashAttribute("idcedula2", usuario.getCedulaUsuario());
				datos(usuario, redirAttrs);
			} else {
				redirAttrs.addFlashAttribute("mensajeCrear2", "Esta cedula ya existe");
			}
		}

		return "redirect:/usuarios";
	}
	


}
