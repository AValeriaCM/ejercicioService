package com.linea.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.linea.dto.ErrorDTO;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ErrorDTO> ModelNotFoundExceptionHandler(ModelNotFoundException ex, HttpServletRequest request){
		ErrorDTO error = new ErrorDTO("404", "NOT_FOUND", ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.NOT_FOUND);	
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.toString(), status.getReasonPhrase(), ex.getBindingResult().getFieldError().getDefaultMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error, status);
		
	}

	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.toString(), status.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error, status);
	}


	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.toString(), status.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error, status);
	}


	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.toString(), status.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error, status);
	}


	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.toString(), status.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error, status);
	}


	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ErrorDTO error = new ErrorDTO(status.toString(), status.getReasonPhrase(), ex.getBindingResult().getFieldError().getDefaultMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error, status);
	}
	
	
	@ExceptionHandler(ArgumentRequiredException.class)
    public final ResponseEntity<ErrorDTO> ArgumentRequiredExceptionHandler (ArgumentRequiredException ex,
            HttpServletRequest request){
       
        ErrorDTO error = new ErrorDTO("BAD_REQUEST", "400", ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);           
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	 public final ResponseEntity<ErrorDTO> EmptyResultDataAccessExceptionHandler (EmptyResultDataAccessException ex,
			 HttpServletRequest request){
	       
	        ErrorDTO error = new ErrorDTO("BAD_REQUEST", "400", ex.getMessage(), request.getRequestURI());
	        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);           
	    }
	
	@ExceptionHandler(ObjectItsCreatedException.class)
	public final ResponseEntity<ErrorDTO> ObjectItsCreatedExceptionHandler (ObjectItsCreatedException ex,
			HttpServletRequest request){
		ErrorDTO error = new ErrorDTO("BAD_REQUEST", "400", ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST); 
	}
}
