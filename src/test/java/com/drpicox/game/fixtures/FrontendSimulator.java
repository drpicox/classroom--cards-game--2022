package com.drpicox.game.fixtures;

import com.drpicox.game.game.api.GameResponse;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Service
public class FrontendSimulator {

    private final MockMvc mockMvc;

    private final Gson gson;

    private List<FrontendBackendInteraction> interactions = new ArrayList<>();

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

        interactions.add(new FrontendBackendInteraction("GET", url, null, list));

        return list;
    }



    public <T> T post(String url, Object requestBody, Class<T> type) {
        String result = null;
        var requestBodyJson = gson.toJson(requestBody);
        try {
            result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBodyJson)
                    .characterEncoding("utf-8")
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        } catch (Exception e) {
            throw new RuntimeException("Error while executing the API REST Call: POST " + url, e);
        }

        var list = gson.fromJson(result, type);

        interactions.add(new FrontendBackendInteraction("POST", url, requestBody, list));

        return list;
    }

    public void clear(String postId) {
        interactions.clear();
        try {
            var file = new File(getInteractionsUri(postId));
            file.delete();
        } catch (Exception reason) {
            System.err.println(reason);
        }
    }

    public void save(String postId) {
        var output = gson.toJson(interactions);
        try {
            var uri = getInteractionsUri(postId);
            try (var fw = new FileWriter(new File(uri))) {
                fw.append(output);
                fw.flush();
            }
        } catch (Exception reason) {
            throw new RuntimeException("Cannot write post frontend backend interactions for " + postId, reason);
        }
    }

    private URI getInteractionsUri(String postId) throws MalformedURLException, URISyntaxException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        var directoryUrl = loader.getResource("frontendBackendInteractions");
        var interactionsUrl = new URL(directoryUrl, "frontendBackendInteractions/" + postId + ".json");
        return interactionsUrl.toURI();
    }
}
