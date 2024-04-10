package com.betting_game.api.exceptions;

public class BetBadRequestException extends RuntimeException{
	public BetBadRequestException(String message) {
		super(message);
	}
}