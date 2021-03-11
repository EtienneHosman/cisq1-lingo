package nl.hu.cisq1.lingo.words.domain.exception;

public class GameEndedException extends RuntimeException {
    public GameEndedException(){
        super("game ended");
    }
}
