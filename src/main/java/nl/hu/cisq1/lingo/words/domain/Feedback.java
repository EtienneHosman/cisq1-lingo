package nl.hu.cisq1.lingo.words.domain;

import nl.hu.cisq1.lingo.words.domain.Mark;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Feedback implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String attempt;
    @ManyToOne
    private List<Mark> marks;
    private String hint;

    public Feedback(String attempt, List<Mark> marks)
    {
        this.attempt = attempt;
        this.marks = marks;
        this.hint = "";
    }

    public boolean isWordGuessed() {
        return marks.stream()
                .allMatch( mark -> mark == Mark.CORRECT);
    }

    public boolean isWordValid() {
        return  marks.stream()
                .noneMatch( mark -> mark == Mark.INVALID);
    }

    public String giveHint(String previousHint, String wordToGuess){
        for (int i = 0; i < this.marks.size(); i++){
            if(this.marks.get(i).equals(Mark.CORRECT) || previousHint.charAt(i) != '.' && previousHint.charAt(i) != '*'){
                this.hint += wordToGuess.charAt(i);
            } else if(this.marks.get(i).equals(Mark.PRESENT) ){
                this.hint += '*';
            }
            else if (this.marks.get(i).equals(Mark.ABSENT)){
                this.hint += '.';
            }else {
                this.hint += '.';
            }
        }
        return this.hint;
    }
    public List<Mark> getMarks() {
        return marks;
    }

    public String getHint() {
        return hint;
    }

    public Feedback(){
    }
}
