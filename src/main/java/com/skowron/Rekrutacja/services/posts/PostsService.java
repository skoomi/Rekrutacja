package com.skowron.Rekrutacja.services.posts;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.RestApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostsService implements RestApiService<Post> {
    private static final Logger logger = LoggerFactory.getLogger(PostsService.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PostsService(RestTemplateBuilder restTemplateBuilder,
                                     @Value("${posts.url:https://jsonplaceholder.typicode.com/posts}") String baseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
        logger.info("PostsService initialized with URL: {}", baseUrl);
    }

    @Override
    public List<Post> getAll() {
        logger.debug("Fetching all posts from URL: {}", baseUrl);

        ResponseEntity<Post[]> response = restTemplate.getForEntity(baseUrl, Post[].class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("HTTP error status: {}", response.getStatusCode());
            throw new RuntimeException("Http error: " + response.getStatusCode());
        }

        List<Post> posts = response.getBody() != null ? List.of(response.getBody()) : List.of();
        logger.debug("Successfully fetched {} posts from {}", posts.size(), baseUrl);
        return posts;
    }
}
