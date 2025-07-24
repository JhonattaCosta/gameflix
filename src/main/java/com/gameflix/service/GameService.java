package com.gameflix.service;

import com.gameflix.dto.CategoryDTO;
import com.gameflix.dto.GameDTO;
import com.gameflix.mapper.CategoryMapper;
import com.gameflix.mapper.GameMapper;
import com.gameflix.model.Category;
import com.gameflix.model.Game;
import com.gameflix.repository.CategoryRepository;
import com.gameflix.repository.GameRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepostiory repostiory;
    private final CategoryService categoryService;
    private final GameMapper gameMapper;
    private final CategoryRepository categoryRepository;

    public GameDTO saveGame(GameDTO gameDTO) {
        gameDTO.setCategories(this.findCategories(gameDTO.getCategories()));
        Game gameSaved = gameMapper.map(gameDTO);
        gameSaved = repostiory.save(gameSaved);
        return gameMapper.map(gameSaved);
    }

    public List<GameDTO> findAllGames() {
        List<Game> games = repostiory.findAll();
        return games.stream()
                .map(GameMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<GameDTO> findGameById(Long id) {
        Optional<Game> gameById = repostiory.findById(id);
        return  gameById.map(GameMapper::map);
    }

    public List<CategoryDTO> findCategories(List<CategoryDTO> categories) {
        List<CategoryDTO> categoriesFound = new ArrayList<>();
        categories.forEach(category ->
                categoryService.findCategoryById(category.getId()).ifPresent(categoriesFound::add)
        );
        return categoriesFound;
    }

    public Optional<GameDTO> updateGame(Long gameId, GameDTO updateGameDTO) {
        Optional<Game> optGame = repostiory.findById(gameId);
        if (optGame.isPresent()) {
            Game game = optGame.get();
            game.setName(updateGameDTO.getName());
            game.setDescription(updateGameDTO.getDescription());
            game.setRating(updateGameDTO.getRating());
            game.setDateRelease(updateGameDTO.getDateRelease());

            List<Category> categories = updateGameDTO.getCategories()
                            .stream()
                            .map(CategoryMapper::map)
                            .collect(Collectors.toList());
            game.getCategories().clear();
            game.getCategories().addAll(categories);

            Game updatedGame = repostiory.save(game);

            GameDTO updatedGameDTO = GameMapper.map(updatedGame);
            return Optional.of(updatedGameDTO);
        }
        return Optional.empty();
    }

    public List<GameDTO> findByCategory(Long categoryId) {
        List<Game> games = repostiory.findByCategoryId(categoryId);
        return games.stream()
                .map(GameMapper::map)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repostiory.deleteById(id);
    }
}
