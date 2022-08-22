package com.drpicox.game.util;

import org.springframework.stereotype.Service;

@Service
public class RandomPickerServiceMock extends RandomPickerService {
    @Override
    public <T> T pick(RandomList<T> items) {
        return items.get(0.0);
    }
}
