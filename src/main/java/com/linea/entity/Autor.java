package com.linea.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Debe digitar un nombre")
	@Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 caracteres")
	@Column(name = "nombre", nullable = false, length=25)
	private String nombre;
	
	@NotNull(message = "Debe digitar un apellido")
	@Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 caracteres")
	@Column(name = "apellido", nullable = false, length=25)
	private String apellido;
	
	@NotNull(message = "Debe digitar una fecha")
	@Past(message = "La fecha debe ser menor a la fecha actual")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Libro> libro;
	
	@NotNull(message = "Direccion es obligatorio")
	@OneToOne(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;


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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
}
