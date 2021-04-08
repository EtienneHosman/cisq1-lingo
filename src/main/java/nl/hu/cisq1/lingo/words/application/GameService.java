package nl.hu.cisq1.lingo.words.application;


import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.words.domain.Game;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GameService {
    private WordService wordService;
    private SpringGameRepository gameRepository;


    public GameService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public Game getGame(UUID id){
        Optional<Game> optionalGame =gameRepository.findById(id);
        return optionalGame.get();
    }
    public Game startGame() {
        Game game = new Game();
        gameRepository.save(game);
        return game;
    }
    public Game startRound(UUID uuid){
        Game game = getGame(uuid);
        String word = wordService.provideRandomWord(5);

        game.startNewRound(word);

        gameRepository.save(game);
        return game;
    }

    public Game guess(UUID uuid, String attempt){
        Game game = getGame(uuid);
        game.guessWord(attempt);
        gameRepository.save(game);
        return game;
    }
}
