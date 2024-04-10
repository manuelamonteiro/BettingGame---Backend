package com.betting_game.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betting_game.api.models.BetModel;

@Repository
public interface BetRepository extends JpaRepository<BetModel, Long> {
	List<BetModel> findAllByUserId(Long userId);
}