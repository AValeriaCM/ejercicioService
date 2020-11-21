package com.linea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "direccion")
public class Direccion {
	
	@Id
	private Integer id;
	
	@NotNull(message = "Debe digitar una descripcion")
	@Size(min = 3, max = 80, message = "Debe tener entre 3 y 80 caracteres")
	@Column(name = "descripcion", nullable = false, length=80)
	private String descripcion;
	
	@NotNull(message = "Debe digitar un barrio")
	@Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 caracteres")
	@Column(name = "barrio", nullable = false, length=25)
	private String barrio;
	
	@OneToOne
	@MapsId
	private Autor autor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	
	@JsonIgnore
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
}
