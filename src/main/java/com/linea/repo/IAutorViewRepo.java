package com.linea.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.linea.entity.AutorLDView;


public interface IAutorViewRepo extends JpaRepository<AutorLDView, Integer> {

	@Query(value = "SELECT * FROM view_autor_l_d", nativeQuery = true)
	public List<AutorLDView> listarView();
}
