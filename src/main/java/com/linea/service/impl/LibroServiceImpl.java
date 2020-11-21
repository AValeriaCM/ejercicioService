package com.linea.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.linea.entity.Libro;
import com.linea.exception.ArgumentRequiredException;
import com.linea.exception.BusinessLogicException;
import com.linea.exception.ModelNotFoundException;
import com.linea.exception.ObjectItsCreatedException;
import com.linea.repo.IAutorRepo;
import com.linea.repo.ILibroRepo;
import com.linea.service.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService {
	
	@Autowired
	private ILibroRepo repo;
	
	@Autowired
	private IAutorRepo autorRepo;

	private String nombre;
	@Override
	public Page<Libro> listarPaginado(Integer page, Integer size, Boolean lazy) {
		Page<Libro> listaPaginadoLibro=repo.findAll(PageRequest.of(page, size, Sort.by("nombre").ascending()));
		return listaPaginadoLibro;
	}

	@Override
	public Libro retornarId(Integer id) {
		Libro libro = repo.findById(id).orElseThrow(() 
				-> new ModelNotFoundException("Libro no encontrado"));
		return libro;
	}

	@Override
	public void guardar(Libro libro) {
		if(libro.getAutor() == null) {
			throw new ArgumentRequiredException("Autor es requerido");
		} else {
			nombre = libro.getNombre();
			if(repo.findByNombre(nombre)==null){
				repo.save(libro);
			} else {
				throw new BusinessLogicException("Ya existe un libro con ese nombre");
			}
		}
	}

	@Override
	public void editar(Libro libro) {
		if(libro.getId() == null) {
			throw new ArgumentRequiredException("Id Libro es requerido");
		}
		Libro libroent = repo.findById(libro.getId()).orElseThrow(() 
				-> new ModelNotFoundException("Libro no encontrado"));
		libroent.setNombre(libro.getNombre());
		libroent.setNumeroPaginas(libro.getNumeroPaginas());
		repo.save(libroent);
	}

	@Override
	public void eliminar(Integer idLibro) {
		try {
		repo.existsById(idLibro);
		}catch(Exception ex) {
			System.out.println("Entra captura");
			throw new EmptyResultDataAccessException("Libro no encontrado",0);
		}
		repo.deleteById(idLibro);		
	}



}
