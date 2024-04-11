package com.betting_game.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "*") 
public class UserController {

	final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<Object> getUsers() {
		List<UserModel> users = userService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		UserModel user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO body) {
		UserModel user = userService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid UserDTO body) {
		UserModel user = userService.login(body);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}