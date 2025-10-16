package com.skowron.Rekrutacja.services.fileWriter;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public abstract class JsonFileWriterService<T> implements FileWriterService<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Path outputDir;
    private final Function<T, String> fileNameProvider;

    public JsonFileWriterService(String outputDir, Function<T, String> fileNameProvider) {
        this.outputDir = Paths.get(outputDir);
        this.fileNameProvider = fileNameProvider;

        try {
            Files.createDirectories(this.outputDir);
        } catch (IOException e) {
            throw new RuntimeException("Error creating output directory: " + outputDir, e);
        }
    }

    @Override
    public void saveObjectToFile(T object) {
        Path filePath = outputDir.resolve(fileNameProvider.apply(object));

        String json = objectMapper.writeValueAsString(object);

        try {
            Files.writeString(filePath, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
