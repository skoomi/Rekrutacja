package com.skowron.Rekrutacja.services.fileWriter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.repositories.FileRepository;
import com.skowron.Rekrutacja.services.fileWriter.JsonFileWriterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PostToJsonFileWriterService extends JsonFileWriterService<Post> {

    public PostToJsonFileWriterService(
            ObjectMapper objectMapper,
            FileRepository fileRepository,
            @Value("${app.outputDir.posts:output/posts}") String outputDir) {
        super(objectMapper, fileRepository, outputDir, post -> post.id() + ".json");
    }
}
