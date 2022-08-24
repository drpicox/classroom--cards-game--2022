package com.drpicox.game.util;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RandomPickerServiceMock extends RandomPickerService {

    private Map<String, List<Object>> mockedRolls = new TreeMap<>();

    @Override
    public <T> T pick(String rollName, List<T> items) {
        if (!mockedRolls.containsKey(rollName)) return items.get(0);

        var list = mockedRolls.get(rollName);
        var element = list.remove(0);
        if (list.isEmpty()) mockedRolls.remove(rollName);

        if (!items.contains(element)) throw new AssertionError(
            "The random for rollName '" + rollName + "' was mocked to be " + element + " but it is not present in the list.\n" +
            " - mocked element   : '" + element + "'\n" +
            " - possible elements: '" + listElements(items) + "'\n");

        return (T) element;
    }

    private <T> String listElements(List<T> items) {
        return items.stream().map(e -> e.toString()).collect(Collectors.joining("', '"));
    }

    public void mockPick(String rollName, Object value) {
        if (!mockedRolls.containsKey(rollName)) {
            mockedRolls.put(rollName, new LinkedList<>());
        }

        var list = mockedRolls.get(rollName);
        list.add(value);
    }


}
