package com.skowron.Rekrutacja;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.posts.PostsService;
import com.skowron.Rekrutacja.services.fileWriter.FileWriterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("unused")
@Component
public class AppRunner implements ApplicationRunner {

    private final PostsService postsService;
    private final FileWriterService<Post> postToJsonService;

    public AppRunner(
            PostsService postsService,
            @Qualifier("postToJsonFileWriterService") FileWriterService<Post> postToJsonService) {
        this.postsService = postsService;
        this.postToJsonService = postToJsonService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Post> posts = postsService.getAll();
        for (Post post : posts) {
            postToJsonService.saveObjectToFile(post);
        }
    }
}
