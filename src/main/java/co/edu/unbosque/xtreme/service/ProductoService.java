package co.edu.unbosque.xtreme.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.xtreme.entity.Producto;
import co.edu.unbosque.xtreme.manejoCsv.LecturaCsv;
import co.edu.unbosque.xtreme.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	public void Guardar(MultipartFile archivo) {
		
		try {
			List<Producto> productos = LecturaCsv.csvAproductos(archivo.getInputStream());
			productoRepository.deleteAll();
			productoRepository.saveAll(productos);

		} catch (IOException e) {
			throw new RuntimeException("No se pudo guardar el archivo" + e.getMessage());

		}

	}
	
	public List<Producto> listaProductos(){
		return productoRepository.findAll();
	}
}
