package com.linea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_autor_l_d")
public class AutorLDView {

	@Id
	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	private String barrio;
	
	@Column(name = "cantidad_libro")
	private Integer cantidadLibro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Integer getCantidadLibro() {
		return cantidadLibro;
	}

	public void setCantidadLibro(Integer cantidadLibro) {
		this.cantidadLibro = cantidadLibro;
	} 
	
	
}
