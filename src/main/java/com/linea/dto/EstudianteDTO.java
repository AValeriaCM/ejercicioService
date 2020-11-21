package com.linea.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo Estudiante")
public class EstudianteDTO {

	//@Id
	@NotNull(message = "Se requiere ID")
	@ApiModelProperty(value = "Id del estudiante", required = true)
	private long id;
	
	@NotNull(message = "Debe digitar un nombre")
	@Size(min = 3, max = 20, message = "El nombre del estudiante debe tener entre 5 y 20 caracteres")
	@Pattern(regexp = "[a-z]+", message = "Campo nombre solo requiere letras en minusculas")
	@ApiModelProperty(value = "Nombre del estudiante", required = true)
	private String nombre;
	
	@NotNull(message = "Debe digitar un apellido")
	@Size(min = 3, max = 20, message = "El apellido del estudiante debe tener entre 5 y 20 caracteres")
	@Pattern(regexp = "[a-z]+", message = "Campo apellido solo requiere letras en minusculas")
	@ApiModelProperty(value = "Apellido del estudiante", required = true)
	private String apellido;
	
	@NotNull(message = "Debe digitar una carrera")
	@Size(min = 5, max = 20, message = "La carrera ingresada no existe")
	@ApiModelProperty(value = "Carrera del estudiante", required = true)
	private String carrera;
	
	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param carrera
	 */
	public EstudianteDTO(long id, String nombre, String apellido, String carrera) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.carrera = carrera;
	}
/**
 * 
 * @return
 */
	public long getId() {
		return id;
	}
/**
 * 
 * @param id
 */
	public void setId(long id) {
		this.id = id;
	}
/**
 * 
 * @return
 */
	public String getNombre() {
		return nombre;
	}
/**
 * 
 * @param nombre
 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
/**
 * 
 * @return
 */
	public String getApellido() {
		return apellido;
	}
/**
 * 
 * @param apellido
 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
/**
 * 
 * @return
 */
	public String getCarrera() {
		return carrera;
	}
/**
 * 
 * @param carrera
 */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	/**
	 * 
	 * @param estu
	 * @return
	 */
	public String serializar(EstudianteDTO estu) {
		String stringcompleto;
		stringcompleto = Long.toString(estu.getId()) +","+estu.getNombre()+","+ estu.getApellido()+ "," +estu.getCarrera()+";";
		return stringcompleto;
	}
	
}
