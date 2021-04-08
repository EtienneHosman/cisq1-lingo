package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.words.application.GameService;
import nl.hu.cisq1.lingo.words.domain.Game;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "game", method = RequestMethod.GET)
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping(value = "")
    public Game startGame() {
        Game game = gameService.startGame();
        return game;
    }

    @GetMapping(value = "/{uuid}")
    public Game getGame(@PathVariable UUID uuid) {
        Game game = gameService.getGame(uuid);
        return game;
    }

    @PostMapping(value = "/{uuid}/play")
    public Game startRound(@PathVariable UUID uuid){
        Game game = gameService.startRound(uuid);
        return game;
    }

    @PatchMapping(value = "/{uuid}/guess")
    public Game guess(@PathVariable UUID uuid, @RequestParam String guess){
        Game game = gameService.guess(uuid, guess);
        return game;
    }

}
