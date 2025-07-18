package com.gameflix.repository;

import com.gameflix.model.Category;
import com.gameflix.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepostiory extends JpaRepository<Game, Long> {

    List<Game> findByCategoriesIn(List<Category> categories);

}
