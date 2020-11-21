package com.linea.dto;

public class AutorDto {
	
	private Integer Id;

	private String nombre;
	
	private String apellido;
	
	private Long fechaNacimiento;

	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Long fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	

}
