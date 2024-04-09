package com.betting_game.api.models;

import com.betting_game.api.dtos.GameDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "tb_games") 
public class GameModel {

	public GameModel(GameDTO dto) {
		this.name = dto.getName();
		this.result = dto.getResult();
	}

	public GameModel(String name, String result) {
		this.name = name;
		this.result = result;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE) 
	private Long id;

	@Column(nullable = false) 
	private String name;

	@Column(nullable = false)
	private String result;

}
