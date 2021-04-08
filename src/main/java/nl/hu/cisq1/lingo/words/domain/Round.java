package nl.hu.cisq1.lingo.words.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Round {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String wordToGuess = null;

    public Round(String word){
        this.wordToGuess = word;
    }

    public Round() {
    }

    public Feedback guessWord(String guess){

        List<Mark> marks = new ArrayList<>();
        for(int i = 0; i < guess.length(); i++){
            if(guess.length() != this.wordToGuess.length()){
                marks.add(Mark.INVALID);
            } else if(this.wordToGuess.charAt(i) == guess.charAt(i)){
                marks.add(Mark.CORRECT);
            } else if(this.wordToGuess.contains(String.valueOf(guess.charAt(i)))){
                marks.add(Mark.PRESENT);
            } else {
                marks.add(Mark.ABSENT);
            }
        }

        Feedback feedback = new Feedback(guess, marks);
        return feedback;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }
}
