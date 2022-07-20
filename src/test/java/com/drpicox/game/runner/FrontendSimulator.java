package com.drpicox.game.runner;

import com.drpicox.game.blog.api.ListPostsResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Service
public class FrontendSimulator {

    private final MockMvc mockMvc;

    private final Gson gson;

    public FrontendSimulator(MockMvc mockMvc, Gson gson) {
        this.mockMvc = mockMvc;
        this.gson = gson;
    }

    public <T> T get(String url, Class<T> type) {
        String result = null;
        try {
            result = mockMvc.perform(MockMvcRequestBuilders.get(url))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            throw new RuntimeException("Error while executing the API REST Call: GET " + url, e);
        }

        var list = gson.fromJson(result, type);
        return list;
    }
}
