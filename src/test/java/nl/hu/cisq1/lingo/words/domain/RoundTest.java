package nl.hu.cisq1.lingo.words.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    private static Stream<Arguments> giveFeedbackExamples(){
        return Stream.of(
                Arguments.of("cheat", "chicken", List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID)),
                Arguments.of("cheat", "chart", List.of(Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT)),
                Arguments.of("cheat", "cheat", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT))
        );
    }

    @ParameterizedTest
    @MethodSource("giveFeedbackExamples")
    @DisplayName("guess must have correct feedback")
    void guessHasFeedback(String word, String guess, List<Mark> result){
        Round round = new Round(word);
        Feedback feedback = round.guessWord(guess);
        assertEquals(result, feedback.getMarks());
    }
}

