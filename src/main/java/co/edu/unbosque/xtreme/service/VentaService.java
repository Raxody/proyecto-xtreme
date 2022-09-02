package co.edu.unbosque.xtreme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.xtreme.entity.Venta;
import co.edu.unbosque.xtreme.repository.VentasRepository;

@Service
public class VentaService {
	
	@Autowired
	VentasRepository ventasRepository;
	
	public List<Venta> getVenta(){
		return ventasRepository.findAll();
	}
	
	public void addVenta(Venta venta) {
		
		ventasRepository.save(venta);
	}
	
	public void deleteVenta(Long codigo_venta) {
		ventasRepository.deleteById(codigo_venta);
	}
	
	
	

}
