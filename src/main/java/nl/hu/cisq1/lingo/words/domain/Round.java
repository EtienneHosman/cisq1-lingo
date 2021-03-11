package nl.hu.cisq1.lingo.words.domain;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess = null;
    private int attempts = 0;

    public Round(String word){
        this.wordToGuess = word;
        this.attempts = 0;
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
        this.attempts++;
        return feedback;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public int getAttempts() {
        return attempts;
    }
}
