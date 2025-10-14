package com.skowron.Rekrutacja;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("unused")
@Component
public class AppRunner implements ApplicationRunner {

    private final PostsService postsService;

    @Autowired
    public AppRunner(PostsService postsService) {
        this.postsService = postsService;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Post> posts = postsService.getPosts();
        System.out.println("Finished");
    }
}
