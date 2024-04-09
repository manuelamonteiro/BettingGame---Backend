package com.betting_game.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ UserConflictException.class })
	public ResponseEntity<String> handleUserNameConflict(UserConflictException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}

	@ExceptionHandler({ UserUnauthorizedException.class })
	public ResponseEntity<String> handleUserUnauthorized(UserUnauthorizedException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
	}

	@ExceptionHandler({ UserForbiddenException.class })
	public ResponseEntity<String> handleUserNotFound(UserForbiddenException exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
	}

	@ExceptionHandler({ GameNotFoundException.class })
	public ResponseEntity<String> handleGameNotFound(GameNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler({ GameConflictException.class })
	public ResponseEntity<String> handleGameConflict(GameConflictException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}

}
