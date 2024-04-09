package com.betting_game.api.exceptions;

public class UserUnauthorizedException extends RuntimeException{
	public UserUnauthorizedException(String message) {
		super(message);
	}
}