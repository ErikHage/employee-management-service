package com.ehage.ems.exception;

public class PersistenceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PersistenceException() {
		super();
	}
	
	public PersistenceException(String message) {
		super(message);
	}
	
	public PersistenceException(String message, Throwable th) {
		super(message, th);
	}
}
