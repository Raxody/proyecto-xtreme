package co.edu.unbosque.xtreme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.xtreme.entity.Producto;
import co.edu.unbosque.xtreme.manejoCsv.LecturaCsv;
import co.edu.unbosque.xtreme.service.ProductoService;

@RestController
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
@RequestMapping("/testProducto")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@PostMapping("/cargar")
	public String cargarArchivo(@RequestParam("file") MultipartFile archivo) {

		String mensaje = "";
		
		if (LecturaCsv.tieneFormatoCsv(archivo)) {
			try {

				productoService.Guardar(archivo);
				mensaje = "correcto";
				return mensaje;
			} catch (Exception e) {
				mensaje = "DatosError";
				return mensaje;
			}
		}
			mensaje = "FormatoError";
		
		

		return mensaje;

	}
	@GetMapping("/lista")
	public List<Producto> listarProductos(){
		return productoService.listaProductos();
	}

}
