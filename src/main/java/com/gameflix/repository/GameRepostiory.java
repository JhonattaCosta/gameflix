package com.gameflix.repository;

import com.gameflix.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepostiory extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g JOIN g.categories c WHERE c.id = :categoryId")
    List<Game> findByCategoryId(@Param("categoryId") Long categoryId);

}
