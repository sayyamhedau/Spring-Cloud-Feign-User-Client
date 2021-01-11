package com.app.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.dto.ApiException;
import com.app.exception.UserNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(UserNotFoundException ex) {
		ApiException apiException = new ApiException(ex.getMessage(), ex.getCause(), HttpStatus.NOT_FOUND,
				ZonedDateTime.now(ZoneId.systemDefault()));
		return ResponseEntity.ok(apiException);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException ex) {
		ApiException apiException = new ApiException(ex.getMessage(), ex.getCause(), HttpStatus.BAD_GATEWAY,
				ZonedDateTime.now(ZoneId.systemDefault()));
		return ResponseEntity.ok(apiException);
	}
}
