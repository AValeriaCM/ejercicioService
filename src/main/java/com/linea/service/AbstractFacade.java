package com.linea.service;

import org.springframework.data.domain.Page;

public abstract interface AbstractFacade <E, ID > {
	
	public void guardar(E entidad);
	public E retornarId(ID id);
	public Page<E> listarPaginado(ID page, ID size, Boolean lazy);
	public void editar(E entidad);
	public void eliminar(ID id);
	

}
