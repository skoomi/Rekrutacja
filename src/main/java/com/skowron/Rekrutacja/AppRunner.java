package com.skowron.Rekrutacja;

import com.skowron.Rekrutacja.models.Post;
import com.skowron.Rekrutacja.utils.JsonFileWriter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class AppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Łololo");
        Post post = new Post(12,1, "tttt","bbbb");
        JsonFileWriter.saveObjectToJsonFile(post, post.id() + ".json");
    }
}
