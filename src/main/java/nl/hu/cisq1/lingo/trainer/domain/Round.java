package nl.hu.cisq1.lingo.trainer.domain;


import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String wordToGuess = null;

    public Round(String word){
        this.wordToGuess = word;
    }

    public Round() {
    }

    public Feedback guessWord(String guess){
        String feedBackMarks = "";

        List<Mark> marks = new ArrayList<>();
        for(int i = 0; i < guess.length(); i++){
            if(guess.length() != this.wordToGuess.length()){
                marks.add(Mark.INVALID);
            } else if(this.wordToGuess.charAt(i) == guess.charAt(i)){
                marks.add(Mark.CORRECT);
                feedBackMarks += ".";
            } else {
                marks.add(Mark.ABSENT);
                feedBackMarks += this.wordToGuess.charAt(i);
            }
        }
        for(int i = 0; i < marks.size(); i++){
            if(marks.get(i).equals(Mark.ABSENT) && feedBackMarks.contains(String.valueOf(guess.charAt(i)))){
                marks.set(i, Mark.PRESENT);
            }
        }


        Feedback feedback = new Feedback(guess, marks);
        return feedback;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }
}
