package com.linea.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.linea.dto.AutorLectorDto;
import com.linea.entity.Autor;
import com.linea.entity.AutorLDView;
import com.linea.entity.AutorLector;
import com.linea.entity.Direccion;
import com.linea.entity.Libro;

public interface IAutorService extends AbstractFacade<Autor, Integer> {
	
	public Autor filtrarNombreLibro(@Param("nombre") String nombre);
	
	public Autor findByNombre(String nombre);
	
	public Autor findByLibro_Nombre(String nombre);
	
	public void editarUno(@Param("id") Direccion direccion);
	
	public Direccion findByAutor_id(Integer id);
	
	public List<AutorLDView> listarView();
	
	public void guardarAutorLector(AutorLectorDto autorLector);
	
	public void guardarAutorLector(List<AutorLectorDto> dto);
	
	List<AutorLector> listarAutorLector(Integer idAutor);
	
}
