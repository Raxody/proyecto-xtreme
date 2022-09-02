package co.edu.unbosque.xtreme.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import co.edu.unbosque.xtreme.entity.Proveedor;
import co.edu.unbosque.xtreme.repository.ProveedorRepository;

@Service
public class ProveedorService {
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	public List<Proveedor> getProveedor(){
		
		return proveedorRepository.findAll();
	}
	
	public void addProveedor(Proveedor proveedor) {
		
		
		proveedorRepository.save(proveedor);
	}
	
	public void deleteProveedor (Long nit) {
		
		proveedorRepository.deleteById(nit);	
}
	
}
