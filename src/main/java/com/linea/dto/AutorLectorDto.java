package com.linea.dto;

import java.io.Serializable;


public class AutorLectorDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AutorDto autor;
	
	private LectorDTO lector;
	 
	private String infoAdicional;

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}

	public LectorDTO getLector() {
		return lector;
	}

	public void setLector(LectorDTO lector) {
		this.lector = lector;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInforAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

		
}
