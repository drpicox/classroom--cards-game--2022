package com.drpicox.game.card.api;

import com.drpicox.game.card.CardPositionService;
import com.drpicox.game.card.CardService;
import com.drpicox.game.game.api.GameResponse;
import com.drpicox.game.game.api.GameResponseFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game/cards")
public class CardController {

    private final CardService cardService;
    private final CardPositionService cardPositionService;
    private final GameResponseFactory gameResponseFactory;

    public CardController(CardService cardService, CardPositionService cardPositionService, GameResponseFactory gameResponseFactory) {
        this.cardService = cardService;
        this.cardPositionService = cardPositionService;
        this.gameResponseFactory = gameResponseFactory;
    }

    @PostMapping("/{cardId}/move")
    public GameResponse moveCard(@PathVariable String cardId, @RequestBody MoveForm moveForm) {
        var card = cardService.findById(cardId).get();
        cardPositionService.moveCard(card, moveForm.getPosition(), moveForm.getZindex());
        return gameResponseFactory.makeGameResponse();
    }

    @PostMapping("/{cardId}/discard")
    public GameResponse discardCard(@PathVariable String cardId) {
        var card = cardService.findById(cardId).get();
        cardService.discardCard(card);
        return gameResponseFactory.makeGameResponse();
    }
}
