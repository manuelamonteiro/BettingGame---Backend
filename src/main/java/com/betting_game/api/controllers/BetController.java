package com.betting_game.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betting_game.api.dtos.BetDTO;
import com.betting_game.api.dtos.UserDTO;
import com.betting_game.api.models.BetModel;
import com.betting_game.api.services.BetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bets")
public class BetController {

	final BetService betService;

	public BetController(BetService betService) {
		this.betService = betService;
	}

	@GetMapping("user/{userId}")
	public ResponseEntity<Object> getBetsByUser(@PathVariable("userId") Long userId,
			@RequestBody @Valid UserDTO body) {
		List<BetModel> betsByUser = betService.findAllByUser(userId, body);
		return ResponseEntity.status(HttpStatus.OK).body(betsByUser);
	}

	@PostMapping
	public ResponseEntity<Object> createBet(@RequestBody @Valid BetDTO body) {
		BetModel bet = betService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(bet);
	}

}
