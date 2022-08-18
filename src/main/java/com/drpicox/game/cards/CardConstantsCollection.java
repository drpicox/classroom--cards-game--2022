package com.drpicox.game.cards;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.constants.ConstantsCollection;
import com.drpicox.game.constants.ConstantsLoader;
import com.drpicox.game.tags.TagFactory;
import com.drpicox.game.tags.TagFactorySettings;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

@Service
public class CardConstantsCollection {

    private final ConstantsCollection cardConstantsCollection;

    public CardConstantsCollection(ConstantsLoader constantsLoader) throws IOException, URISyntaxException {
        this.cardConstantsCollection = constantsLoader.loadCollection("cards");
    }

    public Constants getByName(String constantsNameValue) {
        return cardConstantsCollection.getByName(constantsNameValue);
    }

    public Collection<String> getAllNames() {
        return cardConstantsCollection.getAllNames();
    }
}
