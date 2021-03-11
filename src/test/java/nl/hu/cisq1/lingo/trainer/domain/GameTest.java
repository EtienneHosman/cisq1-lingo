package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Game;
import nl.hu.cisq1.lingo.words.domain.Status;
import nl.hu.cisq1.lingo.words.domain.exception.GameEndedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("Game status must be ended when word is guessed")
    void wordIsGuessed(){
        Game game = new Game();
        game.startNewRound("woord");
        game.guessWord("woord");
        assertEquals(Status.ENDED, game.getStatus());
    }

    @Test
    @DisplayName("game status must be AWAIT when started")
    void gameStatusAwait(){
        Game game = new Game();
        assertEquals(Status.AWAIT, game.getStatus());
    }

    @Test
    @DisplayName("game status must be Started when game is started")
    void gameStatusStarted(){
        Game game = new Game();
        game.startNewRound("woord");
        assertEquals(Status.STARTED, game.getStatus());
    }

    @Test
    @DisplayName("game status must be inprogress when there is a wrong guess")
    void gameStatusInProgress(){
        Game game = new Game();
        game.startNewRound("woord");
        game.guessWord("hallo");
        assertEquals(Status.INPROGRESS, game.getStatus());
    }

    @Test
    @DisplayName("Throw game ended exception when it goes over 5 guesses")
    void gameEnded(){
        Game game = new Game();
        game.startNewRound("woord");
        game.guessWord("hadlo");
        game.guessWord("hablo");
        game.guessWord("hawlo");
        game.guessWord("hatlo");
        game.guessWord("hatlo");

        assertThrows(GameEndedException.class, () -> game.guessWord("woord"));
    }
}