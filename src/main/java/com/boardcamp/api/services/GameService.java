package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.DTOs.GameDTO;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameModel createGame(GameDTO gameDTO){
        boolean gameExistByName= gameRepository.existsByName(gameDTO.getName());

        //if(gameExistByName) throw 
    }
}
