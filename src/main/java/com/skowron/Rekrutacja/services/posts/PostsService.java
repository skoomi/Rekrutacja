package com.skowron.Rekrutacja.services.posts;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.RestApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostsService implements RestApiService<Post> {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PostsService(RestTemplateBuilder restTemplateBuilder,
                                     @Value("${posts.url:https://jsonplaceholder.typicode.com/posts}") String baseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Post> getAll() {
        ResponseEntity<Post[]> response = restTemplate.getForEntity(baseUrl, Post[].class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Http error: " + response.getStatusCode());
        }
        return response.getBody() != null ? List.of(response.getBody()) : List.of();
    }
}
