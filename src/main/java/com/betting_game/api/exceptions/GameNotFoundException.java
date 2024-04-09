package com.betting_game.api.exceptions;

public class GameNotFoundException extends RuntimeException{
	public GameNotFoundException(String message) {
		super(message);
	}
}