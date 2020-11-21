package com.linea.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linea.dto.AutorLectorDto;
import com.linea.entity.Autor;
import com.linea.entity.AutorLDView;
import com.linea.entity.Direccion;
import com.linea.service.IAutorService;
import com.linea.service.IDireccionService;
import com.linea.entity.AutorLector;

@PreAuthorize("hasAuthority('Admin')")
@RestController
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private IAutorService service;
	
	@Autowired
	private IDireccionService serviceDireccion;
		
	@GetMapping("/listarPageable/{lazy}/{page}/{size}")
	public ResponseEntity<Page<Autor>> retornPageable(@PathVariable int page, @PathVariable int size, @PathVariable boolean lazy){
		Page<Autor> listaAutor= service.listarPaginado(page, size, lazy);
		return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);
	}
	
	@GetMapping("/filtNombreLibro/{nombre}")
	public ResponseEntity<Autor> retornNombreLibro(@PathVariable String nombre ){
		Autor autor = service.filtrarNombreLibro(nombre);
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}
	
	@GetMapping("/filtNombreLibro2/{libro}")
	public ResponseEntity<Autor> retornNombreLibro2(@PathVariable String libro ){
		Autor autor = service.findByLibro_Nombre(libro);
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}
	
	@GetMapping("/listarId/{id}")
	public ResponseEntity<Autor> consultarId(@PathVariable int id){
		Autor autor = service.retornarId(id);
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}
	
	@GetMapping("/listarView")
	public ResponseEntity<List<AutorLDView>> listarView(){
		List<AutorLDView> listaVista = service.listarView();
		return new ResponseEntity<List<AutorLDView>>(listaVista, HttpStatus.OK);
	}
	
	@GetMapping("/listarAutorLector/{idAutor}")
	public ResponseEntity<List<AutorLector>> listarAutorLector(@PathVariable int idAutor)  {
			List<AutorLector> listaAutorLector = service.listarAutorLector(idAutor);
			return new ResponseEntity<List<AutorLector>>(listaAutorLector, HttpStatus.OK);					
	}

	@PostMapping("/guardar")
	public ResponseEntity<Object> guardar(@Valid @RequestBody Autor obj) {
		service.guardar(obj);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PostMapping("/asociarLector")
	public ResponseEntity<Object> guardarAutorLector(@RequestBody AutorLectorDto obj) {
		service.guardarAutorLector(obj);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PostMapping("/asociarLectorLista")
	public ResponseEntity<Object> guardarAutorLectorLista(@RequestBody List<AutorLectorDto> obj) {
		service.guardarAutorLector(obj);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@PutMapping(path = "/editar")
	public ResponseEntity<Autor> editarU(@RequestBody Autor autor){
		service.editar(autor);
		return new ResponseEntity<Autor>(HttpStatus.OK);
	}
	
	@PutMapping(path = "/editarDos")
	public ResponseEntity<Direccion> editarD(@RequestBody Direccion direccion){
		serviceDireccion.editarUno(direccion);
		return new ResponseEntity<Direccion>(HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Autor> eliminar(@PathVariable int id){
		service.eliminar(id);
		return new ResponseEntity<Autor>(HttpStatus.NO_CONTENT);
	}
}
