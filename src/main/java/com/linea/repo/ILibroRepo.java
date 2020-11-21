package com.linea.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linea.entity.Autor;
import com.linea.entity.Libro;

@Repository
public interface ILibroRepo extends JpaRepository<Libro, Integer> {

	public Libro findByNombre(String nombre);
}
