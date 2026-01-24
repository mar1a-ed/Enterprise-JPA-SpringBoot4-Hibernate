package com.dev.enterprise.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dev.enterprise.resources.dtos.StandardError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	//controla a saída de aviso de erro
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> noSuchElement(NoSuchElementException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError sterr = new StandardError(Instant.now(), status.value(), error, "Resource not found!", request.getRequestURI());
		return ResponseEntity.status(status).body(sterr);
	}
	
	@ExceptionHandler(DatabasePermissionException.class)
	public ResponseEntity<StandardError> databaseError(DatabasePermissionException e, HttpServletRequest request){
		String error = "Database Permission Denied";
		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
		StandardError sterr = new StandardError(Instant.now(), status.value(), error, "Remove other resources!", request.getRequestURI());
		return ResponseEntity.status(status).body(sterr);
	}
}
