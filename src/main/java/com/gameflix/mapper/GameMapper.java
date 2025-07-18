package com.gameflix.mapper;

import com.gameflix.dto.CategoryDTO;
import com.gameflix.dto.GameDTO;
import com.gameflix.model.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    public GameDTO map(Game game) {
        GameDTO gameDTO = new GameDTO();

        gameDTO.setId(game.getId());
        gameDTO.setName(game.getName());
        gameDTO.setDescription(game.getDescription());
        gameDTO.setRating(game.getRating());
        gameDTO.setDateRelease(game.getDateRelease());
        gameDTO.setCategories(
                game.getCategories().stream()
                        .map(CategoryMapper::map)
                        .collect(Collectors.toList())
        );
        return gameDTO;

    }

    public Game map(GameDTO gameDTO){
        Game game = new Game();

        game.setId(gameDTO.getId());
        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        game.setRating(gameDTO.getRating());
        game.setDateRelease(gameDTO.getDateRelease());
        game.setCategories(
                gameDTO.getCategories().stream()
                        .map(CategoryMapper::map)
                        .collect(Collectors.toList())
        );

        return game;
    }

}

