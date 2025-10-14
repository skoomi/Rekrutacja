package com.skowron.Rekrutacja;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class AppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Łololo");
    }
}
