package com.skowron.Rekrutacja.repositories.impl;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class FileRepository implements com.skowron.Rekrutacja.repositories.FileRepository {

    @Override
    public void save(String directory, String fileName, String content) {

        try {
            Path outputDir = Paths.get(directory);
            Files.createDirectories(outputDir);
            Path filePath = outputDir.resolve(fileName);
            Files.writeString(filePath, content);
        } catch (IOException e) {
            throw new RuntimeException("Error while saving to file", e);
        }
    }
}
