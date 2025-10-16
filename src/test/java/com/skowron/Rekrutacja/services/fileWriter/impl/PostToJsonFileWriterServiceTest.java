package com.skowron.Rekrutacja.services.fileWriter.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.repositories.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostToJsonFileWriterServiceTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private FileRepository fileRepository;

    private PostToJsonFileWriterService service;

    @BeforeEach
    void setUp() {
        service = new PostToJsonFileWriterService(objectMapper, fileRepository, "output/posts");
    }

    @Test
    void saveObjectToFile_shouldSerializeToJsonAndCallSave() throws JsonProcessingException {
        // given
        Post post = new Post(12, 1, "Title", "Body");
        String expectedJson = "{\"id\":1}";

        when(objectMapper.writeValueAsString(post)).thenReturn(expectedJson);

        // when
        service.saveObjectToFile(post);

        // then
        verify(objectMapper, times(1)).writeValueAsString(post);
        verify(fileRepository, times(1)).save("output/posts", "1.json", expectedJson);
    }

    @Test
    void saveObjectToFile_shouldThrowRuntimeException_whenObjectMapperThrowsJsonProcessingException() throws JsonProcessingException {
        // given
        Post post = new Post(12, 1, "Title", "Body");
        when(objectMapper.writeValueAsString(post)).thenThrow(new JsonProcessingException("ex") {});

        // when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.saveObjectToFile(post));

        // then
        assertTrue(exception.getMessage().contains("Error while parsing JSON"));
        verify(fileRepository, never()).save(anyString(), anyString(), anyString());
    }

    @Test
    void saveObjectToFile_shouldThrowRuntimeException_whenFileRepositoryThrowsRuntimeException() throws JsonProcessingException {
        // given
        Post post = new Post(12, 1, "Title", "Body");
        String expectedJson = "{\"id\":1}";

        when(objectMapper.writeValueAsString(post)).thenReturn(expectedJson);

        doThrow(new RuntimeException("ex")).when(fileRepository).save("output/posts", "1.json", expectedJson);

        // when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.saveObjectToFile(post));

        // then
        verify(objectMapper, times(1)).writeValueAsString(post);
        verify(fileRepository, times(1)).save("output/posts", "1.json", expectedJson);
        assertTrue(exception.getMessage().contains("Error while saving to file"));
    }
}