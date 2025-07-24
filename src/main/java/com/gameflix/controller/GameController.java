package com.gameflix.controller;


import com.gameflix.dto.GameDTO;
import com.gameflix.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("gamesflix/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;


    @PostMapping
    public ResponseEntity<GameDTO> saveGame(@RequestBody GameDTO gameDTO){
        GameDTO gameSaved = gameService.saveGame(gameDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gameSaved);
    }


    @GetMapping
    public ResponseEntity<List<GameDTO>> findAllGames(){
        List<GameDTO> games = gameService.findAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findGamesById(@PathVariable Long id){
        Optional<GameDTO> optGameDTO = gameService.findGameById(id);
        if (optGameDTO != null){
            return ResponseEntity.ok(optGameDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Game not found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> UpdateGamesById(@PathVariable Long id, @RequestBody GameDTO gameDTO){
        Optional<GameDTO> optGameDTO = gameService.findGameById(id);
        if (optGameDTO != null){
            gameService.updateGame(id, gameDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Game alterado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possivel alterar!");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<GameDTO>> findByCategory(@RequestParam Long category){
        List<GameDTO> gameByCategory = gameService.findByCategory(category);
        return ResponseEntity.ok(gameByCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteGameId(@PathVariable Long id){
        gameService.deleteById(id);
    }

}
