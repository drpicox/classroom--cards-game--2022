package com.drpicox.game.util;

import java.util.function.Predicate;

public class Ids {

    public static Predicate<HasId> byId(String id) {
        return (hasId) -> hasId.getId().equals(id);
    }

}
