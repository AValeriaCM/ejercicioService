package com.linea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linea.dto.AutorLectorDto;
import com.linea.entity.Autor;
import com.linea.entity.AutorLDView;
import com.linea.entity.AutorLector;
import com.linea.entity.Direccion;
import com.linea.entity.Lector;
import com.linea.entity.Libro;
import com.linea.exception.ArgumentRequiredException;
import com.linea.exception.BusinessLogicException;
import com.linea.exception.ModelNotFoundException;
import com.linea.repo.IAutorLector;
import com.linea.repo.IAutorRepo;
import com.linea.repo.IAutorViewRepo;
import com.linea.repo.IDireccionRepo;
import com.linea.service.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorRepo repo;
	
	@Autowired
	private IAutorLector repoAutorLector;
	
	@Autowired
	private IDireccionRepo repoDireccion;
	
	@Autowired
	private IAutorViewRepo repoView;
	
	@Override
	public Page<Autor> listarPaginado(Integer page, Integer size, Boolean lazy) {
		Page<Autor> listaPaginadoAutor=repo.findAll(PageRequest.of(page, size, Sort.by("apellido").ascending()));
		
		if(lazy) {
			for(Autor autor: listaPaginadoAutor.getContent()) {
				autor.setLibro(null);
				autor.setDireccion(null);
			}
		}
		
		
		return listaPaginadoAutor;
	}

	@Override
	public Autor retornarId(Integer id) {
		Autor autor = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Autor no encontrado"));
		return autor;
	}

	@Override
	public void guardar(Autor autor) {
		if(autor.getLibro() != null) {
			for(Libro libro: autor.getLibro()) {
				libro.setAutor(autor);
			}
		}
		if(autor.getDireccion() != null) {
			autor.getDireccion().setAutor(autor);
		}
		repo.save(autor);
	}

	@Override
	public void editar(Autor autor) {
		if(autor.getId() == null) 
			throw new ArgumentRequiredException("Por favor ingrese ID");
		Autor autorR = repo.findById(autor.getId()).orElseThrow(()
				-> new ModelNotFoundException("Autor no encontrado"));
		
		autorR.setNombre(autor.getNombre());
		autorR.setApellido(autor.getApellido());
		autorR.setFechaNacimiento(autor.getFechaNacimiento());
		autorR.getDireccion().setDescripcion(autor.getDireccion().getDescripcion());
		autorR.getDireccion().setBarrio(autor.getDireccion().getBarrio());
		this.retornarId(autor.getId());
		
		repo.save(autor);
		
	}

	@Override
	public void eliminar(Integer idAutor) {
		Autor autor = this.retornarId(idAutor);
		List<Libro> libro = autor.getLibro();
		if(libro != null && autor.getLibro().size() > 0) {
			throw new BusinessLogicException("Debe eliminar todos los libros asociados a este Autor");
		}
		repo.deleteById(idAutor);
		
	}


	@Override
	public Autor findByNombre(String nombre) {
		Autor autor = repo.findByNombre(nombre);
		return autor;
	}

	@Override
	public Autor filtrarNombreLibro(String nombre) {
		return repo.filtrarNombreLibro(nombre);
	}

	@Override
	public Autor findByLibro_Nombre(String libro) {
		Autor autor = repo.findByLibro_Nombre(libro);
		return autor;
	}

	@Override
	public void editarUno(Direccion direccion) {
		if(direccion.getId()==null) {
			throw new ArgumentRequiredException("Por favor ingrese ID");
		} else {
			repoDireccion.editarUno(direccion.getBarrio(), direccion.getDescripcion(), direccion.getId());
			
		}
	}

	@Override
	public Direccion findByAutor_id(Integer id) {
		return repoDireccion.findByAutor_id(id);
	}

	@Override
	public List<AutorLDView> listarView() {
		return repoView.listarView();
	}

	@Override
	public void guardarAutorLector(AutorLectorDto dto) {
		
		//Validar info
		repoAutorLector.guardar(
				dto.getAutor().getId(), dto.getLector().getId(), dto.getInfoAdicional());
	}
	
	@Transactional
	@Override
	public void guardarAutorLector(List<AutorLectorDto> dto) {
		//Validar Info
		for (AutorLectorDto obj : dto) {
			//try {
			repoAutorLector.guardar(
					obj.getAutor().getId(), obj.getLector().getId(), obj.getInfoAdicional());
			//}
			//catch(Exception e) {
			//	System.out.println(e.getMessage());
			//}
			
		}
	}	

	@Override
	public List<AutorLector> listarAutorLector(Integer idAutor) {
		
		List<AutorLector> lista= repoAutorLector.listarAutorLector(idAutor);
		for (AutorLector autorLector : lista) {
			autorLector.setAutor(null);
		}
		
		return lista;
	}

	
	
}
