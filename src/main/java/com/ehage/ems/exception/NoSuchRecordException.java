package com.ehage.ems.exception;

public class NoSuchRecordException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoSuchRecordException() {
		super();
	}
	
	public NoSuchRecordException(String message) {
		super(message);
	}
	
	public NoSuchRecordException(String message, Throwable th) {
		super(message, th);
	}
	
}
