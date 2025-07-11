package com.gameflix.repository;

import com.gameflix.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepostiory extends JpaRepository<Game, Long> {
}
