package com.skowron.Rekrutacja.services.fileWriter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.fileWriter.JsonFileWriterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PostToJsonFileWriterService extends JsonFileWriterService<Post> {

    public PostToJsonFileWriterService(
            ObjectMapper objectMapper,
            @Value("${app.outputDir.posts:output/posts}") String outputDir) {
        super(objectMapper, outputDir, post -> post.id() + ".json");
    }
}
