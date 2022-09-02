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

import co.edu.unbosque.xtreme.entity.Cliente;
import co.edu.unbosque.xtreme.service.ClienteService;

@RestController
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
@RequestMapping (path = "/testCliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/listarCliente")	
	public List<Cliente> getClientes(){		
		return clienteService.getCliente();
	}
	
	@DeleteMapping("/eliminarCliente/{cedula}")
	public void eliminar(@PathVariable("cedula")Long cedula ) {
		clienteService.deleteClientes(cedula);
	}
	
	@PutMapping("/actualizarCliente")
	public void actualizar(@RequestBody Cliente cliente) {
		clienteService.addClientes(cliente);
	}
	
	@PostMapping("guardarCliente")
	public void crear(@RequestBody Cliente cliente) {
		clienteService.addClientes(cliente);
	}
}
