package com.linea.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.linea.entity.Autor;
import com.linea.entity.AutorLector;

public interface IAutorLector extends JpaRepository<AutorLector, Integer> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO autor_lector(id_autor, id_lector, info_adicional) "
			+ "VALUES (:idAutor, :idLector, :infoAdicional)", nativeQuery = true)
	Integer guardar(@Param("idAutor") Integer idAutor, @Param("idLector") Integer idLector, @Param("infoAdicional") String infoAdicional);	
	
	//Eliminar 
	
	@Query("from  AutorLector ce where ce.autor.id = :idAutor")
	List<AutorLector> listarAutorLector(@Param("idAutor") Integer idAutor);
}
