package com.skowron.Rekrutacja;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.fileWriter.FileWriterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class AppRunner implements ApplicationRunner {

    private final FileWriterService<Post> postToJsonService;

    public AppRunner(@Qualifier("postToJsonFileWriterService") FileWriterService<Post> postToJsonService) {
        this.postToJsonService = postToJsonService;
    }

    @Override
    public void run(ApplicationArguments args) {
        Post post = new Post(12,1, "tttt","bbbb");
//        JsonFileWriter.saveObjectToJsonFile(post, post.id() + ".json");
        postToJsonService.saveObjectToFile(post);
    }
}
