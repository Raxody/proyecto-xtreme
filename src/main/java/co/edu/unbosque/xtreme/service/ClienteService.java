package co.edu.unbosque.xtreme.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.xtreme.entity.Cliente;
import co.edu.unbosque.xtreme.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private  ClienteRepository clienteRepository;
	
	
	public List<Cliente> getCliente(){
		
		return clienteRepository.findAll();
	}
	
	public void addClientes(Cliente cliente) {
		
		
		clienteRepository.save(cliente);
	}
	
	public void deleteClientes (Long cedula) {
		
		clienteRepository.deleteById(cedula);
		
	}
}
