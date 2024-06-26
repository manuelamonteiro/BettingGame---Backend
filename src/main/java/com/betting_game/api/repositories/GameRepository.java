package com.betting_game.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betting_game.api.models.GameModel;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {
	boolean existsByName(String name);
}