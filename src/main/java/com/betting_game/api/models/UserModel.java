package com.betting_game.api.models;

import com.betting_game.api.dtos.UserDTO;

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
@Table(name = "tb_users")
public class UserModel {

	public UserModel(UserDTO dto) {
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.coins = 1000L;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) 
	private Long id;

	@Column(length = 150, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Long coins;

}