package com.gameflix.service;

import com.gameflix.entity.Game;
import com.gameflix.repository.GameRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepostiory repostiory;

    public List<Game> findAllGames(){
        return repostiory.findAll();
    }

}
