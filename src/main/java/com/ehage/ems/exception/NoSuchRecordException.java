package com.ehage.ems.exception;

public class NoSuchRecordException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public NoSuchRecordException() {
		super();
	}
	
	public NoSuchRecordException(String message, String id) {
		super(message);
		this.id = id;
	}
	
	public NoSuchRecordException(String message, String id, Throwable th) {
		super(message, th);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
