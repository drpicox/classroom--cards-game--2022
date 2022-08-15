package com.drpicox.game.util;

import java.util.function.Predicate;

public class Positions {

    public static Predicate<? super HasPosition> byPosition(int position) {
        return (hasPosition) -> hasPosition.getPosition() == position;
    }

}
