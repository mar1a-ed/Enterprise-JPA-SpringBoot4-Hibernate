package com.dev.enterprise.resources.exceptions;

public class DatabasePermissionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DatabasePermissionException(Object id) {
		super("Error. There's information in another table.");
	}
	
	
}
