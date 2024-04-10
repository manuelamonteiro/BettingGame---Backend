package com.betting_game.api.exceptions;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
		super(message);
	}
}
