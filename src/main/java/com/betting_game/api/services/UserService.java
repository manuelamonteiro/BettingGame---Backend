package com.betting_game.api.services;

import org.springframework.stereotype.Service;

import com.betting_game.api.dtos.UserDTO;
import com.betting_game.api.exceptions.UserConflictException;
import com.betting_game.api.models.UserModel;
import com.betting_game.api.repositories.UserRepository;

@Service
public class UserService {
	final UserRepository userRepository;

	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserModel save(UserDTO dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new UserConflictException("User already exists!");
		}

		UserModel user = new UserModel(dto);
		return userRepository.save(user);
	}

}