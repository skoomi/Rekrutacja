package com.skowron.Rekrutacja.services.fileWriter.impl;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.fileWriter.JsonFileWriterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PostToJsonFileWriterService extends JsonFileWriterService<Post> {

    public PostToJsonFileWriterService(@Value("${app.outputDir.posts:output/posts}") String outputDir) {
        super(outputDir, post -> post.id() + ".json");
    }
}
