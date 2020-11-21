package com.linea.exception;

public class ObjectItsCreatedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObjectItsCreatedException(String mensaje) {        
        super(mensaje);
    }
}
