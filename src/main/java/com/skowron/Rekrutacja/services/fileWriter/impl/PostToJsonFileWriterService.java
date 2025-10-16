package com.skowron.Rekrutacja.services.fileWriter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.repositories.FileRepository;
import com.skowron.Rekrutacja.services.fileWriter.JsonFileWriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PostToJsonFileWriterService extends JsonFileWriterService<Post> {
    private static final Logger logger = LoggerFactory.getLogger(PostToJsonFileWriterService.class);

    public PostToJsonFileWriterService(
            ObjectMapper objectMapper,
            FileRepository fileRepository,
            @Value("${app.outputDir.posts:output/posts}") String outputDir) {
        super(objectMapper, fileRepository, outputDir, post -> post.id() + ".json");
        logger.info("PostToJsonFileWriterService initialized with outputDir {}", outputDir);
    }
}
