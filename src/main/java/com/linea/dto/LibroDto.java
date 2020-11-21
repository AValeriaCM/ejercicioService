package com.linea.dto;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linea.entity.Autor;

public class LibroDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Debe digitar un nombre")
	@Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 caracteres")
	@Column(name = "nombre", nullable = false, length=25)
	private String nombre;
	
	@Min(value = 5, message = "Debe tener minimo 5 paginas")
	@Column(name = "numero_paginas", nullable = false, length=25)
	private Integer numeroPaginas;
	
	@ManyToOne
	@JoinColumn(name = "id_autor", nullable = false, foreignKey = @ForeignKey(name = "FK_Autor_libro"))
	@JsonIgnore
	private Autor autor;

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

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	

}
