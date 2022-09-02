package co.edu.unbosque.xtreme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.xtreme.entity.DetalleVenta;
import co.edu.unbosque.xtreme.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {
	
	@Autowired
	DetalleVentaRepository detalleVentaRepository;
	
	public List<DetalleVenta> getDetalleVenta(){
		
		return detalleVentaRepository.findAll();
	}
	
	public void addDetalleVenta(DetalleVenta detalleVenta) {
		
		detalleVentaRepository.save(detalleVenta);
	}
	
	public void deleteDetalleVenta(Long codigo_detalle_venta) {
		detalleVentaRepository.deleteById(codigo_detalle_venta);
	}

}
