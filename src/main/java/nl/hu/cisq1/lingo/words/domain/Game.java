package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.exception.GameEndedException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int guesses = 0;
    private List<Round> rounds = new ArrayList<>();
    private Progress progress = new Progress();
    private Status status = Status.AWAIT;

    public void startNewRound(String wordToGuess) {
        rounds.add(new Round(wordToGuess));
        this.status = Status.STARTED;
        progress.newRound(wordToGuess);
    }

    public void guessWord(String guess){
        if(!this.status.equals(Status.ENDED)){
            Round round = this.rounds.get(rounds.size() -1);
            String hint = this.progress.getFeedbackList().get(this.progress.getFeedbackList().size() - 1).getHint();
            Feedback feedback = round.guessWord(guess);
            feedback.giveHint(hint, round.getWordToGuess());
            this.progress.addFeedback(feedback);
            this.guesses++;

            if(feedback.isWordGuessed()){
                this.status = Status.ENDED;
                this.guesses = 0;
            } else if(this.guesses < 5) {
                this.status = Status.INPROGRESS;
            } else {
                this.status = Status.ENDED;
                this.guesses = 0;
            }
        } else {
            throw new GameEndedException();
        }

    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Progress getProgress() {
        return progress;
    }

    public Status getStatus() {
        return status;
    }
}
