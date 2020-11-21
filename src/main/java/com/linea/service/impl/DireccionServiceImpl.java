package com.linea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.linea.entity.Direccion;
import com.linea.exception.ArgumentRequiredException;
import com.linea.exception.ModelNotFoundException;
import com.linea.repo.IDireccionRepo;
import com.linea.service.IDireccionService;

@Service
public class DireccionServiceImpl implements IDireccionService{
	
	@Autowired
	private IDireccionRepo repoDireccion;

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
	public void editar(Direccion direccion) {
		if(direccion.getId()==null) 
			throw new ArgumentRequiredException("Por favor ingrese ID");
		Direccion direccionR = repoDireccion.findById(direccion.getId()).orElseThrow(()
				-> new ModelNotFoundException("Id no encontrado"));
		
		direccionR.setBarrio(direccion.getBarrio());
		direccionR.setDescripcion(direccion.getDescripcion());

		repoDireccion.save(direccionR);
		
	}

	@Override
	public void guardar(Direccion entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Direccion retornarId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Direccion> listarPaginado(Integer page, Integer size, Boolean lazy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}
	

}
