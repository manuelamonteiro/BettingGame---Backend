package com.betting_game.api.services;

import java.util.List;
import java.util.Random;

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

	public List<GameModel> update() {

		List<GameModel> games = gameRepository.findAll();

		Random random = new Random();

		for (GameModel game : games) {
			String name = game.getName();
			String[] teams = name.split("x");
			String[] options = { "draw", teams[0], teams[1] };
			int randomIndex = random.nextInt((options.length));

			game.setResult(options[randomIndex]);
		}

		List<GameModel> updatedGames = gameRepository.saveAll(games);

		//após realizar a atualização dos jogos deve atualizar a quantidade de moedas de cada jogador a partir do resultado e aposta
		//primeiro deve pegar essa lista de resultados atualizados
		//deve pegar todas as apostas dos usuários
		//deve percorrer a lista de apostas dos usuários
		//deve verificar de cada usuário os jogos participantes comparando o resultado final com a aposta (talvez deva percorrer novamente?)
		//se o resultado for igual deve dobrar o valor apostado e adc ao saldo da conta do jogador
		//se o resultado não for igual nada acontece

		return updatedGames;
	}
}