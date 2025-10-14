package com.skowron.Rekrutacja.services;

import com.skowron.Rekrutacja.models.Post;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class PostsService {
    private final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    public List<Post> getPosts() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(POSTS_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            return mapResponseToList(response.body());
        } catch (java.io.IOException | InterruptedException e) {
            throw new RuntimeException("Error while fetching posts", e);
        }
    }

    private List<Post> mapResponseToList(String responseToParse) {
//        Można by użyć spring boot starter json, wtedy z automatu będzie bean mapper
        ObjectMapper mapper = new ObjectMapper();
        return mapper.<List<Post>>readValue(responseToParse, new TypeReference<>(){});
//        List<Post> posts = mapper.readValue(responseToParse, new TypeReference<>(){});
//        return posts;
    }
}
