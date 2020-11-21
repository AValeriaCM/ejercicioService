package com.linea.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@NotNull(message = "Documento es requerido")
	@Size(min = 7, max = 19, message = "Longitud invalida")
	@Column(name = "documento", nullable = false, unique = true, length = 20)
	private String documento;
	
	@NotNull(message = "Nombre es requerido")
	@Size(min = 3, max = 24, message = "Longitud invalida")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	@NotNull(message = "Apellido es requerido")
	@Size(min = 3, max = 24, message = "Longitud invalida")
	@Column(name = "apellido", nullable = false, length = 20)
	private String apellido;
	
	@NotNull(message = "Nick es requerido")
	@Size(min = 3, max = 30, message = "Longitud invalida")
	@Column(name = "nick", nullable = false,  length = 20, unique = true)
	private String nick;
	
	@NotNull(message = "Clave es requerido")
	@Column(columnDefinition = "TEXT", name = "clave")
	private String clave;
	
	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "idRol", foreignKey = @ForeignKey(name = "FK_rol"))
	private Rol rol;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
}
