package com.linea.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linea.entity.Estudiante;

@Repository
public interface IEstudianteRepo extends JpaRepository<Estudiante, Integer> {

	@Query("SELECT ee FROM Estudiante ee WHERE ee.cedula = :cedula") 
	public Estudiante filtrarCedula(@Param("cedula") String cedula);
	
	public Estudiante findByCedula(String cedula);

	public Page<Estudiante> findByNombreIgnoreCase(String nombre, Pageable pageable);

	@Query(value = "SELECT ee FROM Estudiante ee WHERE ee.nombre = :nombre AND ee.apellido = :apellido")
	public Page<Estudiante> buscarPorNombreApellido(String nombre, String apellido, Pageable pageable);
	
	//public List<Estudiante> findByApellidoOrderBySeatNumberAsc(String apellido, Sort sort);
}
