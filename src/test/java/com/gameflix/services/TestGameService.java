package com.gameflix.services;

import com.gameflix.model.Category;
import com.gameflix.model.Game;
import com.gameflix.repository.CategoryRepository;
import com.gameflix.repository.GameRepostiory;
import com.gameflix.service.CategoryService;
import com.gameflix.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestGameService {

    @Mock
    private GameRepostiory repository;
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private GameService service;

    List<Category> categories = Arrays.asList(
            new Category(1L, "Ação"),
            new Category(2L, "Terceira-Pessoa")
    );

    @Test
    public void testSaveGame(){
        Game gamefake = new Game(
                1L,
                "GTA 6",
                "Jogo de mundo aberto",
                4,
                LocalDate.of(2026, 06, 26),
                categories
        );

        when(repository.save(gamefake)).thenReturn(gamefake);

        Game savedGame = service.saveGame(gamefake);

        assertEquals(gamefake, savedGame);
    }

    @Test
    public void testFindAllGames(){
        List<Game> games = Arrays.asList(
                new Game(
                        1L,
                        "GTA 6",
                        "Jogo de mundo aberto",
                        4,
                        LocalDate.of(2026, 06, 26),
                        categories
                ),
                new Game(
                        1L,
                        "PUBG",
                        "BattleGround",
                        4,
                        LocalDate.of(2026, 06, 26),
                        categories
                ));
        when(repository.findAll()).thenReturn(games);

        List<Game> gamesfake = service.findAllGames();

        assertEquals(2,gamesfake.size());
        assertEquals(games,gamesfake);
    }

    @Test
    public void testFindGameById(){
        Optional<Game> gamefake = Optional.of(new Game(
                1L,
                "GTA 6",
                "Jogo de mundo aberto",
                4,
                LocalDate.of(2026, 06, 26),
                categories
        ));

        when(repository.findById(1L)).thenReturn(gamefake);

        Optional<Game> findIdGame = service.findGameById(1L);

        assertTrue(findIdGame.isPresent());
        assertEquals(findIdGame.get(), gamefake.get());
    }

    @Test
    public void testFindGameByCategory(){
        List<Category> expectedCategories = Arrays.asList(
                new Category(1L, "Ação")
        );
        List<Game> games = Arrays.asList(
                new Game(
                        1L,
                        "GTA 6",
                        "Jogo de mundo aberto",
                        4,
                        LocalDate.of(2026, 06, 26),
                        categories
                ),
                new Game(
                        1L,
                        "PUBG",
                        "BattleGround",
                        4,
                        LocalDate.of(2026, 06, 26),
                        categories
                ));
        when(repository.findGamebyCategories(anyList())).thenReturn(games);

        List<Game> gamesFind = service.findByCategory(1L);

       assertEquals(2, gamesFind.size());
       assertTrue(gamesFind.containsAll(games));
    }

    @Test
    public void testUpdateGames(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Ação"));
        categories.add(new Category(2L, "Terceira-Pessoa"));
        Game gameFake = new Game(
                1L,
                "GTA 61",
                "Jogo de mundo aberto",
                4,
                LocalDate.of(2026, 06, 26),
                categories
        );
        Game gameUpdate = new Game(
                1L,
                "GTA 6",
                "Jogo de mundo aberto",
                4,
                LocalDate.of(2026, 06, 26),
                categories
        );


        when(repository.findById(1L)).thenReturn(Optional.of(gameFake));
        when(repository.save(gameFake)).thenReturn(gameFake);

        Optional<Game> result = service.updateGame(1L, gameUpdate);

        assertTrue(result.isPresent());
        assertEquals("GTA 6", result.get().getName());


    }

    @Test
    public void testDeleteGame(){
        Game gamefake = new Game(
                1L,
                "GTA 6",
                "Jogo de mundo aberto",
                4,
                LocalDate.of(2026, 06, 26),
                categories
        );
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }
}
