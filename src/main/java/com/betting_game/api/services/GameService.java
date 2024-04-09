package com.betting_game.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betting_game.api.dtos.GameDTO;
import com.betting_game.api.exceptions.GameConflictException;
import com.betting_game.api.models.GameModel;
import com.betting_game.api.repositories.GameRepository;

@Service
public class GameService {
	final GameRepository gameRepository;

	GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public List<GameModel> findAll() {
		return gameRepository.findAll();
	}

	public GameModel save(GameDTO dto) {
		if (gameRepository.existsByName(dto.getName())) {
			throw new GameConflictException("Game already exists!");
		}

		GameModel game = new GameModel(dto);
		return gameRepository.save(game);
	}
}