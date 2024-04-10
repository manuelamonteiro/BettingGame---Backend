package com.betting_game.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betting_game.api.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	boolean existsByUsername(String username);
	UserModel findByUsername(String username);
}
