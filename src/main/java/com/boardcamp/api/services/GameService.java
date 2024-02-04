package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.GameDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findGames(){
        List<GameModel> games= gameRepository.findAll();

        return games;
    }

    public GameModel createGame(GameDTO gameDTO) {
        boolean gameExistByName = gameRepository.existsByName(gameDTO.getName());

        if (gameExistByName)
            throw new ConflictException("The game already exists.");

        GameModel game = new GameModel(gameDTO);
        gameRepository.save(game);

        return game;
    }
}
