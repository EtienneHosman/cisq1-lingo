package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class GameServiceTest {

    WordService wordService;
    SpringGameRepository gameRepository;
    GameService gameService;

    @BeforeEach
    void mockServices(){
        wordService = mock(WordService.class);
        gameRepository = mock(SpringGameRepository.class);
        gameService = new GameService(wordService, gameRepository);

        when(wordService.provideRandomWord(anyInt())).thenReturn("appel");

    }

    @Test
    @DisplayName("New game started")
    void startGame() {
        gameService.startGame();

        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    @DisplayName("New round started")
    void startRound() {
        Game game = new Game();

        when(gameRepository.findById(any())).thenReturn(Optional.of(game));

        gameService.startRound(game.getId());

        verify(wordService, times(1)).provideRandomWord(anyInt());
    }

    @Test
    void guess() {
        Game game = new Game();


        gameService.startGame();
        when(gameRepository.findById(any())).thenReturn(Optional.of(game));

        gameService.startRound(game.getId());
        gameService.guess(game.getId(), "hallo");

        assertEquals(game.getRounds().get(0).getWordToGuess(), "appel");
    }
}