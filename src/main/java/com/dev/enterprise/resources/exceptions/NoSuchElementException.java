package com.dev.enterprise.resources.exceptions;

public class NoSuchElementException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NoSuchElementException(Object id) {
		super("Resource not found!");
	}
	
}
