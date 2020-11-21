package com.linea.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.linea.entity.Estudiante;


@Service
public interface IEstudianteService {
	
//	public List<EstudianteDTO> retornarListaEst();
//	public EstudianteDTO retornarEstudianteId(Long id);
//	public EstudianteDTO guardarEstudiante(EstudianteDTO estudiante);
//	public EstudianteDTO editarEstudiante(EstudianteDTO estudiante);
//	public void eliminarEstudiante(Long id);
//	
//	public void guardarPorBD(EstudianteDTO estudiante) throws SQLException;
//	public List<EstudianteDTO> retornarTodosBD() throws SQLException;

	public List<Estudiante> retornar();
	
	public Estudiante retornarId(Integer id);
	
	public Estudiante filtrarCedula(String cedula);
	
	public Page<Estudiante>listarPorNombre(int page, int size, String nombre);
	
	public Page<Estudiante> buscarPorNombreApellido(int page, int size, String nombre, String apellido);
	
	//public List<Estudiante> findByApellidoOrderBySeatNumberAsc(String apellido, Sort sort);
	
	public void guardar(Estudiante estudiante);
	
	public void editar(Estudiante estudiante);
	
	public void eliminar(Integer idEstudiante);
	
}
