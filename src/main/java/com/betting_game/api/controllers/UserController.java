package com.betting_game.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betting_game.api.dtos.UserDTO;
import com.betting_game.api.models.UserModel;
import com.betting_game.api.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO body) {
		UserModel user = userService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}