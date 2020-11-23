package com.linea.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linea.entity.Usuario;
import com.linea.service.IUsuarioService;



@PreAuthorize("hasAuthority('Usuario')")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	
	private IUsuarioService usuarioService;
	
	@GetMapping("/listarPageable/{lazy}/{page}/{size}")
	public ResponseEntity<Page<Usuario>> retornPageable(@PathVariable int page, @PathVariable int size, @PathVariable boolean lazy){
		Page<Usuario> listaUsuario= usuarioService.listarPaginado(page, size, lazy);
		return new ResponseEntity<Page<Usuario>>(listaUsuario);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> guardar(@Valid @RequestBody Usuario obj) {
		usuarioService.guardar(obj);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
}
