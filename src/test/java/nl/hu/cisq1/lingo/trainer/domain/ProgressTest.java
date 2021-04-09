package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgressTest {

    @Test
    @DisplayName("first hint should be first letter")
    void firstHint() {
        Progress progress = new Progress();
        progress.newRound("woord");
        assertEquals("w....", progress.getFeedbackList().get(0).getHint());
    }

    @Test
    @DisplayName("rounds are increased after starting new round")
    void newRoundCounter(){
        Progress progress = new Progress();
        assertEquals(0, progress.getRound());
        progress.newRound("woord");
        assertEquals(1, progress.getRound());
        progress.newRound("hallo");
        assertEquals(2, progress.getRound());
    }


}