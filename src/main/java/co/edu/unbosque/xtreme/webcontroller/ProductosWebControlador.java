package co.edu.unbosque.xtreme.webcontroller;

import java.net.Authenticator.RequestorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.xtreme.controller.ProductoController;

@Controller
@CrossOrigin(origins ={"http://localhost:8080","http://54.145.169.132"})
public class ProductosWebControlador {
	
	@Autowired
	ProductoController productoController;
	@Autowired
	LoginWebControlador loginWebControlador;
	
	
	@GetMapping("/productos")
	public String monstrar(Model model) {
		
		if(loginWebControlador.getUsuario() != null) {					
			model.addAttribute("usuario2", loginWebControlador.getUsuario());	
			return "producto.html";
			
			}
			
			return "redirect:/login";
		
	}
	
	@RequestMapping("/cargarArchivo")
	public String cargar(@RequestParam ("archivo") MultipartFile archivo, RedirectAttributes ra) {
		
		 String mensaje = productoController.cargarArchivo(archivo);
		 
		System.out.println(archivo.getName());	
		 if (mensaje.contentEquals("correcto")) {
			 ra.addFlashAttribute("mensaje1", " El archivo " + archivo.getOriginalFilename() + " se subio correctamente");
			 
		 }else if (mensaje.contentEquals("DatosError")) {
			 ra.addFlashAttribute("mensaje", " Los datos del archivo " + archivo.getOriginalFilename()+ " son invalidos");
			 
		 }else {
			 ra.addFlashAttribute("mensaje", " Formato no valido");
			 
		 }				
		 return "redirect:/productos";
	}
	
	

}
