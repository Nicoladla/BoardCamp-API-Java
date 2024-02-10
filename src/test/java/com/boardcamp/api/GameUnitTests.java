package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.DTOs.GameDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.services.GameService;

@SpringBootTest
class GameUnitTests {

	@InjectMocks
	private GameService gameService;

	@Mock
	private GameRepository gameRepository;

	@Test
	void givenRepeatGame_whenCreatingGame_thenThrowsError() {
		GameDTO gameDTO = new GameDTO("name", "http://link", 1, 3000);

		doReturn(true).when(gameRepository).existsByName(any());

		ConflictException exception = assertThrows(
				ConflictException.class,
				() -> gameService.createGame(gameDTO));

		verify(gameRepository, times(0)).save(any());
		verify(gameRepository, times(1)).existsByName(any());
		assertNotNull(exception);
		assertEquals("The game already exists.", exception.getMessage());
	}

	@Test
	void givenValidGame_whenCreatingGame_thenCreateGame() {
		GameDTO gameDTO = new GameDTO("name", "http://link", 1, 3000);
		GameModel newGame = new GameModel(gameDTO);

		doReturn(false).when(gameRepository).existsByName(any());
		doReturn(newGame).when(gameRepository).save(any());

		GameModel result = gameService.createGame(gameDTO);

		verify(gameRepository, times(1)).save(any());
		verify(gameRepository, times(1)).existsByName(any());
		assertEquals(newGame, result);
	}

}
