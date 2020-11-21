package com.linea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linea.entity.Direccion;
import com.linea.service.IDireccionService;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {
	
	@Autowired
	private IDireccionService service;
	
	@PutMapping(path = "/editar")
	public ResponseEntity<Direccion> editarU(@RequestBody Direccion direccion){
		service.editarUno(direccion);
		return new ResponseEntity<Direccion>(HttpStatus.OK);
	}
	
	@PutMapping(path = "/editarDos")
	public ResponseEntity<Direccion> editarD(@RequestBody Direccion direccion){
		service.editar(direccion);
		return new ResponseEntity<Direccion>(HttpStatus.OK);
	}
}
