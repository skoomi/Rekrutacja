package com.skowron.Rekrutacja.services.fileWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public abstract class JsonFileWriterService<T> implements FileWriterService<T> {

    private final ObjectMapper objectMapper;
    private final Path outputDir;
    private final Function<T, String> fileNameProvider;

    public JsonFileWriterService(ObjectMapper objectMapper, String outputDir, Function<T, String> fileNameProvider) {
        this.objectMapper = objectMapper;
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

        try {
            String json = objectMapper.writeValueAsString(object);
            Files.writeString(filePath, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing JSON", e);
        } catch (IOException e) {
            throw new RuntimeException("Error while saving to file",e);
        }
    }
}
