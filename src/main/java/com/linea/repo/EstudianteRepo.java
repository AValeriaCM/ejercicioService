package com.linea.repo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.linea.dto.EstudianteDTO;

@Repository
public class EstudianteRepo {

	Connection connect;
	Statement stmt;
	
	public EstudianteRepo() {
		
	}
	
	public void crear(EstudianteDTO estudiante) {
		try {
			File archivo = new File("fichero/estudiante.txt");
			FileWriter escribir = new FileWriter(archivo, true); 
			escribir.write(estudiante.serializar(estudiante));
			escribir.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<EstudianteDTO> consultar(){
		List<EstudianteDTO> lista = new ArrayList<>();
		try {
			File archivo = new File("fichero/estudiante.txt");
			FileReader leer = new FileReader(archivo);
			BufferedReader br = new BufferedReader(leer);
			String linea = br.readLine();
			String palabra[] = linea.split(";");
			int i;
			for (i = 0; i < palabra.length; i++) {
				String buffer[] = palabra[i].split(",");
				EstudianteDTO estudiante = new EstudianteDTO(Long.parseLong(buffer[0]), buffer[1], buffer[2], buffer[3]);
				System.out.print(estudiante.getNombre());
				lista.add(estudiante);
			}
			
			return lista;
		} catch (FileNotFoundException e) {

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void editar(List<EstudianteDTO> lista) {
		try {
			File archivo = new File("fichero/estudiante.txt");
			FileWriter escribir = new FileWriter(archivo, false); 
			String concat="";
			for(EstudianteDTO estu : lista) {
				concat = concat + estu.serializar(estu);
			}
			escribir.write(concat);
			escribir.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void crearBD(EstudianteDTO estudiante) throws SQLException {
		ConexionConBD con = new ConexionConBD("postgres","admin");
		
		connect = con.con();
		stmt = con.stamt();
		stmt.execute("INSERT INTO public.estudiante(id, nombre, apellido, carrera)"
				+ "VALUES ("+estudiante.getId()+",'"+estudiante.getNombre()+"','"+estudiante.getApellido()+"','"+estudiante.getCarrera()+"')");
		connect.close();
	}
	
	public List<EstudianteDTO> retornarBD() throws SQLException{
		ConexionConBD con = new ConexionConBD("postgres","admin");	
		connect = con.con();
		stmt = con.stamt();
		stmt.execute("SELECT * FROM public.estudiante");
		connect.close();
		return null;
	}
	
}
