package com.betting_game.api.exceptions;

public class BetConflictException extends RuntimeException{
	public BetConflictException(String message) {
		super(message);
	}
}
