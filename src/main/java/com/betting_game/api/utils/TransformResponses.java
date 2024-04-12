package com.betting_game.api.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.betting_game.api.dtos.BetTransformedResponseDTO;
import com.betting_game.api.dtos.BetsByUserTransformedResponseDTO;
import com.betting_game.api.models.BetModel;
import com.betting_game.api.models.UserModel;

@Component
public class TransformResponses {

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

}
