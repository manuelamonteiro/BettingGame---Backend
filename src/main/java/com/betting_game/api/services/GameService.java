package com.betting_game.api.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.betting_game.api.dtos.BetTransformedResponseDTO;
import com.betting_game.api.dtos.GameDTO;
import com.betting_game.api.exceptions.GameConflictException;
import com.betting_game.api.models.BetModel;
import com.betting_game.api.models.GameModel;
import com.betting_game.api.models.UserModel;
import com.betting_game.api.repositories.BetRepository;
import com.betting_game.api.repositories.GameRepository;
import com.betting_game.api.repositories.UserRepository;
import com.betting_game.api.utils.TransformResponses;

@Service
public class GameService {

	final UserRepository userRepository;
	final GameRepository gameRepository;
	final BetRepository betRepository;
	final TransformResponses transformResponses;

	GameService(GameRepository gameRepository, UserRepository userRepository, BetRepository betRepository,
			TransformResponses transformResponses) {
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.betRepository = betRepository;
		this.transformResponses = transformResponses;
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

	public List<GameModel> update() {

		List<GameModel> games = gameRepository.findAll();

		Random random = new Random();

		for (GameModel game : games) {
			String name = game.getName();
			String[] teams = name.split("");
			String[] options = { "draw", teams[0], teams[2] };
			int randomIndex = random.nextInt((options.length));

			game.setResult(options[randomIndex]);
		}

		List<GameModel> updatedGames = gameRepository.saveAll(games);

		this.updateUsersCoins();

		return updatedGames;
	}

	public void updateUsersCoins() {

		List<UserModel> users = userRepository.findAll();
		List<GameModel> games = gameRepository.findAll();

		for (UserModel user : users) {
			List<BetModel> betsByUser = betRepository.findAllByUserId(user.getId());
			List<BetTransformedResponseDTO> betsByUserTransformed = transformResponses
					.transform(betsByUser, user)
					.getBets();

			if (!betsByUserTransformed.isEmpty()) {
				for (BetTransformedResponseDTO betByUserTransformed : betsByUserTransformed) {
					for (GameModel game : games) {
						if (betByUserTransformed.getGameId() == game.getId() &&
								betByUserTransformed.getBet()
										.equals(game.getResult())) {
							user.setCoins(user.getCoins()
									+ (betByUserTransformed.getBetAmount() * 2));
							userRepository.save(user);
						}
					}
				}
			} else {
				continue;
			}
		}
	}

}