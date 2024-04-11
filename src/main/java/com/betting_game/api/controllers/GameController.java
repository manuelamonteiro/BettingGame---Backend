package com.betting_game.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betting_game.api.dtos.GameDTO;
import com.betting_game.api.models.GameModel;
import com.betting_game.api.services.GameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*") 
public class GameController {

	final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping
	public ResponseEntity<Object> getGames() {
		List<GameModel> games = gameService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(games);
	}

	@PostMapping
	public ResponseEntity<Object> createGame(@RequestBody @Valid GameDTO body) {
		GameModel game = gameService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(game);
	}

	@PutMapping("/end")
	public ResponseEntity<Object> endGames() {
		List<GameModel> games = gameService.update();
		return ResponseEntity.status(HttpStatus.OK).body(games);
	}

}
