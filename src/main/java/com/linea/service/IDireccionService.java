package com.linea.service;

import org.springframework.data.repository.query.Param;

import com.linea.entity.Direccion;

public interface IDireccionService extends AbstractFacade<Direccion, Integer> {

	public void editarUno(@Param("id") Direccion direccion);
	
	public Direccion findByAutor_id(Integer id);
	
}
