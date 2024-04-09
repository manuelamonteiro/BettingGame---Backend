package com.betting_game.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {

	@NotBlank
	private String name;

	@NotBlank
	private String result;

}