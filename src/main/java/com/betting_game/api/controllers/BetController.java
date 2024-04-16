package com.betting_game.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betting_game.api.dtos.BetDTO;
import com.betting_game.api.dtos.BetsByUserTransformedResponseDTO;
import com.betting_game.api.dtos.UserDTO;
import com.betting_game.api.models.BetModel;
import com.betting_game.api.services.BetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bets")
@CrossOrigin(origins = "*") 
public class BetController {

	final BetService betService;

	public BetController(BetService betService) {
		this.betService = betService;
	}

	@PostMapping("user/{userId}")
	public ResponseEntity<Object> getBetsByUser(@PathVariable("userId") Long userId,
			@RequestBody @Valid UserDTO body) {
				BetsByUserTransformedResponseDTO betsByUser = betService.findAllByUser(userId, body);
		return ResponseEntity.status(HttpStatus.OK).body(betsByUser);
	}

	@PostMapping
	public ResponseEntity<Object> createBet(@RequestBody @Valid BetDTO body) {
		BetModel bet = betService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(bet);
	}

}
