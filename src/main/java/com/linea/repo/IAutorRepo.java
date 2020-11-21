package com.linea.repo;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linea.entity.Autor;

@Repository
public interface IAutorRepo extends JpaRepository<Autor, Integer> {

	public Autor findByNombre(String nombre);
	
	public Autor findByLibro_Nombre(String nombre);
	
	@Query("SELECT aa FROM Autor aa, Libro l WHERE l.nombre = :nombre AND aa.id = l.autor.id")
	public Autor filtrarNombreLibro(@Param("nombre") String nombre);
	
	@Query("SELECT count(a) FROM Autor aa WHERE aa.id = : id")
	BigInteger validarExistenciaId(@Param("id") Integer id);
	
}
