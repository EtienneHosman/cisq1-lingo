package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameControllerTest {
    private static GameService gameService;
    private GameController gameController;

    @BeforeAll
    static void mockService() {
        gameService = mock(GameService.class);
    }
    @BeforeEach
    void newController(){
        this.gameController = new GameController(gameService);
    }

    @Test
    @DisplayName("New game is created")
    void startGame() {
        Game game = new Game();

        when(gameService.startGame()).thenReturn(game);

        assertNotNull(gameController.startGame());
    }

    @Test
    @DisplayName("When a game is created it exists")
    void getGame() {
        Game game = new Game();
        game.setId(UUID.randomUUID());

        when (gameService.getGame(game.getId())).thenReturn(game);


        assertNotNull(gameController.getGame(game.getId()));
    }

    @Test
    @DisplayName("a new round is started")
    void startRound() {
        Game game = new Game();
        game.setId(UUID.randomUUID());

        when(gameService.startRound(game.getId())).thenReturn(game);

        assertNotNull(gameController.startRound(game.getId()));

    }

    @Test
    @DisplayName("A guess can be made and progresses the game")
    void guess() {
        Game game = new Game();
        game.setId(UUID.randomUUID());
        game.startNewRound("hallo");

        when(gameService.guess(game.getId(),"broer")).thenReturn(game);

        assertNotNull(gameController.guess(game.getId(),"broer"));
    }
}