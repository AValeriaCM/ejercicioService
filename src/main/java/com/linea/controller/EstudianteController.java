package com.linea.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linea.entity.Estudiante;
import com.linea.service.impl.EstudianteServImpl;

import io.swagger.annotations.Api;

@Validated
@Controller
@RestController
@RequestMapping("/estudiantes")
@Api(value = "REST informacion de estudiante")
public class EstudianteController {

	@Autowired
	EstudianteServImpl estudianteService;
	
	@GetMapping("/retornar")
	public ResponseEntity<List<Estudiante>> consultarTodos(){
		List<Estudiante> listaEstudiante = estudianteService.retornar();
		return new ResponseEntity<List<Estudiante>>(listaEstudiante, HttpStatus.OK);
	}
	
	@GetMapping("/retornarId")
	public ResponseEntity<Estudiante> consultarId(@Valid @RequestParam(value="id",required=true) @PathVariable int id){
		Estudiante estudiante = estudianteService.retornarId(id);
		return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);
				
	}
	
	@GetMapping("/retornarCedula")
	public ResponseEntity<Estudiante> filtrarCedula(@Valid @RequestParam(value="cedula",required=true) @PathVariable String cedula){
		Estudiante estudiante = estudianteService.filtrarCedula(cedula);
		return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);
	}
	
	@GetMapping("/listarPorNombre/{page}/{size}/{nombre}")
	public  ResponseEntity<Page<Estudiante>> rentorPorNombre(@PathVariable int page, @PathVariable int size, @PathVariable String nombre) {		
		Page<Estudiante> listaEstudiante = estudianteService.listarPorNombre(page, size, nombre);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);
	}
	
	@GetMapping("/listarPorNombreApellido/{page}/{size}/{nombre}/{apellido}")
	public  ResponseEntity<Page<Estudiante>> rentorPorNombreApellido(@PathVariable int page, @PathVariable int size, @PathVariable String nombre, @PathVariable String apellido) {		
		Page<Estudiante> listaEstudiante = estudianteService.buscarPorNombreApellido(page, size, nombre, apellido);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<Void> eliminarEstudiante(@Valid @RequestParam(value="id",required=true) @PathVariable int id){
		estudianteService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> guardar(@Valid @RequestBody Estudiante obj) {
		estudianteService.guardar(obj);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
//	@ApiOperation(value="Crear estudiante")
//	@PostMapping("/guardar")
//	public ResponseEntity<EstudianteDTO> crear(@NonNull @Valid @RequestBody EstudianteDTO estudiante){
//		estudianteService.guardarEstudiante(estudiante);
//		return new ResponseEntity<EstudianteDTO>(estudiante, HttpStatus.CREATED); 
//	}
//	
//	@ApiOperation(value="Listar estudiantes")
//	@GetMapping("/retornar")
//	public ResponseEntity<List<EstudianteDTO>> consultarTodos(){
//		return new ResponseEntity<List<EstudianteDTO>>(estudianteService.retornarListaEst(), HttpStatus.OK);
//	}
//	
//	@ApiOperation(value="Listar estudiantes por ID")
//	@GetMapping("/retornarId")
//	public ResponseEntity<EstudianteDTO> consultarId(@Valid @RequestParam(value="id",required=true) @PathVariable Long id){
//		return new ResponseEntity<EstudianteDTO>(estudianteService.retornarEstudianteId(id), HttpStatus.OK);
//				
//	}
//	
//	@ApiOperation(value="Editar datos del estudiante")
//	@PutMapping("/editar")
//	public ResponseEntity<EstudianteDTO> editar(@Valid @RequestBody EstudianteDTO estudiante){
//		estudianteService.editarEstudiante(estudiante);
//		return new ResponseEntity<EstudianteDTO>(estudiante, HttpStatus.OK);
//	}
//	
//	@ApiOperation(value="Eliminar estudiante por ID")
//	@DeleteMapping("/eliminar")
//	public ResponseEntity<Void> eliminarEstudiante(@Valid @RequestParam(value="id",required=true) @PathVariable Long id){
//		estudianteService.eliminarEstudiante(id);
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}
//	
//	
//	@ApiOperation(value="Crear estudiante en bd")
//	@PostMapping("/guardarenbd")
//	public ResponseEntity<EstudianteDTO> crearEnBD(@NonNull @Valid @RequestBody EstudianteDTO estudiante) throws SQLException{
//		estudianteService.guardarPorBD(estudiante);
//		return new ResponseEntity<EstudianteDTO>(estudiante, HttpStatus.CREATED); 
//	}
//	
//	@ApiOperation(value="Listar estudiantes en BD")
//	@GetMapping("/retornarbd")
//	public ResponseEntity<List<EstudianteDTO>> consultarTodosBD(){
//		return new ResponseEntity<List<EstudianteDTO>>(estudianteService.retornarListaEst(), HttpStatus.OK);
//	}
}
