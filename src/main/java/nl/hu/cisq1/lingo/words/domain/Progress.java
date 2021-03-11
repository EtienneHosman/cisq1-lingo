package nl.hu.cisq1.lingo.words.domain;

import java.util.ArrayList;
import java.util.List;

public class Progress {
    private int round = -1;
    private List<Feedback> feedbackList = null;

    public Progress(){
        this.round = 0;
        feedbackList = new ArrayList<>();
    }

    public void newRound(String wordToGuess){
        this.round++;
        feedbackList.clear();

        List<Mark> marks = new ArrayList<>();
        String guess = String.valueOf(wordToGuess.charAt(0));
        String firstLetter = String.valueOf(wordToGuess.charAt(0));

        for(int i = 0; i < wordToGuess.length(); i++){
            guess += ' ';
            firstLetter += '.';
            marks.add(Mark.ABSENT);
        }

        Feedback feedback = new Feedback(guess, marks);
        feedback.giveHint(firstLetter, guess);
        feedbackList.add(feedback);
    }
    public void addFeedback(Feedback feedback){
        feedbackList.add(feedback);
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public int getRound() {
        return round;
    }
}
