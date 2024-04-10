package com.betting_game.api.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.betting_game.api.dtos.UserDTO;
import com.betting_game.api.exceptions.UserConflictException;
import com.betting_game.api.exceptions.UserNotFoundException;
import com.betting_game.api.models.UserModel;
import com.betting_game.api.repositories.UserRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class UserService {
	final UserRepository userRepository;
	final BCryptPasswordEncoder bCryptPasswordEncoder;

	UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

	public UserModel findById(@NonNull Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new UserNotFoundException("User not found!"));
	}

	public UserModel save(UserDTO dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new UserConflictException("User already exists!");
		}

		String encryptedPassword = bCryptPasswordEncoder.encode(dto.getPassword());

		UserModel user = new UserModel(dto.getUsername(), encryptedPassword);
		return userRepository.save(user);
	}

}