package com.linea.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.linea.entity.Direccion;

public interface IDireccionRepo extends JpaRepository<Direccion, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.direccion SET barrio = :barrio, descripcion = :descripcion WHERE autor_id = :id", nativeQuery = true)
	public void editarUno(@Param("barrio")String barrio, @Param("descripcion")String descripcion, @Param("id")Integer id);
	
	public Direccion findByAutor_id(Integer id);
}
