package co.edu.unbosque.xtreme.manejoCsv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.xtreme.entity.Producto;
import co.edu.unbosque.xtreme.entity.Proveedor;

public class LecturaCsv {
	
	public static String Tipo = "application/vnd.ms-excel";
	
	static String[] encabezados = {"codigo_producto","iva_compra","nombre_producto","precio_compra","precio_venta","nit_proveedor"};
	
	public static boolean tieneFormatoCsv(MultipartFile archivo) {
		System.out.println(archivo.getContentType());
		if(!Tipo.equals(archivo.getContentType())) {			
			return false;
		}
	
		
		return true;
	}
	
	
	public static List <Producto> csvAproductos(InputStream archivo){
		System.out.println("estoy entrando1" );			
		try(BufferedReader lectorArchivo = new BufferedReader(new InputStreamReader(archivo, "UTF-8"));
				CSVParser csvParser = new CSVParser(lectorArchivo, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().
						withTrim());){
			List <Producto> productos = new ArrayList<Producto>();
			
			Iterable <CSVRecord> csvLineas = csvParser.getRecords();

			for (CSVRecord csvLinea : csvLineas ) {
				System.out.println("estoy entrando3" + csvLinea);
				Producto producto = new Producto(
						Long.parseLong(csvLinea.get("codigo_producto")),
						csvLinea.get("nombre_producto"),
						new Proveedor (Long.parseLong(csvLinea.get("nit_proveedor"))),
						Double.parseDouble(csvLinea.get("precio_compra")),
						Double.parseDouble(csvLinea.get("iva_compra")),
						Double.parseDouble(csvLinea.get("precio_venta"))
						);
					
				productos.add(producto);
			}
			System.out.println("estoy entrando6" );
			return productos;
		}catch(IOException e) {
			System.out.println(e.toString());
			System.out.println("No se pudo pasar el archivo" );
			return null;
			//throw new RuntimeException("No se pudo pasar el archivo" + e.getMessage());
			
			
		}
	
		
	}

}

