package com.betting_game.api.exceptions;

public class UserConflictException extends RuntimeException{
	public UserConflictException(String message) {
		super(message);
	}
}
