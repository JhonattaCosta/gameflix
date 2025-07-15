package com.gameflix.service;

import com.gameflix.model.Category;
import com.gameflix.model.Game;
import com.gameflix.repository.GameRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepostiory repostiory;

    public Game saveGame(Game game){
        return repostiory.save(game);
    }

    public List<Game> findAllGames(){
        return repostiory.findAll();
    }

    public Optional<Game> findGameById(Long id){
        return repostiory.findById(id);
    }

    public List<Game> findByCategory(Long categoryId){
        return repostiory.findGamebyCategories(List.of(Category.builder().id(categoryId).build()));
    }

}
