package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.Status;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(CiTestConfiguration.class)
class GameServiceIntergrationTest {

    @Autowired
    private SpringGameRepository repository;
    @Autowired
    private GameService gameService;
    @Autowired
    private WordService wordService;

    @BeforeEach
    void newGameService() {
        gameService = new GameService(wordService,repository);
    }

    @Test
    @DisplayName("Game can be started and finished")
    void playIntergration() {
        Game game = gameService.startGame();

        UUID id = game.getId();

        game = gameService.startRound(id);
        System.out.println(game.getRounds());
        Round currentRound = game.getRounds().get(0);
        String wordToGuess = currentRound.getWordToGuess();
        game = gameService.guess(id,wordToGuess);

        assertEquals(Status.ENDED, game.getStatus());
    }
}