package com.drpicox.game.synthetics;

import com.drpicox.game.util.RandomPickerService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class RandomPickerServiceTest {

    @Test public void test_random_picker_one_of_two() {
        var picker = new RandomPickerService();
        var list = new ArrayList<String>();
        list.add("first");
        list.add("second");

        var times = 1000;
        var results = pickMany(picker, list, times);

        assertThat(results).containsKey("first");
        assertThat(results).containsKey("second");
        assertThat(results.get("first")).isGreaterThan(results.get("second") / 3);
        assertThat(results.get("second")).isGreaterThan(results.get("first") / 3);
    }

    @Test public void test_random_picker_one_of_three() {
        var picker = new RandomPickerService();
        var list = new ArrayList<String>();
        list.add("first");
        list.add("second");
        list.add("third");

        var times = 1000;
        var results = pickMany(picker, list, times);

        assertThat(results).containsKey("first");
        assertThat(results).containsKey("second");
        assertThat(results).containsKey("third");
        assertThat(results.get("first")).isGreaterThan(results.get("second") / 4);
        assertThat(results.get("second")).isGreaterThan(results.get("first") / 4);
        assertThat(results.get("second")).isGreaterThan(results.get("third") / 4);
        assertThat(results.get("third")).isGreaterThan(results.get("first") / 4);
    }

    @Test public void test_random_picker_one_of_many() {
        var picker = new RandomPickerService();
        var list = new ArrayList<String>();
        list.add("few");
        for (var i = 0; i < 9; i++) list.add("many");

        var times = 1000;
        var results = pickMany(picker, list, times);

        System.out.println(results);
        assertThat(results).containsKey("few");
        assertThat(results).containsKey("many");
        assertThat(results.get("few")).isGreaterThan(results.get("many") / 30);
        assertThat(results.get("many")).isGreaterThan(results.get("few") * 2);
    }

    private static HashMap<String, Integer> pickMany(RandomPickerService picker, List<String> list, int times) {
        var results = new HashMap<String, Integer>();
        for (var i = 0; i < times; i++) {
            var result = picker.pick("test", list);
            results.put(result, results.getOrDefault(result, 0) + 1);
        }
        return results;
    }

}
