package com.linea.dto;

import java.time.LocalTime;

public class ErrorDTO {
	
	private LocalTime timestamp;
	
	private String status;
	
	private String error;
	
	private String message;
	
	private String path;

	public ErrorDTO(String status, String error, String message, String path) {
		super();
		this.timestamp = LocalTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
