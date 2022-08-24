package com.drpicox.game.util;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RandomPickerService {

    private final Random random = new Random();

    public <T> T pick(String rollName, List<T> items) {
        var randomIndex = random.nextInt(items.size());
        var result = items.get(randomIndex);
        return result;
    }
}
