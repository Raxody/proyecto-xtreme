package co.edu.unbosque.xtreme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.xtreme.entity.Venta;
import co.edu.unbosque.xtreme.service.VentaService;

@RestController
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
@RequestMapping (path = "/testVenta")
public class VentaController {
	
	@Autowired
	VentaService ventaService;
	
	@GetMapping("/listaVentas")
	public List<Venta> getVentas(){
		
		return ventaService.getVenta();
	}
	
	@DeleteMapping ("/eliminarVenta/{id}")
	
	public void eliminar(@PathVariable("id")Long id) {
		
		ventaService.deleteVenta(id);
	}
	
	@PutMapping ("/actualizarVenta")
	
	public void actualizar(@RequestBody Venta venta){
		
		ventaService.addVenta(venta);
	}
	
	@PostMapping ("/crearVenta")
	
	public void crear(@RequestBody Venta venta) {
		
		ventaService.addVenta(venta);
		
	}

}
