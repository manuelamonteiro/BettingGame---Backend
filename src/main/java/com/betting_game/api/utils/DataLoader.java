package com.betting_game.api.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.betting_game.api.models.GameModel;
import com.betting_game.api.repositories.GameRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

	private final GameRepository gameRepository;

	public DataLoader(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public void run(ApplicationArguments args) {
		List<GameModel> games = Arrays.asList(
				new GameModel("AxB", "waiting"),
				new GameModel("AxC", "waiting"),
				new GameModel("AxD", "waiting"),
				new GameModel("BxC", "waiting"),
				new GameModel("BxD", "waiting"),
				new GameModel("CxD", "waiting"));

		for (GameModel game : games) {
			if (!gameRepository.existsByName(game.getName())) {
				gameRepository.save(game);
			}
		}
	}

}