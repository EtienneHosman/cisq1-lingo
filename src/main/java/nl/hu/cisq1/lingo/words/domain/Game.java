package nl.hu.cisq1.lingo.words.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Round> rounds = new ArrayList<>();
    private Progress progress = new Progress();
    private Status status = Status.AWAIT;

    public void startNewRound(String wordToGuess) {
        rounds.add(new Round(wordToGuess));
        this.status = Status.STARTED;
        progress.newRound(wordToGuess);
    }

    public void guessWord(String guess){
        Round round = this.rounds.get(rounds.size() -1);
        String hint = this.progress.getFeedbackList().get(this.progress.getFeedbackList().size() - 1).getHint();
        Feedback feedback = round.guessWord(guess);
        feedback.giveHint(hint, round.getWordToGuess());
        this.progress.addFeedback(feedback);

        if(feedback.isWordGuessed()){
            this.status = Status.ENDED;
        } else {
            this.status = Status.INPROGRESS;
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
