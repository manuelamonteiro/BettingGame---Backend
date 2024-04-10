package com.betting_game.api.models;

import com.betting_game.api.dtos.BetDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "tb_bets") 
public class BetModel {

	public BetModel(BetDTO dto, UserModel user, GameModel game){
		this.betAmount = dto.getBetAmount();
		this.bet = dto.getBet();
		this.user = user;
		this.game = game;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE) 
	private Long id;
	
	@Column(nullable = false)
	private Long betAmount; 

	@Column(nullable = false)
	private String bet;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private GameModel game;

}
