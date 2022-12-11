package com.mysoft.companyStatusFinder.exceptions;

public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 5863530988351426834L;

	public GlobalException() {
		super();
	}
	
	public GlobalException(String message) {
		super(message);
	}
	
	public GlobalException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GlobalException(Throwable cause) {
		super(cause);
	}
}
