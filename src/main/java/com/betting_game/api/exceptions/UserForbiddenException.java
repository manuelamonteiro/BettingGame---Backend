package com.betting_game.api.exceptions;

public class UserForbiddenException extends RuntimeException{
	public UserForbiddenException(String message) {
		super(message);
	}
}
