package com.betting_game.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.betting_game.api.dtos.BetDTO;
import com.betting_game.api.dtos.BetTransformedResponseDTO;
import com.betting_game.api.dtos.BetsByUserTransformedResponseDTO;
import com.betting_game.api.dtos.UserDTO;
import com.betting_game.api.exceptions.BetConflictException;
import com.betting_game.api.exceptions.GameNotFoundException;
import com.betting_game.api.exceptions.UserConflictException;
import com.betting_game.api.exceptions.BetBadRequestException;
import com.betting_game.api.exceptions.UserNotFoundException;
import com.betting_game.api.exceptions.UserUnauthorizedException;
import com.betting_game.api.models.BetModel;
import com.betting_game.api.models.GameModel;
import com.betting_game.api.models.UserModel;
import com.betting_game.api.repositories.BetRepository;
import com.betting_game.api.repositories.GameRepository;
import com.betting_game.api.repositories.UserRepository;

@Service
public class BetService {

	final UserRepository userRepository;
	final GameRepository gameRepository;
	final BetRepository betRepository;
	final BCryptPasswordEncoder bCryptPasswordEncoder;

	BetService(UserRepository userRepository, GameRepository gameRepository, BetRepository betRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.betRepository = betRepository;
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
	}

	public UserModel verifyLogin(String username, String password) {
		UserModel userByUsername = userRepository.findByUsername(username);

		if (userByUsername == null) {
			throw new UserNotFoundException("User not found!");
		}

		if (!bCryptPasswordEncoder.matches(password, userByUsername.getPassword())) {
			throw new UserUnauthorizedException("User unauthorized!");
		}

		return userByUsername;
	}

	public BetsByUserTransformedResponseDTO transform(List<BetModel> body, UserModel user) {
		BetsByUserTransformedResponseDTO response = new BetsByUserTransformedResponseDTO();
		List<BetTransformedResponseDTO> bets = new ArrayList<>();

		response.setUsername(user.getUsername());
		response.setCoins(user.getCoins());

		if (!body.isEmpty()) {
			for (BetModel betBody : body) {
				BetTransformedResponseDTO bet = new BetTransformedResponseDTO();
				bet.setId(betBody.getId());
				bet.setBetAmount(betBody.getBetAmount());
				bet.setBet(betBody.getBet());
				bet.setGameId(betBody.getGame().getId());

				bets.add(bet);
			}
		}

		response.setBets(bets);

		return response;
	}

	public BetsByUserTransformedResponseDTO findAllByUser(Long userId, UserDTO dto) {
		UserModel user = this.verifyLogin(dto.getUsername(), dto.getPassword());

		if (!user.getId().equals(userId)) {
			throw new UserConflictException("Something went wrong!");
		}

		List<BetModel> bets = betRepository.findAllByUserId(userId);
		BetsByUserTransformedResponseDTO response = this.transform(bets, user);

		return response;
	}

	public BetModel save(BetDTO dto) {

		this.verifyLogin(dto.getUsername(), dto.getPassword());

		UserModel user = userRepository.findById(dto.getUserId()).orElseThrow(
				() -> new UserNotFoundException("User not found!"));

		GameModel game = gameRepository.findById(Objects.requireNonNull(dto.getGameId())).orElseThrow(
				() -> new GameNotFoundException("Game not found!"));

		List<BetModel> betExists = betRepository.findAllByUserId(dto.getUserId());

		for (BetModel bet : betExists) {
			if (bet.getGame().getId().equals(dto.getGameId())) {
				throw new BetConflictException("Bet already exists!");
			}
		}

		if (user.getCoins() < dto.getBetAmount()) {
			throw new BetBadRequestException("User does not have enough coins for the bet!");
		}

		user.setCoins(user.getCoins() - dto.getBetAmount());

		BetModel bet = new BetModel(dto, user, game);
		BetModel savedBet = betRepository.save(bet);

		if (savedBet.getId() == null) {
			user.setCoins(user.getCoins() + dto.getBetAmount());
			throw new BetBadRequestException("Failed to save the bet!");
		}

		return savedBet;

	}

}
