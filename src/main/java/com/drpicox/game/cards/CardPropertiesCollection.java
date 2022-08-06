package com.drpicox.game.cards;

import com.drpicox.game.propertiesSyrup.PropertiesSyrup;
import com.drpicox.game.propertiesSyrup.PropertiesSyrupCollection;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CardPropertiesCollection implements PropertiesSyrupCollection {

    private final Map<String, PropertiesSyrup> propertiesByName = new HashMap<>();

    @Override public String getCategory() {
        return "cards";
    }

    @Override
    public void add(PropertiesSyrup props) {
        var name = props.getString("name");
        propertiesByName.put(name, props);
    }

    public Optional<PropertiesSyrup> find(String name) {
        return Optional.ofNullable(propertiesByName.get(name));
    }
}
