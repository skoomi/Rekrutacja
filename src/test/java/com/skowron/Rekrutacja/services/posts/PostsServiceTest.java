package com.skowron.Rekrutacja.services.posts;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.fileWriter.impl.PostToJsonFileWriterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostsServiceTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Mock
    private RestTemplate restTemplate;

    private PostsService postsService;

    @BeforeEach
    void setUp() {
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        when(builder.build()).thenReturn(restTemplate);
        postsService = new PostsService(builder, BASE_URL);
    }

    @Test
    void getAll_shouldReturnEmptyList_whenResponseIsEmpty() {
        // given
        Post[] posts = {};
        ResponseEntity<Post[]> response = new ResponseEntity<>(posts, HttpStatus.OK);

        when(restTemplate.getForEntity(BASE_URL, Post[].class)).thenReturn(response);

        // when
        List<Post> result = postsService.getAll();

        // then
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(restTemplate, times(1)).getForEntity(BASE_URL, Post[].class);
    }

    @Test
    void getAll_shouldReturnPostsList_whenResponseIsSuccessful() {
        // given
        Post[] posts = {
                new Post(1, 1, "title", "body"),
                new Post(2, 2, "title2", "body2")};

        ResponseEntity<Post[]> response = new ResponseEntity<>(posts, HttpStatus.OK);

        when(restTemplate.getForEntity(BASE_URL, Post[].class)).thenReturn(response);

        // when
        List<Post> result = postsService.getAll();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(posts[0].id(), result.get(0).id());
        assertEquals(posts[1].id(), result.get(1).id());
        verify(restTemplate, times(1)).getForEntity(BASE_URL, Post[].class);
    }

    @Test
    void getAll_shouldReturnEmptyList_whenBodyIsNull() {
        // given
        ResponseEntity<Post[]> entity = new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.getForEntity(BASE_URL, Post[].class)).thenReturn(entity);

        // when
        List<Post> result = postsService.getAll();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getAll_shouldThrowRuntimeException_whenResponseStatusIsError() {
        // given
        ResponseEntity<Post[]> entity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.getForEntity(BASE_URL, Post[].class)).thenReturn(entity);

        //when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> postsService.getAll());

        // then
        assertTrue(exception.getMessage().contains("Http error: "));
    }
}