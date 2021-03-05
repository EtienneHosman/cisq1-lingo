package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Feedback;
import nl.hu.cisq1.lingo.words.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    static Stream<Arguments> provideHintExamples(){
        return Stream.of(
            Arguments.of("w....", "words", "word", List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID), "w...."),
            Arguments.of("w....", "words", "water", List.of(Mark.CORRECT,Mark.ABSENT,Mark.ABSENT,Mark.ABSENT, Mark.PRESENT), "w...*"),
            Arguments.of("w...*", "words", "worst", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.ABSENT), "wor*."),
            Arguments.of("wor*.", "words", "words", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "words")
        );
    }
    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is incorrect if not all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.INVALID, Mark.ABSENT, Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("guess is invalid if it isn't the same length as word")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("telang", List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
        assertFalse(feedback.isWordValid());
    }
    @Test
    @DisplayName("guess is valid if it is the same length as word")
    void guessIsValid(){
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordValid());
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("guess gives correct hint")
    void guessHasHint(String previousHint, String wordToGuess, String guess, List<Mark> marks, String hint){
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(hint, feedback.giveHint(previousHint, wordToGuess));
    }

}