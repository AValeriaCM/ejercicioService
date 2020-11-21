package com.linea.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linea.dto.LibroDto;
import com.linea.entity.Libro;
import com.linea.service.ILibroService;


@RestController
@RequestMapping("/libros")
public class LibroController {

	//@Qualifier
	@Autowired
	private ILibroService service;
	
	@PreAuthorize("hasAuthority('Invitado')")
	@GetMapping("/listarPageable/{lazy}/{page}/{size}")
	public ResponseEntity<Page<Libro>> retornPageable(@PathVariable int page, @PathVariable int size, @PathVariable boolean lazy){
		Page<Libro> listaLibro= service.listarPaginado(page, size, lazy);
		return new ResponseEntity<Page<Libro>>(listaLibro, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Usuario')")
	@GetMapping("/listarId/{id}")
	public ResponseEntity<Libro> consultarId(@PathVariable int id){
		Libro libro = service.retornarId(id);
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('Invitado')")
	@PostMapping("/guardar")
	public ResponseEntity<Object> guardar(@Valid @RequestBody Libro obj) {
		service.guardar(obj);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('Invitado')")
	@PutMapping(path = "/editar")
	public ResponseEntity<Libro> editar(@RequestBody Libro libro){
		service.editar(libro);
		return new ResponseEntity<Libro>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Invitado')")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Libro> eliminar(@PathVariable int id){
		service.eliminar(id);
		return new ResponseEntity<Libro>(HttpStatus.NO_CONTENT);
	}
}
