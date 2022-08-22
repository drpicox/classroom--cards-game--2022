package com.drpicox.game.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomPickerService {

    private final Random random = new Random();

    public <T> T pick(RandomList<T> items) {
        return items.get(random.nextDouble());
    }
}
