package com.gameflix.services;

import com.gameflix.entity.Game;
import com.gameflix.repository.GameRepostiory;
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
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestGameService {

    @Mock
    private GameRepostiory repository;

    @InjectMocks
    private GameService service;

    @Test
    public void testSaveGame(){
        Game gamefake = new Game(1L,
                "GTA 6",
                "Jogo de mundo aberto",
                LocalDate.of(2026,05,26));

        when(repository.save(gamefake)).thenReturn(gamefake);

        Game savedGame = service.saveGame(gamefake);

        assertEquals(gamefake, savedGame);
    }


    @Test
    public void testFindAllGames(){
        List<Game> games = Arrays.asList(
            new Game(1L,
                    "GTA 6",
                    "Jogo de mundo aberto",
                    LocalDate.of(2026,05,26)),
            new Game(1L,
                    "PUBG 2",
                    "Battle Royale",
                    LocalDate.of(2026,02,15))
        );
        when(repository.findAll()).thenReturn(games);

        List<Game> gamesfake = service.findAllGames();

        assertEquals(2,gamesfake.size());
    }
}
