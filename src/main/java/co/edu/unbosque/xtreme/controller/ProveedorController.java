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

import co.edu.unbosque.xtreme.entity.Proveedor;
import co.edu.unbosque.xtreme.service.ProveedorService;

@RestController
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
@RequestMapping (path = "/testProveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping("/listarProveedor")	
	public List<Proveedor> getProveedores(){
		
		return proveedorService.getProveedor();
	}
	
	@DeleteMapping("/eliminarProveedor/{nit}")
	public void eliminar(@PathVariable("nit")Long nit ) {
		proveedorService.deleteProveedor(nit);
	}
	
	@PutMapping("/actualizarProveedor")
	public void actualizar(@RequestBody Proveedor proveedor) {
		proveedorService.addProveedor(proveedor);
	}
	
	@PostMapping("/guardarProveedor")
	public void crear(@RequestBody Proveedor proveedor) {
		proveedorService.addProveedor(proveedor);
	}
}
