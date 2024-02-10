package com.boardcamp.api.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.DTOs.GameDTO;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class GameIntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @AfterEach
    public void cleanUpDatabase() {
        gameRepository.deleteAll();
    }

    @Test
    void givenRequest_whenWantGetListGames_thenReturnList() {
        GameModel game1 = new GameModel(null, "name", "http://link", 1, 3000);
        GameModel game2 = new GameModel(null, "name1", "http://link", 1, 3500);
        GameModel game3 = new GameModel(null, "name2", "http://link", 1, 2000);
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);

        ResponseEntity<List<GameModel>> response = restTemplate.exchange(
                "/games",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GameModel>>() {
                });

        List<GameModel> games = response.getBody();

        assertEquals(3, gameRepository.count());
        assertEquals(3, games.size());
        assertEquals("name", games.get(0).getName());
        assertEquals("name1", games.get(1).getName());
        assertEquals("name2", games.get(2).getName());
    }

    @Test
    void givenRepeatGame_whenCreatingGame_thenThrowsError() {
        GameDTO gameDTO = new GameDTO("name", "http://link", 1, 3000);
        GameModel gameConflit = new GameModel(gameDTO);
        gameRepository.save(gameConflit);

        HttpEntity<GameDTO> body = new HttpEntity<>(gameDTO);

        ResponseEntity<String> response = restTemplate.exchange(
                "/games",
                HttpMethod.POST,
                body,
                String.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(1, gameRepository.count());
    }

    @Test
    void givenValidGame_whenCreatingGame_thenCreateGame() {
        GameDTO gameDTO = new GameDTO("name", "http://link", 1, 3000);

        HttpEntity<GameDTO> body = new HttpEntity<>(gameDTO);

        ResponseEntity<GameModel> response = restTemplate.exchange(
                "/games",
                HttpMethod.POST,
                body,
                GameModel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, gameRepository.count());
    }
}
