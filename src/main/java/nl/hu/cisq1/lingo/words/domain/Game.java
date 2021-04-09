package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.exception.GameEndedException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private int guesses = 0;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();
    @Column
    private Progress progress = new Progress();
    @Column
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
    public UUID getId() {
        return id;
    }
    public void setId(UUID id){
        this.id = id;
    }
}
