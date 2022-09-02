package co.edu.unbosque.xtreme.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.xtreme.entity.Usuario;
import co.edu.unbosque.xtreme.repository.UsuarioRepository;



@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> getUsuario(){
		
		return usuarioRepository.findAll();
	}
	
	public void addUsuario(Usuario usuario) {
		
		
		usuarioRepository.save(usuario);
	}
	
	public void deleteUsuario (Long cedula) {
		
			usuarioRepository.deleteById(cedula);
		
	}
}
