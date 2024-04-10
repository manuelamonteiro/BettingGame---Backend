package com.betting_game.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BetDTO {

	@NotNull
	@Positive(message = "Must be greater than 0!")
	private Long betAmount;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String bet;

	@NotNull
	private Long userId;

	@NotNull
	private Long gameId;

}
