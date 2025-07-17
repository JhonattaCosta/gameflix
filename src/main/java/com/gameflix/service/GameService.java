package com.gameflix.service;

import com.gameflix.model.Category;
import com.gameflix.model.Game;
import com.gameflix.repository.GameRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepostiory repostiory;
    private final CategoryService categoryService;

    public Game saveGame(Game game){
        return repostiory.save(game);
    }

    public List<Game> findAllGames(){
        return repostiory.findAll();
    }

    public Optional<Game> findGameById(Long id){
        return repostiory.findById(id);
    }

    public List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category ->
                categoryService.findCategoryById(category.getId()).ifPresent(categoriesFound::add)
        );
        return categoriesFound;
    }

    public Optional<Game> updateGame(Long gameId, Game updateGame){
        Optional<Game> optGame = repostiory.findById(gameId);
        if (optGame.isPresent()){
            List<Category> categories = this.findCategories(updateGame.getCategories());

            Game game = optGame.get();
            game.setName(updateGame.getName());
            game.setDescription(updateGame.getDescription());
            game.setRating(updateGame.getRating());
            game.setDateRelease(updateGame.getDateRelease());

            game.getCategories().clear();
            game.getCategories().addAll(categories);

            repostiory.save(game);
            return Optional.of(game);
        }
        return Optional.empty();
    }

    public List<Game> findByCategory(Long categoryId){
        return repostiory.findGamebyCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public void deleteById(Long id){
        repostiory.deleteById(id);
    }
}
