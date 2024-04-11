package com.betting_game.api.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BetsByUserTransformedResponseDTO {

	public BetsByUserTransformedResponseDTO() {}

	@NotBlank
	private String username;

	@NotNull
	@Positive
	private Long coins;
	
	private List<BetTransformedResponseDTO> bets;
	
}
