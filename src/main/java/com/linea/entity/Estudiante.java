package com.linea.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estudiante")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Debe digitar una cedula")
	@Size(min = 3, max = 20, message = "Cedula debe tener entre 3 y 20 caracteres")
	@Column(name = "cedula", nullable = false, length = 20, unique = true)
	private String cedula;
	
	@NotNull(message = "Debe digitar un nombre")
	@Size(min = 3, max = 20, message = "Nombre debe tener entre 3 y 20 caracteres")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	@NotNull(message = "Debe digitar un apellido")
	@Size(min = 3, max = 20, message = "Apellido debe tener entre 3 y 20 caracteres")
	@Column(name = "apellido", nullable = false, length = 20)
	private String apellido;
	
	@NotNull(message = "Debe digitar una carrera")
	@Size(min = 3, max = 20, message = "Carrera debe tener entre 3 y 20 caracteres")
	@Column(name = "carrera", nullable = false, length = 20)
	private String carrera;
	
	
	@Size(min = 3, max = 20, message = "El correo debe tener entre 3 y 20 caracteres")
	@Email(message = "El correo electronico debe ser valido")
	@Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$", message = "Email invalido")
	@Column(name = "correo", length = 20)
	private String correo;
	
    @Temporal(TemporalType.DATE)
	@Column(name = "fechaNacimiento",updatable = false, length = 20)
	private Calendar fechaNacimiento;
	
	/*
	 * Constructor
	 */

	public Estudiante() {
		super();
	}

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

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}
