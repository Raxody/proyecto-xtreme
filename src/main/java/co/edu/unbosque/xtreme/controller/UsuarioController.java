package co.edu.unbosque.xtreme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.xtreme.entity.Usuario;
import co.edu.unbosque.xtreme.service.UsuarioService;


@RestController
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
@RequestMapping (path = "/testUsuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	@GetMapping("/listarUsuario")
	public List<Usuario> getUsuarios(){
		
		return usuarioService.getUsuario();
	}
	
	@DeleteMapping("/eliminarUsuario/{cedula}")
	public void eliminar(@PathVariable("cedula")Long cedula ) {
		usuarioService.deleteUsuario(cedula);
	}
	
	@PutMapping("/actualizarUsuario")
	public void actualizar(@RequestBody Usuario usuario) {
		usuarioService.addUsuario(usuario);
	}
	
	@PostMapping("guardarUsuario")
	public void crear(@RequestBody Usuario usuario) {
		usuarioService.addUsuario(usuario);
	}
}
