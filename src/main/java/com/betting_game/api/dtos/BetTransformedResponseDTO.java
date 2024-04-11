package com.betting_game.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BetTransformedResponseDTO {

	public BetTransformedResponseDTO(){}

	@NotNull
	private Long id;

	@NotNull
	private Long gameId;

	@NotNull
	@Positive
	private Long betAmount;

	@NotBlank
	private String bet;
	
}
